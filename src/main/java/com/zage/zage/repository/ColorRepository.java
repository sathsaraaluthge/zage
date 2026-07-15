package com.zage.zage.repository;

import com.zage.zage.dto.ColorDto;
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
public class ColorRepository {

    private final JdbcTemplate jdbcTemplate;

    public ColorDto insertColor(ColorDto dto) {
        String sql = "INSERT INTO colors (name, hex_code) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getHexCode());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<ColorDto> findById(Integer id) {
        String sql = "SELECT * FROM colors WHERE id = ?";
        List<ColorDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<ColorDto> findAll() {
        String sql = "SELECT * FROM colors";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public ColorDto updateColor(Integer id, ColorDto dto) {
        String sql = "UPDATE colors SET name = ?, hex_code = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getHexCode(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteColor(Integer id) {
        String sql = "DELETE FROM colors WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private ColorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColorDto dto = new ColorDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setHexCode(rs.getString("hex_code"));
        return dto;
    }
}
