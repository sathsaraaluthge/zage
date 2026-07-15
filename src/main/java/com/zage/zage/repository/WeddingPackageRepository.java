package com.zage.zage.repository;

import com.zage.zage.dto.WeddingPackageDto;
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
public class WeddingPackageRepository {

    private final JdbcTemplate jdbcTemplate;

    public WeddingPackageDto insertWeddingPackage(WeddingPackageDto dto) {
        String sql = "INSERT INTO wedding_packages (name, price, description) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setBigDecimal(2, dto.getPrice());
            ps.setString(3, dto.getDescription());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<WeddingPackageDto> findById(Integer id) {
        String sql = "SELECT * FROM wedding_packages WHERE id = ?";
        List<WeddingPackageDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<WeddingPackageDto> findAll() {
        String sql = "SELECT * FROM wedding_packages";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public WeddingPackageDto updateWeddingPackage(Integer id, WeddingPackageDto dto) {
        String sql = "UPDATE wedding_packages SET name = ?, price = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getPrice(), dto.getDescription(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteWeddingPackage(Integer id) {
        String sql = "DELETE FROM wedding_packages WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private WeddingPackageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        WeddingPackageDto dto = new WeddingPackageDto();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("name"));
        dto.setPrice(rs.getBigDecimal("price"));
        dto.setDescription(rs.getString("description"));
        return dto;
    }
}
