package com.zage.zage.service.impl;

import com.zage.zage.dto.PaymentDto;
import com.zage.zage.exception.ResourceNotFoundException;
import com.zage.zage.repository.PaymentRepository;
import com.zage.zage.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto createPayment(PaymentDto dto) {
        return paymentRepository.insertPayment(dto);
    }

    @Override
    public PaymentDto getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Payment not found")
        );
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentDto updatePayment(Integer id, PaymentDto dto) {
        getPaymentById(id);
        return paymentRepository.updatePayment(id, dto);
    }

    @Override
    public void deletePayment(Integer id) {
        getPaymentById(id);
        paymentRepository.deletePayment(id);
    }
}
