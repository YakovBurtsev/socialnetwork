package ru.yakovburtsev.socialnetwork.friends.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * The class define dao methods for a user friends
 */

@Repository
@Transactional(readOnly = true)
public class SpringJdbcFriendsRepositoryImpl implements FriendsRepository {
    private static final String GET_FRIENDS_IDS = "SELECT friend_id FROM friends WHERE id=?";
    private static final String DELETE_FROM_FRIENDS = "DELETE FROM friends WHERE id=:id AND friend_id=:friend_id";

    private static final BeanPropertyRowMapper<Long> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Long.class);

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
        return jdbcTemplate.query(GET_FRIENDS_IDS, ROW_MAPPER, userId);
    }

    @Override
    @Transactional
    public boolean addFriend(Long userId, Long friendId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userId)
                .addValue("friend_id", friendId);
        return insertFriend.execute(map) != 0;
    }

    @Override
    @Transactional
    public boolean deleteFromFriends(Long userId, Long friendId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userId)
                .addValue("friend_id", friendId);
        return namedParameterJdbcTemplate.update(DELETE_FROM_FRIENDS, map) != 0;
    }
}
