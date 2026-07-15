package com.zage.zage.repository;

import com.zage.zage.dto.SizeDto;
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
public class SizeRepository {

    private final JdbcTemplate jdbcTemplate;

    public SizeDto insertSize(SizeDto dto) {
        String sql = "INSERT INTO sizes (name, type) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getType());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<SizeDto> findById(Integer id) {
        String sql = "SELECT * FROM sizes WHERE id = ?";
        List<SizeDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<SizeDto> findAll() {
        String sql = "SELECT * FROM sizes";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public SizeDto updateSize(Integer id, SizeDto dto) {
        String sql = "UPDATE sizes SET name = ?, type = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getType(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteSize(Integer id) {
        String sql = "DELETE FROM sizes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private SizeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        SizeDto dto = new SizeDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setType(rs.getString("type"));
        return dto;
    }
}
