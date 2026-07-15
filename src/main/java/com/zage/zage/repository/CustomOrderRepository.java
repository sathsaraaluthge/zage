package com.zage.zage.repository;

import com.zage.zage.dto.CustomOrderDto;
import com.zage.zage.enums.Status;
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
public class CustomOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomOrderDto insertCustomOrder(CustomOrderDto dto) {
        String sql = "INSERT INTO custom_orders (status, notes, products_id, products_categories_id, measurement_profiles_id, measurement_profiles_customers_id, measurement_profiles_customers_users_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        if (dto.getStatus() == null) {
            dto.setStatus(Status.ACTIVE);
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getStatus().name());
            ps.setString(2, dto.getNotes());
            ps.setObject(3, dto.getProductsId());
            ps.setObject(4, dto.getProductsCategoriesId());
            ps.setObject(5, dto.getMeasurementProfilesId());
            ps.setObject(6, dto.getMeasurementProfilesCustomersId());
            ps.setObject(7, dto.getMeasurementProfilesCustomersUsersId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<CustomOrderDto> findById(Integer id) {
        String sql = "SELECT * FROM custom_orders WHERE id = ?";
        List<CustomOrderDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomOrderDto> findAll() {
        String sql = "SELECT * FROM custom_orders WHERE status = 'ACTIVE'";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public CustomOrderDto updateCustomOrder(Integer id, CustomOrderDto dto) {
        String sql = "UPDATE custom_orders SET status = ?, notes = ?, products_id = ?, products_categories_id = ?, measurement_profiles_id = ?, measurement_profiles_customers_id = ?, measurement_profiles_customers_users_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getStatus().name(), dto.getNotes(), dto.getProductsId(), dto.getProductsCategoriesId(), dto.getMeasurementProfilesId(), dto.getMeasurementProfilesCustomersId(), dto.getMeasurementProfilesCustomersUsersId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteCustomOrder(Integer id) {
        String sql = "UPDATE custom_orders SET status = 'INACTIVE' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private CustomOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomOrderDto dto = new CustomOrderDto();
        dto.setId(rs.getInt("id"));
        
        String statusStr = rs.getString("status");
        if (statusStr != null) {
            try {
                dto.setStatus(Status.valueOf(statusStr));
            } catch (IllegalArgumentException e) {
                dto.setStatus(null);
            }
        }
        
        dto.setNotes(rs.getString("notes"));
        dto.setProductsId(rs.getObject("products_id") != null ? rs.getInt("products_id") : null);
        dto.setProductsCategoriesId(rs.getObject("products_categories_id") != null ? rs.getInt("products_categories_id") : null);
        dto.setMeasurementProfilesId(rs.getObject("measurement_profiles_id") != null ? rs.getInt("measurement_profiles_id") : null);
        dto.setMeasurementProfilesCustomersId(rs.getObject("measurement_profiles_customers_id") != null ? rs.getInt("measurement_profiles_customers_id") : null);
        dto.setMeasurementProfilesCustomersUsersId(rs.getObject("measurement_profiles_customers_users_id") != null ? rs.getInt("measurement_profiles_customers_users_id") : null);
        return dto;
    }
}
