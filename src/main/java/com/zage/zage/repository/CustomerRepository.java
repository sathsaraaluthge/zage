package com.zage.zage.repository;

import com.zage.zage.dto.CustomerRequestDto;
import com.zage.zage.dto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long saveCustomer(CustomerRequestDto dto) {
        String sql = """
            INSERT INTO customers
            (first_name, last_name, email, phone, date_of_birth, gender, address_line1, address_line2, city, state, postal_code, country, profile_image, notes)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getFirstName());
            ps.setString(2, dto.getLastName());
            ps.setString(3, dto.getEmail());
            ps.setString(4, dto.getPhone());
            ps.setDate(5, dto.getDateOfBirth() != null ? Date.valueOf(dto.getDateOfBirth()) : null);
            ps.setString(6, dto.getGender());
            ps.setString(7, dto.getAddressLine1());
            ps.setString(8, dto.getAddressLine2());
            ps.setString(9, dto.getCity());
            ps.setString(10, dto.getState());
            ps.setString(11, dto.getPostalCode());
            ps.setString(12, dto.getCountry());
            ps.setString(13, dto.getProfileImage());
            ps.setString(14, dto.getNotes());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<CustomerResponseDto> findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id=?";
        List<CustomerResponseDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomerResponseDto> findAll() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public boolean existsByEmail(String email) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM customers WHERE email=?",
                Integer.class,
                email);
        return count != null && count > 0;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM customers WHERE id=?", id);
    }

    private CustomerResponseDto mapRow(ResultSet rs, int row) throws SQLException {
        return CustomerResponseDto.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .dateOfBirth(rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null)
                .gender(rs.getString("gender"))
                .addressLine1(rs.getString("address_line1"))
                .addressLine2(rs.getString("address_line2"))
                .city(rs.getString("city"))
                .state(rs.getString("state"))
                .postalCode(rs.getString("postal_code"))
                .country(rs.getString("country"))
                .profileImage(rs.getString("profile_image"))
                .notes(rs.getString("notes"))
                .status(rs.getBoolean("status"))
                .createdAt(
                        rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .updatedAt(
                        rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                .build();
    }
}
