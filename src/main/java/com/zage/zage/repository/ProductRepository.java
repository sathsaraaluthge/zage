package com.zage.zage.repository;

import com.zage.zage.dto.ProductDto;
import com.zage.zage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    public ProductDto registerProduct(ProductDto dto) {

        String sql = """
                INSERT INTO products
                (
                    name,
                    description,
                    product_type,
                    is_customizable,
                    selling_price,
                    rental_price,
                    status,
                    categories_id,
                    created_at
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;


        KeyHolder keyHolder = new GeneratedKeyHolder();


        LocalDateTime createdAt = LocalDateTime.now();


        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );


            ps.setString(1, dto.getName());

            ps.setString(2, dto.getDescription());

            ps.setString(
                    3,
                    dto.getProductType() != null
                            ? dto.getProductType().name()
                            : null
            );

            ps.setBoolean(
                    4,
                    Boolean.TRUE.equals(dto.getIsCustomizable())
            );

            ps.setBigDecimal(5, dto.getSellingPrice());

            ps.setBigDecimal(6, dto.getRentalPrice());

            ps.setString(
                    7,
                    dto.getStatus() != null
                            ? dto.getStatus().name()
                            : null
            );

            ps.setInt(8, dto.getCategoryId());


            ps.setTimestamp(
                    9,
                    Timestamp.valueOf(createdAt)
            );


            return ps;

        }, keyHolder);



        // set generated ID
        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }


        // set created time
        dto.setCreatedAt(createdAt);


        return dto;

    }

    public ProductDto updateProduct(Integer id, ProductDto dto) {
        String sql = """
                UPDATE products
                SET name = ?,
                    description = ?,
                    product_type = ?,
                    is_customizable = ?,
                    selling_price = ?,
                    rental_price = ?,
                    status = ?,
                    categories_id = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getProductType() != null ? dto.getProductType().name() : null,
                Boolean.TRUE.equals(dto.getIsCustomizable()),
                dto.getSellingPrice(),
                dto.getRentalPrice(),
                dto.getStatus() != null ? dto.getStatus().name() : null,
                dto.getCategoryId(),
                id
        );
        
        dto.setId(id);
        return dto;
    }

    public void deleteProduct(Integer id) {
        String sql = "UPDATE products SET status = 'INACTIVE' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<ProductDto> findById(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<ProductDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<ProductDto> searchProducts(String name, String status, Integer categoryId, 
                                           String productType, BigDecimal minPrice, BigDecimal maxPrice) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        java.util.List<Object> params = new java.util.ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
            params.add(status);
        }
        if (categoryId != null) {
            sql.append(" AND categories_id = ?");
            params.add(categoryId);
        }
        if (productType != null && !productType.isEmpty()) {
            sql.append(" AND product_type = ?");
            params.add(productType);
        }
        if (minPrice != null) {
            sql.append(" AND selling_price >= ?");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" AND selling_price <= ?");
            params.add(maxPrice);
        }

        return jdbcTemplate.query(sql.toString(), this::mapRow, params.toArray());
    }

    private ProductDto mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        ProductDto dto = new ProductDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setDescription(rs.getString("description"));
        String pType = rs.getString("product_type");
        if (pType != null) {
            dto.setProductType(com.zage.zage.enums.ProductType.valueOf(pType));
        }
        dto.setIsCustomizable(rs.getBoolean("is_customizable"));
        dto.setSellingPrice(rs.getBigDecimal("selling_price"));
        dto.setRentalPrice(rs.getBigDecimal("rental_price"));
        String st = rs.getString("status");
        if (st != null) {
            dto.setStatus(com.zage.zage.enums.Status.valueOf(st));
        }
        dto.setCategoryId(rs.getInt("categories_id"));
        java.sql.Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            dto.setCreatedAt(createdAt.toLocalDateTime());
        }
        return dto;
    }
}
