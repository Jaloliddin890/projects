package uz.pdp.daos;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import uz.pdp.domains.AuthUser;

import java.util.Optional;

@Component
public class AuthUserDao {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthUserDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long save(@NonNull AuthUser authUser) {
        var sql = "insert into authuser(username, password) values(:username, :password)";
        var sqlParam = new MapSqlParameterSource()
                .addValue("username", authUser.getUsername())
                .addValue("password", authUser.getPassword());

        var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParam, keyHolder, new String[]{"id"});

        return (Long) keyHolder.getKeys().get("id");
    }


    public Optional<AuthUser> findByUsername(@NonNull String username) {
        var sql = "select * from authuser where username = :username";
        var sqlParam = new MapSqlParameterSource().addValue("username", username);
        var rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, sqlParam,rowMapper));
    }
}
