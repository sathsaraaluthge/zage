package com.zage.zage.repository;

import com.zage.zage.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentDto insertPayment(PaymentDto dto) {
        String sql = "INSERT INTO payments (amount, method, payment_date, orders_id, orders_customers_id, orders_customers_users_id) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (dto.getPaymentDate() == null) {
            dto.setPaymentDate(LocalDateTime.now());
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, dto.getAmount());
            ps.setString(2, dto.getMethod());
            ps.setTimestamp(3, Timestamp.valueOf(dto.getPaymentDate()));
            ps.setObject(4, dto.getOrdersId());
            ps.setObject(5, dto.getOrdersCustomersId());
            ps.setObject(6, dto.getOrdersCustomersUsersId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<PaymentDto> findById(Integer id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        List<PaymentDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<PaymentDto> findAll() {
        String sql = "SELECT * FROM payments";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public PaymentDto updatePayment(Integer id, PaymentDto dto) {
        String sql = "UPDATE payments SET amount = ?, method = ?, payment_date = ?, orders_id = ?, orders_customers_id = ?, orders_customers_users_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getAmount(), dto.getMethod(), dto.getPaymentDate() != null ? Timestamp.valueOf(dto.getPaymentDate()) : null, dto.getOrdersId(), dto.getOrdersCustomersId(), dto.getOrdersCustomersUsersId(), id);
        dto.setId(id);
        return dto;
    }

    public void deletePayment(Integer id) {
        String sql = "DELETE FROM payments WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private PaymentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentDto dto = new PaymentDto();
        dto.setId(rs.getInt("id"));
        dto.setAmount(rs.getBigDecimal("amount"));
        dto.setMethod(rs.getString("method"));
        
        Timestamp timestamp = rs.getTimestamp("payment_date");
        if (timestamp != null) {
            dto.setPaymentDate(timestamp.toLocalDateTime());
        }
        
        dto.setOrdersId(rs.getObject("orders_id") != null ? rs.getInt("orders_id") : null);
        dto.setOrdersCustomersId(rs.getObject("orders_customers_id") != null ? rs.getInt("orders_customers_id") : null);
        dto.setOrdersCustomersUsersId(rs.getObject("orders_customers_users_id") != null ? rs.getInt("orders_customers_users_id") : null);
        return dto;
    }
}
