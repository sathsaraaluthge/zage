package com.zage.zage.repository;

import com.zage.zage.dto.MeasurementValueDto;
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
public class MeasurementValueRepository {

    private final JdbcTemplate jdbcTemplate;

    public MeasurementValueDto insertMeasurementValue(MeasurementValueDto dto) {
        String sql = "INSERT INTO measurement_values (value, unit, measurement_profiles_id, measurement_profiles_customers_id, measurement_profiles_customers_users_id, measurement_types_id) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, dto.getValue());
            ps.setString(2, dto.getUnit());
            ps.setObject(3, dto.getMeasurementProfilesId());
            ps.setObject(4, dto.getMeasurementProfilesCustomersId());
            ps.setObject(5, dto.getMeasurementProfilesCustomersUsersId());
            ps.setObject(6, dto.getMeasurementTypesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<MeasurementValueDto> findById(Integer id) {
        String sql = "SELECT * FROM measurement_values WHERE id = ?";
        List<MeasurementValueDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<MeasurementValueDto> findAll() {
        String sql = "SELECT * FROM measurement_values";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public MeasurementValueDto updateMeasurementValue(Integer id, MeasurementValueDto dto) {
        String sql = "UPDATE measurement_values SET value = ?, unit = ?, measurement_profiles_id = ?, measurement_profiles_customers_id = ?, measurement_profiles_customers_users_id = ?, measurement_types_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getValue(), dto.getUnit(), dto.getMeasurementProfilesId(), dto.getMeasurementProfilesCustomersId(), dto.getMeasurementProfilesCustomersUsersId(), dto.getMeasurementTypesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteMeasurementValue(Integer id) {
        String sql = "DELETE FROM measurement_values WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private MeasurementValueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeasurementValueDto dto = new MeasurementValueDto();
        dto.setId(rs.getInt("id"));
        dto.setValue(rs.getBigDecimal("value"));
        dto.setUnit(rs.getString("unit"));
        dto.setMeasurementProfilesId(rs.getObject("measurement_profiles_id") != null ? rs.getInt("measurement_profiles_id") : null);
        dto.setMeasurementProfilesCustomersId(rs.getObject("measurement_profiles_customers_id") != null ? rs.getInt("measurement_profiles_customers_id") : null);
        dto.setMeasurementProfilesCustomersUsersId(rs.getObject("measurement_profiles_customers_users_id") != null ? rs.getInt("measurement_profiles_customers_users_id") : null);
        dto.setMeasurementTypesId(rs.getObject("measurement_types_id") != null ? rs.getInt("measurement_types_id") : null);
        return dto;
    }
}
