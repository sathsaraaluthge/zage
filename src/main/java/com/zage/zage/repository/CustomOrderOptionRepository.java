package com.zage.zage.repository;

import com.zage.zage.dto.CustomOrderOptionDto;
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
public class CustomOrderOptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomOrderOptionDto insertCustomOrderOption(CustomOrderOptionDto dto) {
        String sql = "INSERT INTO custom_order_options (custom_orders_id, custom_orders_products_id, custom_orders_products_categories_id, custom_orders_measurement_profiles_id, custom_orders_measurement_profiles_customers_id, custom_orders_measurement_profiles_customers_users_id, customization_options_id, customization_values_id, customization_values_customization_options_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, dto.getCustomOrdersId());
            ps.setObject(2, dto.getCustomOrdersProductsId());
            ps.setObject(3, dto.getCustomOrdersProductsCategoriesId());
            ps.setObject(4, dto.getCustomOrdersMeasurementProfilesId());
            ps.setObject(5, dto.getCustomOrdersMeasurementProfilesCustomersId());
            ps.setObject(6, dto.getCustomOrdersMeasurementProfilesCustomersUsersId());
            ps.setObject(7, dto.getCustomizationOptionsId());
            ps.setObject(8, dto.getCustomizationValuesId());
            ps.setObject(9, dto.getCustomizationValuesCustomizationOptionsId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<CustomOrderOptionDto> findById(Integer id) {
        String sql = "SELECT * FROM custom_order_options WHERE id = ?";
        List<CustomOrderOptionDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomOrderOptionDto> findAll() {
        String sql = "SELECT * FROM custom_order_options";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public CustomOrderOptionDto updateCustomOrderOption(Integer id, CustomOrderOptionDto dto) {
        String sql = "UPDATE custom_order_options SET custom_orders_id = ?, custom_orders_products_id = ?, custom_orders_products_categories_id = ?, custom_orders_measurement_profiles_id = ?, custom_orders_measurement_profiles_customers_id = ?, custom_orders_measurement_profiles_customers_users_id = ?, customization_options_id = ?, customization_values_id = ?, customization_values_customization_options_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getCustomOrdersId(), dto.getCustomOrdersProductsId(), dto.getCustomOrdersProductsCategoriesId(), dto.getCustomOrdersMeasurementProfilesId(), dto.getCustomOrdersMeasurementProfilesCustomersId(), dto.getCustomOrdersMeasurementProfilesCustomersUsersId(), dto.getCustomizationOptionsId(), dto.getCustomizationValuesId(), dto.getCustomizationValuesCustomizationOptionsId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteCustomOrderOption(Integer id) {
        String sql = "DELETE FROM custom_order_options WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private CustomOrderOptionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomOrderOptionDto dto = new CustomOrderOptionDto();
        dto.setId(rs.getInt("id"));
        dto.setCustomOrdersId(rs.getObject("custom_orders_id") != null ? rs.getInt("custom_orders_id") : null);
        dto.setCustomOrdersProductsId(rs.getObject("custom_orders_products_id") != null ? rs.getInt("custom_orders_products_id") : null);
        dto.setCustomOrdersProductsCategoriesId(rs.getObject("custom_orders_products_categories_id") != null ? rs.getInt("custom_orders_products_categories_id") : null);
        dto.setCustomOrdersMeasurementProfilesId(rs.getObject("custom_orders_measurement_profiles_id") != null ? rs.getInt("custom_orders_measurement_profiles_id") : null);
        dto.setCustomOrdersMeasurementProfilesCustomersId(rs.getObject("custom_orders_measurement_profiles_customers_id") != null ? rs.getInt("custom_orders_measurement_profiles_customers_id") : null);
        dto.setCustomOrdersMeasurementProfilesCustomersUsersId(rs.getObject("custom_orders_measurement_profiles_customers_users_id") != null ? rs.getInt("custom_orders_measurement_profiles_customers_users_id") : null);
        dto.setCustomizationOptionsId(rs.getObject("customization_options_id") != null ? rs.getInt("customization_options_id") : null);
        dto.setCustomizationValuesId(rs.getObject("customization_values_id") != null ? rs.getInt("customization_values_id") : null);
        dto.setCustomizationValuesCustomizationOptionsId(rs.getObject("customization_values_customization_options_id") != null ? rs.getInt("customization_values_customization_options_id") : null);
        return dto;
    }
}
