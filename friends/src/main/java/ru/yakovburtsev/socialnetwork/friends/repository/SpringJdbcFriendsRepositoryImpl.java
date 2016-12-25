package ru.yakovburtsev.socialnetwork.friends.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import javax.sql.DataSource;
import java.util.List;

/**
 * The class define dao methods for a user friends
 */

@Repository
@Transactional(readOnly = true)
public class SpringJdbcFriendsRepositoryImpl implements FriendsRepository {
    private static final String GET_FRIENDS_IDS = "SELECT friend_id FROM friends WHERE user_id=?";
    private static final String GET_FRIENDS = "SELECT id, name, surname FROM users WHERE id IN (:ids)";
    private static final String DELETE_FROM_FRIENDS = "DELETE FROM friends WHERE user_id=:user_id AND friend_id=:friend_id";
    private static final String USER_ID = "user_id";
    private static final String FRIEND_ID = "friend_id";

    private static final BeanPropertyRowMapper<UserInfo> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserInfo.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertFriend;

    @Autowired
    public SpringJdbcFriendsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertFriend = new SimpleJdbcInsert(dataSource)
                .withTableName("friends")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<UserInfo> getFriends(Long userId) {
        List<Long> ids = jdbcTemplate.queryForList(GET_FRIENDS_IDS, Long.class, userId);
        MapSqlParameterSource map = new MapSqlParameterSource().addValue("ids", ids);
        return namedParameterJdbcTemplate.query(GET_FRIENDS, map, ROW_MAPPER);
    }

    @Override
    @Transactional
    public boolean addFriend(Long userId, Long friendId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue(USER_ID, userId)
                .addValue(FRIEND_ID, friendId);
        boolean addToUserResult = insertFriend.execute(map) != 0;
        map = new MapSqlParameterSource()
                .addValue(USER_ID, friendId)
                .addValue(FRIEND_ID, userId);
        boolean addToFriendResult = insertFriend.execute(map) != 0;
        return addToUserResult && addToFriendResult;
    }

    @Override
    @Transactional
    public boolean deleteFromFriends(Long userId, Long friendId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue(USER_ID, userId)
                .addValue(FRIEND_ID, friendId);
        boolean deleteFromUserResult = namedParameterJdbcTemplate.update(DELETE_FROM_FRIENDS, map) != 0;

        map = new MapSqlParameterSource()
                .addValue(FRIEND_ID, userId)
                .addValue(USER_ID, friendId);
        boolean deleteFromFriendResult = namedParameterJdbcTemplate.update(DELETE_FROM_FRIENDS, map) != 0;
        return deleteFromUserResult && deleteFromFriendResult;
    }
}
