package com.zage.zage.repository;

import com.zage.zage.dto.ProductionOrderDto;
import com.zage.zage.enums.Stage;
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
public class ProductionOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductionOrderDto insertProductionOrder(ProductionOrderDto dto) {
        String sql = "INSERT INTO production_orders (stage, status, custom_orders_id, custom_orders_products_id, custom_orders_products_categories_id, custom_orders_measurement_profiles_id, custom_orders_measurement_profiles_customers_id, custom_orders_measurement_profiles_customers_users_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (dto.getStatus() == null) {
            dto.setStatus(Status.ACTIVE);
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getStage() != null ? dto.getStage().name() : null);
            ps.setString(2, dto.getStatus().name());
            ps.setObject(3, dto.getCustomOrdersId());
            ps.setObject(4, dto.getCustomOrdersProductsId());
            ps.setObject(5, dto.getCustomOrdersProductsCategoriesId());
            ps.setObject(6, dto.getCustomOrdersMeasurementProfilesId());
            ps.setObject(7, dto.getCustomOrdersMeasurementProfilesCustomersId());
            ps.setObject(8, dto.getCustomOrdersMeasurementProfilesCustomersUsersId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<ProductionOrderDto> findById(Integer id) {
        String sql = "SELECT * FROM production_orders WHERE id = ?";
        List<ProductionOrderDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<ProductionOrderDto> findAll() {
        String sql = "SELECT * FROM production_orders WHERE status = 'ACTIVE'";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public ProductionOrderDto updateProductionOrder(Integer id, ProductionOrderDto dto) {
        String sql = "UPDATE production_orders SET stage = ?, status = ?, custom_orders_id = ?, custom_orders_products_id = ?, custom_orders_products_categories_id = ?, custom_orders_measurement_profiles_id = ?, custom_orders_measurement_profiles_customers_id = ?, custom_orders_measurement_profiles_customers_users_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getStage() != null ? dto.getStage().name() : null, dto.getStatus().name(), dto.getCustomOrdersId(), dto.getCustomOrdersProductsId(), dto.getCustomOrdersProductsCategoriesId(), dto.getCustomOrdersMeasurementProfilesId(), dto.getCustomOrdersMeasurementProfilesCustomersId(), dto.getCustomOrdersMeasurementProfilesCustomersUsersId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteProductionOrder(Integer id) {
        String sql = "UPDATE production_orders SET status = 'INACTIVE' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private ProductionOrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductionOrderDto dto = new ProductionOrderDto();
        dto.setId(rs.getInt("id"));
        String stageStr = rs.getString("stage");
        if (stageStr != null) {
            try {
                dto.setStage(Stage.valueOf(stageStr));
            } catch (IllegalArgumentException e) {
                dto.setStage(null);
            }
        }
        
        String statusStr = rs.getString("status");
        if (statusStr != null) {
            try {
                dto.setStatus(Status.valueOf(statusStr));
            } catch (IllegalArgumentException e) {
                dto.setStatus(null);
            }
        }
        
        dto.setCustomOrdersId(rs.getObject("custom_orders_id") != null ? rs.getInt("custom_orders_id") : null);
        dto.setCustomOrdersProductsId(rs.getObject("custom_orders_products_id") != null ? rs.getInt("custom_orders_products_id") : null);
        dto.setCustomOrdersProductsCategoriesId(rs.getObject("custom_orders_products_categories_id") != null ? rs.getInt("custom_orders_products_categories_id") : null);
        dto.setCustomOrdersMeasurementProfilesId(rs.getObject("custom_orders_measurement_profiles_id") != null ? rs.getInt("custom_orders_measurement_profiles_id") : null);
        dto.setCustomOrdersMeasurementProfilesCustomersId(rs.getObject("custom_orders_measurement_profiles_customers_id") != null ? rs.getInt("custom_orders_measurement_profiles_customers_id") : null);
        dto.setCustomOrdersMeasurementProfilesCustomersUsersId(rs.getObject("custom_orders_measurement_profiles_customers_users_id") != null ? rs.getInt("custom_orders_measurement_profiles_customers_users_id") : null);
        return dto;
    }
}
