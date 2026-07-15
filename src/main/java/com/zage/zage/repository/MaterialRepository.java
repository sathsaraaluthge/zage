package com.zage.zage.repository;

import com.zage.zage.dto.MaterialDto;
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
public class MaterialRepository {

    private final JdbcTemplate jdbcTemplate;

    public MaterialDto insertMaterial(MaterialDto dto) {
        String sql = "INSERT INTO materials (name, description) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getDescription());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<MaterialDto> findById(Integer id) {
        String sql = "SELECT * FROM materials WHERE id = ?";
        List<MaterialDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<MaterialDto> findAll() {
        String sql = "SELECT * FROM materials";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public MaterialDto updateMaterial(Integer id, MaterialDto dto) {
        String sql = "UPDATE materials SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getDescription(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteMaterial(Integer id) {
        String sql = "DELETE FROM materials WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private MaterialDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MaterialDto dto = new MaterialDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setDescription(rs.getString("description"));
        return dto;
    }
}
