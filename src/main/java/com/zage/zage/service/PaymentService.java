package com.zage.zage.service;

import com.zage.zage.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto dto);
    PaymentDto getPaymentById(Integer id);
    List<PaymentDto> getAllPayments();
    PaymentDto updatePayment(Integer id, PaymentDto dto);
    void deletePayment(Integer id);
}
