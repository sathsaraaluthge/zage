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
            INSERT INTO staff
            (staffname, email, first_name, last_name, phone)
            VALUES (?,?,?,?,?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getStaffname());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getFirstName());
            ps.setString(4, dto.getLastName());
            ps.setString(5, dto.getPhone());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<StaffResponseDto> findById(Long id) {
        String sql = "SELECT * FROM staff WHERE id=?";
        List<StaffResponseDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<StaffResponseDto> findAll() {
        String sql = "SELECT * FROM staff";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public boolean existsByEmail(String email) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM staff WHERE email=?",
                Integer.class,
                email);
        return count != null && count > 0;
    }

    public boolean existsByStaffname(String staffname) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM staff WHERE staffname=?",
                Integer.class,
                staffname);
        return count != null && count > 0;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM staff WHERE id=?", id);
    }

    private StaffResponseDto mapRow(ResultSet rs, int row) throws SQLException {
        return StaffResponseDto.builder()
                .id(rs.getLong("id"))
                .staffname(rs.getString("staffname"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .status(rs.getBoolean("status"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
