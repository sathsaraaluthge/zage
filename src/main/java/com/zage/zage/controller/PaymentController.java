package com.zage.zage.controller;

import com.zage.zage.dto.ApiResponse;
import com.zage.zage.dto.PaymentDto;
import com.zage.zage.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentDto>> createPayment(@RequestBody PaymentDto dto) {
        PaymentDto createdPayment = paymentService.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "Payment created successfully", createdPayment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentDto>> getPaymentById(@PathVariable Integer id) {
        PaymentDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Payment fetched successfully", payment));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentDto>>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Payments fetched successfully", payments));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentDto>> updatePayment(
            @PathVariable Integer id,
            @RequestBody PaymentDto dto) {
        PaymentDto updatedPayment = paymentService.updatePayment(id, dto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Payment updated successfully", updatedPayment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), "Payment deleted successfully", null));
    }
}
