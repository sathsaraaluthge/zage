package com.zage.zage.repository;

import com.zage.zage.dto.CategoryDto;
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
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDto insertCategory(CategoryDto dto) {
        String sql = """
                INSERT INTO categories (name, description, status)
                VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getDescription());
            ps.setString(3, dto.getStatus() != null ? dto.getStatus().name() : "ACTIVE");
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }

        if (dto.getStatus() == null) {
            dto.setStatus(com.zage.zage.enums.Status.ACTIVE);
        }

        return dto;
    }

    public Optional<CategoryDto> findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<CategoryDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CategoryDto> findAll() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public CategoryDto updateCategory(Integer id, CategoryDto dto) {
        String sql = """
                UPDATE categories
                SET name = ?, description = ?, status = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                dto.getName(),
                dto.getDescription(),
                dto.getStatus() != null ? dto.getStatus().name() : null,
                id
        );

        dto.setId(id);
        return dto;
    }

    public void deleteCategory(Integer id) {
        String sql = "UPDATE categories SET status = 'INACTIVE' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryDto dto = new CategoryDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setDescription(rs.getString("description"));
        String st = rs.getString("status");
        if (st != null) {
            dto.setStatus(com.zage.zage.enums.Status.valueOf(st));
        }
        return dto;
    }
}
