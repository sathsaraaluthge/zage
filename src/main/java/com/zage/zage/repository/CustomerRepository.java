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

    public Long saveCustomer(Long userId, CustomerRequestDto dto) {
        String sql = """
            INSERT INTO customers
            (address, date_of_birth, users_id)
            VALUES (?,?,?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getAddressLine1());
            ps.setDate(2, dto.getDateOfBirth() != null ? Date.valueOf(dto.getDateOfBirth()) : null);
            ps.setLong(3, userId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<CustomerResponseDto> findById(Long id) {
        String sql = """
            SELECT c.id, c.address, c.date_of_birth, c.created_at, c.users_id,
                   u.name, u.email, u.phone, u.status
            FROM customers c
            JOIN users u ON c.users_id = u.id
            WHERE c.id=?
            """;
        List<CustomerResponseDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<CustomerResponseDto> findAll() {
        String sql = """
            SELECT c.id, c.address, c.date_of_birth, c.created_at, c.users_id,
                   u.name, u.email, u.phone, u.status
            FROM customers c
            JOIN users u ON c.users_id = u.id
            """;
        return jdbcTemplate.query(sql, this::mapRow);
    }



    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM customers WHERE id=?", id);
    }

    private CustomerResponseDto mapRow(ResultSet rs, int row) throws SQLException {
        String fullName = rs.getString("name");
        String firstName = "";
        String lastName = "";
        if (fullName != null) {
            String[] parts = fullName.split(" ", 2);
            firstName = parts[0];
            if (parts.length > 1) {
                lastName = parts[1];
            }
        }

        return CustomerResponseDto.builder()
                .id(rs.getLong("id"))
                .firstName(firstName)
                .lastName(lastName)
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .dateOfBirth(rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null)
                .addressLine1(rs.getString("address"))
                .status("ACTIVE".equalsIgnoreCase(rs.getString("status")))
                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .build();
    }
}
