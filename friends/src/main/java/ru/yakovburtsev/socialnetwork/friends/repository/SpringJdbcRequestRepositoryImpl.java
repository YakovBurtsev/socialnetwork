package ru.yakovburtsev.socialnetwork.friends.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.Request;

import javax.sql.DataSource;
import java.util.List;

/**
 * The class define dao methods for requests for adding a new friend
 */
@Repository
@Transactional(readOnly = true)
public class SpringJdbcRequestRepositoryImpl implements RequestRepository {
    private static final String GET = "SELECT * FROM requests WHERE id=?";
    private static final String DELETE = "DELETE FROM requests WHERE id=?";
    private static final String GET_SENT_REQUESTS = "SELECT * FROM requests WHERE from_user_id=?";
    private static final String GET_RECEIVE_REQUESTS = "SELECT * FROM requests WHERE to_user_id=?";

    private static final BeanPropertyRowMapper<Request> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Request.class);

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertRequest;

    @Autowired
    public SpringJdbcRequestRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertRequest = new SimpleJdbcInsert(dataSource)
                .withTableName("requests")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Request save(Request request) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", request.getId())
                .addValue("from_user_id", request.getFromUserId())
                .addValue("to_user_id", request.getToUserId());

        Number newKey = insertRequest.executeAndReturnKey(map);
        request.setId(newKey.longValue());
        return request;
    }

    @Override
    public Request get(Long id) {
        List<Request> requests = jdbcTemplate.query(GET, ROW_MAPPER, id);
        return DataAccessUtils.singleResult(requests);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE, id) != 0;
    }

    @Override
    public List<Request> getSentRequests(Long userId) {
        return jdbcTemplate.query(GET_SENT_REQUESTS, ROW_MAPPER, userId);
    }

    @Override
    public List<Request> getReceiveRequests(Long userId) {
        return jdbcTemplate.query(GET_RECEIVE_REQUESTS, ROW_MAPPER, userId);
    }
}
