package com.zage.zage.repository;

import com.zage.zage.dto.OrderItemDto;
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
public class OrderItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemDto insertOrderItem(OrderItemDto dto) {
        String sql = "INSERT INTO order_items (quantity, price, orders_id, orders_customers_id, orders_customers_users_id, products_id, products_categories_id, product_variants_id, product_variants_products_id, product_variants_products_categories_id, product_variants_colors_id, product_variants_materials_id, product_variants_sizes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, dto.getQuantity());
            ps.setBigDecimal(2, dto.getPrice());
            ps.setObject(3, dto.getOrdersId());
            ps.setObject(4, dto.getOrdersCustomersId());
            ps.setObject(5, dto.getOrdersCustomersUsersId());
            ps.setObject(6, dto.getProductsId());
            ps.setObject(7, dto.getProductsCategoriesId());
            ps.setObject(8, dto.getProductVariantsId());
            ps.setObject(9, dto.getProductVariantsProductsId());
            ps.setObject(10, dto.getProductVariantsProductsCategoriesId());
            ps.setObject(11, dto.getProductVariantsColorsId());
            ps.setObject(12, dto.getProductVariantsMaterialsId());
            ps.setObject(13, dto.getProductVariantsSizesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<OrderItemDto> findById(Integer id) {
        String sql = "SELECT * FROM order_items WHERE id = ?";
        List<OrderItemDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<OrderItemDto> findAll() {
        String sql = "SELECT * FROM order_items";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public OrderItemDto updateOrderItem(Integer id, OrderItemDto dto) {
        String sql = "UPDATE order_items SET quantity = ?, price = ?, orders_id = ?, orders_customers_id = ?, orders_customers_users_id = ?, products_id = ?, products_categories_id = ?, product_variants_id = ?, product_variants_products_id = ?, product_variants_products_categories_id = ?, product_variants_colors_id = ?, product_variants_materials_id = ?, product_variants_sizes_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getQuantity(), dto.getPrice(), dto.getOrdersId(), dto.getOrdersCustomersId(), dto.getOrdersCustomersUsersId(), dto.getProductsId(), dto.getProductsCategoriesId(), dto.getProductVariantsId(), dto.getProductVariantsProductsId(), dto.getProductVariantsProductsCategoriesId(), dto.getProductVariantsColorsId(), dto.getProductVariantsMaterialsId(), dto.getProductVariantsSizesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteOrderItem(Integer id) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private OrderItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(rs.getInt("id"));
        dto.setQuantity(rs.getObject("quantity") != null ? rs.getInt("quantity") : null);
        dto.setPrice(rs.getBigDecimal("price"));
        dto.setOrdersId(rs.getObject("orders_id") != null ? rs.getInt("orders_id") : null);
        dto.setOrdersCustomersId(rs.getObject("orders_customers_id") != null ? rs.getInt("orders_customers_id") : null);
        dto.setOrdersCustomersUsersId(rs.getObject("orders_customers_users_id") != null ? rs.getInt("orders_customers_users_id") : null);
        dto.setProductsId(rs.getObject("products_id") != null ? rs.getInt("products_id") : null);
        dto.setProductsCategoriesId(rs.getObject("products_categories_id") != null ? rs.getInt("products_categories_id") : null);
        dto.setProductVariantsId(rs.getObject("product_variants_id") != null ? rs.getInt("product_variants_id") : null);
        dto.setProductVariantsProductsId(rs.getObject("product_variants_products_id") != null ? rs.getInt("product_variants_products_id") : null);
        dto.setProductVariantsProductsCategoriesId(rs.getObject("product_variants_products_categories_id") != null ? rs.getInt("product_variants_products_categories_id") : null);
        dto.setProductVariantsColorsId(rs.getObject("product_variants_colors_id") != null ? rs.getInt("product_variants_colors_id") : null);
        dto.setProductVariantsMaterialsId(rs.getObject("product_variants_materials_id") != null ? rs.getInt("product_variants_materials_id") : null);
        dto.setProductVariantsSizesId(rs.getObject("product_variants_sizes_id") != null ? rs.getInt("product_variants_sizes_id") : null);
        return dto;
    }
}
