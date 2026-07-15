package com.zage.zage.repository;

import com.zage.zage.dto.ProductVariantDto;
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
public class ProductVariantRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductVariantDto insertProductVariant(ProductVariantDto dto) {
        String sql = "INSERT INTO product_variants (sku, price, stock_qty, products_id, products_categories_id, colors_id, materials_id, sizes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getSku());
            ps.setBigDecimal(2, dto.getPrice());
            ps.setBigDecimal(3, dto.getStockQty());
            ps.setObject(4, dto.getProductsId());
            ps.setObject(5, dto.getProductsCategoriesId());
            ps.setObject(6, dto.getColorsId());
            ps.setObject(7, dto.getMaterialsId());
            ps.setObject(8, dto.getSizesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<ProductVariantDto> findById(Integer id) {
        String sql = "SELECT * FROM product_variants WHERE id = ?";
        List<ProductVariantDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<ProductVariantDto> findAll() {
        String sql = "SELECT * FROM product_variants";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public ProductVariantDto updateProductVariant(Integer id, ProductVariantDto dto) {
        String sql = "UPDATE product_variants SET sku = ?, price = ?, stock_qty = ?, products_id = ?, products_categories_id = ?, colors_id = ?, materials_id = ?, sizes_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getSku(), dto.getPrice(), dto.getStockQty(), dto.getProductsId(), dto.getProductsCategoriesId(), dto.getColorsId(), dto.getMaterialsId(), dto.getSizesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteProductVariant(Integer id) {
        String sql = "DELETE FROM product_variants WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private ProductVariantDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductVariantDto dto = new ProductVariantDto();
        dto.setId(rs.getInt("id"));
        dto.setSku(rs.getString("sku"));
        dto.setPrice(rs.getBigDecimal("price"));
        dto.setStockQty(rs.getBigDecimal("stock_qty"));
        dto.setProductsId(rs.getObject("products_id") != null ? rs.getInt("products_id") : null);
        dto.setProductsCategoriesId(rs.getObject("products_categories_id") != null ? rs.getInt("products_categories_id") : null);
        dto.setColorsId(rs.getObject("colors_id") != null ? rs.getInt("colors_id") : null);
        dto.setMaterialsId(rs.getObject("materials_id") != null ? rs.getInt("materials_id") : null);
        dto.setSizesId(rs.getObject("sizes_id") != null ? rs.getInt("sizes_id") : null);
        return dto;
    }
}
