package com.zage.zage.repository;

import com.zage.zage.dto.OrderDto;
import com.zage.zage.enums.OrderType;
import com.zage.zage.enums.Status;
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
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderDto insertOrder(OrderDto dto) {
        String sql = "INSERT INTO orders (order_type, total_amount, status, customers_id, customers_users_id) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        if (dto.getStatus() == null) {
            dto.setStatus(Status.ACTIVE);
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getOrderType() != null ? dto.getOrderType().name() : null);
            ps.setBigDecimal(2, dto.getTotalAmount());
            ps.setString(3, dto.getStatus().name());
            ps.setObject(4, dto.getCustomersId());
            ps.setObject(5, dto.getCustomersUsersId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            dto.setId(keyHolder.getKey().intValue());
        }
        return dto;
    }

    public Optional<OrderDto> findById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        List<OrderDto> result = jdbcTemplate.query(sql, this::mapRow, id);
        return result.stream().findFirst();
    }

    public List<OrderDto> findAll() {
        String sql = "SELECT * FROM orders WHERE status = 'ACTIVE'";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public OrderDto updateOrder(Integer id, OrderDto dto) {
        String sql = "UPDATE orders SET order_type = ?, total_amount = ?, status = ?, customers_id = ?, customers_users_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getOrderType() != null ? dto.getOrderType().name() : null, dto.getTotalAmount(), dto.getStatus().name(), dto.getCustomersId(), dto.getCustomersUsersId(), id);
        dto.setId(id);
        return dto;
    }

    public void deleteOrder(Integer id) {
        String sql = "UPDATE orders SET status = 'INACTIVE' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto dto = new OrderDto();
        dto.setId(rs.getInt("id"));
        String orderTypeStr = rs.getString("order_type");
        if (orderTypeStr != null) {
            try {
                dto.setOrderType(OrderType.valueOf(orderTypeStr));
            } catch (IllegalArgumentException e) {
                dto.setOrderType(null);
            }
        }
        
        dto.setTotalAmount(rs.getBigDecimal("total_amount"));
        
        String statusStr = rs.getString("status");
        if (statusStr != null) {
            try {
                dto.setStatus(Status.valueOf(statusStr));
            } catch (IllegalArgumentException e) {
                dto.setStatus(null);
            }
        }
        
        dto.setCustomersId(rs.getObject("customers_id") != null ? rs.getInt("customers_id") : null);
        dto.setCustomersUsersId(rs.getObject("customers_users_id") != null ? rs.getInt("customers_users_id") : null);
        return dto;
    }
}
