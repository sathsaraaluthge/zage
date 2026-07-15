package com.zage.zage.repository;

import com.zage.zage.dto.CustomizationValueDto;
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
public class CustomizationValueRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomizationValueDto insertCustomizationValue(CustomizationValueDto dto) {
        String sql = "INSERT INTO customization_values (value, customization_options_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getValue());
            ps.setObject(2, dto.getCustomizationOptionsId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<CustomizationValueDto> findById(Integer id) {
        String sql = "SELECT * FROM customization_values WHERE id = ?";
        List<CustomizationValueDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomizationValueDto> findAll() {
        String sql = "SELECT * FROM customization_values";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public CustomizationValueDto updateCustomizationValue(Integer id, CustomizationValueDto dto) {
        String sql = "UPDATE customization_values SET value = ?, customization_options_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getValue(), dto.getCustomizationOptionsId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteCustomizationValue(Integer id) {
        String sql = "DELETE FROM customization_values WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private CustomizationValueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomizationValueDto dto = new CustomizationValueDto();
        dto.setId(rs.getInt("id"));
        dto.setValue(rs.getString("value"));
        dto.setCustomizationOptionsId(rs.getObject("customization_options_id") != null ? rs.getInt("customization_options_id") : null);
        return dto;
    }
}
