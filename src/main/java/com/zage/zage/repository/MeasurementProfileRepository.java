package com.zage.zage.repository;

import com.zage.zage.dto.MeasurementProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MeasurementProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public MeasurementProfileDto insertMeasurementProfile(MeasurementProfileDto dto) {
        String sql = "INSERT INTO measurement_profiles (name, is_default, created_at, customers_id, customers_users_id) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        if (dto.getCreatedAt() == null) {
            dto.setCreatedAt(LocalDateTime.now());
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setObject(2, dto.getIsDefault());
            ps.setTimestamp(3, Timestamp.valueOf(dto.getCreatedAt()));
            ps.setObject(4, dto.getCustomersId());
            ps.setObject(5, dto.getCustomersUsersId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<MeasurementProfileDto> findById(Integer id) {
        String sql = "SELECT * FROM measurement_profiles WHERE id = ?";
        List<MeasurementProfileDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<MeasurementProfileDto> findAll() {
        String sql = "SELECT * FROM measurement_profiles";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public MeasurementProfileDto updateMeasurementProfile(Integer id, MeasurementProfileDto dto) {
        String sql = "UPDATE measurement_profiles SET name = ?, is_default = ?, created_at = ?, customers_id = ?, customers_users_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getIsDefault(), dto.getCreatedAt() != null ? Timestamp.valueOf(dto.getCreatedAt()) : null, dto.getCustomersId(), dto.getCustomersUsersId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteMeasurementProfile(Integer id) {
        String sql = "DELETE FROM measurement_profiles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private MeasurementProfileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeasurementProfileDto dto = new MeasurementProfileDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setIsDefault(rs.getObject("is_default") != null ? rs.getBoolean("is_default") : null);
        
        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            dto.setCreatedAt(timestamp.toLocalDateTime());
        }
        
        dto.setCustomersId(rs.getObject("customers_id") != null ? rs.getInt("customers_id") : null);
        dto.setCustomersUsersId(rs.getObject("customers_users_id") != null ? rs.getInt("customers_users_id") : null);
        return dto;
    }
}
