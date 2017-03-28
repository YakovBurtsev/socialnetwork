package ru.yakovburtsev.socialnetwork.friends.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import javax.sql.DataSource;
import java.util.List;

/**
 * The class define dao methods for a user friends
 */

@Repository
public class SpringJdbcFriendsRepositoryImpl implements FriendsRepository {
    private static final String IS_FRIEND = "SELECT COUNT(*) FROM friends WHERE user_id=:user_id AND friend_id=:friend_id";
    private static final String GET_FRIENDS_IDS = "SELECT friend_id FROM friends WHERE user_id=?";
    private static final String DELETE_FROM_FRIENDS = "DELETE FROM friends WHERE user_id=:user_id AND friend_id=:friend_id";
    private static final String USER_ID = "user_id";
    private static final String FRIEND_ID = "friend_id";

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
    public List<Long> getFriendsIds(Long userId) {
        return jdbcTemplate.queryForList(GET_FRIENDS_IDS, Long.class, userId);
    }

    @Override
    public boolean isFriend(Long userId, Long friendId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("friend_id", friendId);
        int count = namedParameterJdbcTemplate.queryForObject(IS_FRIEND, map, Integer.class);
        return count == 1;
    }

    @Override
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
