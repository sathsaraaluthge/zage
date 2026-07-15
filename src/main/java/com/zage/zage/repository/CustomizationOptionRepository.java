package com.zage.zage.repository;

import com.zage.zage.dto.CustomizationOptionDto;
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
public class CustomizationOptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomizationOptionDto insertCustomizationOption(CustomizationOptionDto dto) {
        String sql = "INSERT INTO customization_options (name, type) VALUES (?, ?)";
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

    public Optional<CustomizationOptionDto> findById(Integer id) {
        String sql = "SELECT * FROM customization_options WHERE id = ?";
        List<CustomizationOptionDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomizationOptionDto> findAll() {
        String sql = "SELECT * FROM customization_options";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public CustomizationOptionDto updateCustomizationOption(Integer id, CustomizationOptionDto dto) {
        String sql = "UPDATE customization_options SET name = ?, type = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getType(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteCustomizationOption(Integer id) {
        String sql = "DELETE FROM customization_options WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private CustomizationOptionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomizationOptionDto dto = new CustomizationOptionDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setType(rs.getString("type"));
        return dto;
    }
}
