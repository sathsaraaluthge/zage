package com.zage.zage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveLogin(Long userId, String username, String password, String role) {
        String sql = """
            INSERT INTO user_login
            (user_id, username, password, role)
            VALUES (?,?,?,?)
            """;
        jdbcTemplate.update(sql, userId, username, password, role);
    }

    public Optional<UserLogin> findByUsername(String username) {
        String sql = "SELECT * FROM user_login WHERE username=?";
        List<UserLogin> result = jdbcTemplate.query(sql, this::mapRow, username);
        return result.stream().findFirst();
    }

    private UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserLogin user = new UserLogin();
        user.setId(rs.getLong("id"));
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
