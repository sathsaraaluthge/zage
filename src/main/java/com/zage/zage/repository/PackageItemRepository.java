package com.zage.zage.repository;

import com.zage.zage.dto.PackageItemDto;
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
public class PackageItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public PackageItemDto insertPackageItem(PackageItemDto dto) {
        String sql = "INSERT INTO package_items (quantity, wedding_packages_id, products_id, products_categories_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, dto.getQuantity());
            ps.setObject(2, dto.getWeddingPackagesId());
            ps.setObject(3, dto.getProductsId());
            ps.setObject(4, dto.getProductsCategoriesId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<PackageItemDto> findById(Integer id) {
        String sql = "SELECT * FROM package_items WHERE id = ?";
        List<PackageItemDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<PackageItemDto> findAll() {
        String sql = "SELECT * FROM package_items";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public PackageItemDto updatePackageItem(Integer id, PackageItemDto dto) {
        String sql = "UPDATE package_items SET quantity = ?, wedding_packages_id = ?, products_id = ?, products_categories_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getQuantity(), dto.getWeddingPackagesId(), dto.getProductsId(), dto.getProductsCategoriesId(), id);
        dto.setId(id);
        return dto;
    }

    public void deletePackageItem(Integer id) {
        String sql = "DELETE FROM package_items WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private PackageItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PackageItemDto dto = new PackageItemDto();
        dto.setId(rs.getInt("id"));
        dto.setQuantity(rs.getObject("quantity") != null ? rs.getInt("quantity") : null);
        dto.setWeddingPackagesId(rs.getObject("wedding_packages_id") != null ? rs.getInt("wedding_packages_id") : null);
        dto.setProductsId(rs.getObject("products_id") != null ? rs.getInt("products_id") : null);
        dto.setProductsCategoriesId(rs.getObject("products_categories_id") != null ? rs.getInt("products_categories_id") : null);
        return dto;
    }
}
