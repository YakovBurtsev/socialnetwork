package ru.yakovburtsev.socialnetwork.user.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.User;

import javax.sql.DataSource;
import java.util.List;


@Repository
@Transactional
public class UserRepositorySpringJdbcImpl implements UserRepository {

    private static final String UPDATE = "UPDATE users SET name=:name, surname=:surname, birthday=:birthday, " +
            "sex=:sex, city=:city, email=:email, password=:password WHERE id=:id";
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String GET = "SELECT * FROM users WHERE id=?";
    private static final String GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertUser;

    @Autowired
    public UserRepositorySpringJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("birthday", user.getBirthday())
                .addValue("sex", user.getSex())
                .addValue("city", user.getCity())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.longValue());
        } else {
            namedParameterJdbcTemplate.update(UPDATE, map);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE, id) != 0;
    }

    @Override
    public User get(Long id) {
        List<User> users = jdbcTemplate.query(GET, ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query(GET_BY_EMAIL, ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }
}
