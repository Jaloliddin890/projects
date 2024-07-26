package uz.pdp.daos;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import uz.pdp.domains.AuthRoles;
import uz.pdp.domains.AuthUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AuthRoleDao {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthRoleDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<AuthRoles> findByUserID(@NonNull Long userID) {
        var sql = "select r.* from authuser_authrole au inner join authrole r on au.role_id = r.id where au.user_id = :userID";
        var sqlParam = new MapSqlParameterSource().addValue("userID", userID);


        try {
            return namedParameterJdbcTemplate.query(sql, sqlParam, (rs, rowNum) -> AuthRoles.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .code(rs.getString("code"))
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
