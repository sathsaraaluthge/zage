package com.zage.zage.repository;

import com.zage.zage.dto.InventoryDto;
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
public class InventoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public InventoryDto insertInventory(InventoryDto dto) {
        String sql = "INSERT INTO inventory (quantity, location, product_variants_id, product_variants_products_id, product_variants_products_categories_id, product_variants_colors_id, product_variants_materials_id, product_variants_sizes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, dto.getQuantity());
            ps.setString(2, dto.getLocation());
            ps.setObject(3, dto.getProductVariantsId());
            ps.setObject(4, dto.getProductVariantsProductsId());
            ps.setObject(5, dto.getProductVariantsProductsCategoriesId());
            ps.setObject(6, dto.getProductVariantsColorsId());
            ps.setObject(7, dto.getProductVariantsMaterialsId());
            ps.setObject(8, dto.getProductVariantsSizesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<InventoryDto> findById(Integer id) {
        String sql = "SELECT * FROM inventory WHERE id = ?";
        List<InventoryDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<InventoryDto> findAll() {
        String sql = "SELECT * FROM inventory";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public InventoryDto updateInventory(Integer id, InventoryDto dto) {
        String sql = "UPDATE inventory SET quantity = ?, location = ?, product_variants_id = ?, product_variants_products_id = ?, product_variants_products_categories_id = ?, product_variants_colors_id = ?, product_variants_materials_id = ?, product_variants_sizes_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getQuantity(), dto.getLocation(), dto.getProductVariantsId(), dto.getProductVariantsProductsId(), dto.getProductVariantsProductsCategoriesId(), dto.getProductVariantsColorsId(), dto.getProductVariantsMaterialsId(), dto.getProductVariantsSizesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteInventory(Integer id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private InventoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventoryDto dto = new InventoryDto();
        dto.setId(rs.getInt("id"));
        dto.setQuantity(rs.getObject("quantity") != null ? rs.getInt("quantity") : null);
        dto.setLocation(rs.getString("location"));
        dto.setProductVariantsId(rs.getObject("product_variants_id") != null ? rs.getInt("product_variants_id") : null);
        dto.setProductVariantsProductsId(rs.getObject("product_variants_products_id") != null ? rs.getInt("product_variants_products_id") : null);
        dto.setProductVariantsProductsCategoriesId(rs.getObject("product_variants_products_categories_id") != null ? rs.getInt("product_variants_products_categories_id") : null);
        dto.setProductVariantsColorsId(rs.getObject("product_variants_colors_id") != null ? rs.getInt("product_variants_colors_id") : null);
        dto.setProductVariantsMaterialsId(rs.getObject("product_variants_materials_id") != null ? rs.getInt("product_variants_materials_id") : null);
        dto.setProductVariantsSizesId(rs.getObject("product_variants_sizes_id") != null ? rs.getInt("product_variants_sizes_id") : null);
        return dto;
    }
}
