package com.zage.zage.repository;

import com.zage.zage.dto.StaffRequestDto;
import com.zage.zage.dto.StaffResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StaffRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long saveStaff(StaffRequestDto dto) {
        String sql = """
            INSERT INTO users
            (name, email, phone, password, role)
            VALUES (?,?,?,?,?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getFirstName() + " " + dto.getLastName()); // assuming staffname is passed as well, but we use name for Users table
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getPhone());
            ps.setString(4, dto.getPassword());
            ps.setString(5, "STAFF");
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<StaffResponseDto> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id=? AND role='STAFF'";
        List<StaffResponseDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<StaffResponseDto> findAll() {
        String sql = "SELECT * FROM users WHERE role='STAFF'";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public boolean existsByEmail(String email) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE email=? AND role='STAFF'",
                Integer.class,
                email);
        return count != null && count > 0;
    }

    public boolean existsByStaffname(String staffname) {
        // Since we map staffname to name in the users table
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE name=? AND role='STAFF'",
                Integer.class,
                staffname);
        return count != null && count > 0;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=? AND role='STAFF'", id);
    }

    private StaffResponseDto mapRow(ResultSet rs, int row) throws SQLException {
        String fullName = rs.getString("name");
        String firstName = "";
        String lastName = "";
        if (fullName != null) {
            String[] parts = fullName.split(" ", 2);
            firstName = parts[0];
            if (parts.length > 1) {
                lastName = parts[1];
            }
        }

        return StaffResponseDto.builder()
                .id(rs.getLong("id"))
                .staffname(fullName)
                .email(rs.getString("email"))
                .firstName(firstName)
                .lastName(lastName)
                .phone(rs.getString("phone"))
                .status("ACTIVE".equalsIgnoreCase(rs.getString("status")))
                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .build();
    }
}
