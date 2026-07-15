package com.zage.zage.repository;

import com.zage.zage.dto.ProductImageDto;
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
public class ProductImageRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductImageDto insertProductImage(ProductImageDto dto) {
        String sql = "INSERT INTO product_images (image_url, type, products_id, products_categories_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getImageUrl());
            ps.setString(2, dto.getType());
            ps.setObject(3, dto.getProductsId());
            ps.setObject(4, dto.getProductsCategoriesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<ProductImageDto> findById(Integer id) {
        String sql = "SELECT * FROM product_images WHERE id = ?";
        List<ProductImageDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<ProductImageDto> findAll() {
        String sql = "SELECT * FROM product_images";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public ProductImageDto updateProductImage(Integer id, ProductImageDto dto) {
        String sql = "UPDATE product_images SET image_url = ?, type = ?, products_id = ?, products_categories_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getImageUrl(), dto.getType(), dto.getProductsId(), dto.getProductsCategoriesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteProductImage(Integer id) {
        String sql = "DELETE FROM product_images WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private ProductImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductImageDto dto = new ProductImageDto();
        dto.setId(rs.getInt("id"));
        dto.setImageUrl(rs.getString("image_url"));
        dto.setType(rs.getString("type"));
        dto.setProductsId(rs.getObject("products_id") != null ? rs.getInt("products_id") : null);
        dto.setProductsCategoriesId(rs.getObject("products_categories_id") != null ? rs.getInt("products_categories_id") : null);
        return dto;
    }
}
