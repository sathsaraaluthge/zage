package com.zage.zage.repository;

import com.zage.zage.dto.MeasurementTypeDto;
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
public class MeasurementTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public MeasurementTypeDto insertMeasurementType(MeasurementTypeDto dto) {
        String sql = "INSERT INTO measurement_types (name, category, unit) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getCategory());
            ps.setString(3, dto.getUnit());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<MeasurementTypeDto> findById(Integer id) {
        String sql = "SELECT * FROM measurement_types WHERE id = ?";
        List<MeasurementTypeDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<MeasurementTypeDto> findAll() {
        String sql = "SELECT * FROM measurement_types";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public MeasurementTypeDto updateMeasurementType(Integer id, MeasurementTypeDto dto) {
        String sql = "UPDATE measurement_types SET name = ?, category = ?, unit = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getCategory(), dto.getUnit(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteMeasurementType(Integer id) {
        String sql = "DELETE FROM measurement_types WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private MeasurementTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeasurementTypeDto dto = new MeasurementTypeDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setCategory(rs.getString("category"));
        dto.setUnit(rs.getString("unit"));
        return dto;
    }
}
