package com.project.billingSoftware.controller;

import com.razorpay.RazorpayException;
import com.project.billingSoftware.io.OrderResponse;
import com.project.billingSoftware.io.PaymentRequest;
import com.project.billingSoftware.io.PaymentVerificationRequest;
import com.project.billingSoftware.io.RazorpayOrderResponse;
import com.project.billingSoftware.service.OrderService;
import com.project.billingSoftware.service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RazorpayService razorpayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public RazorpayOrderResponse createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return razorpayService.createOrder(request.getAmount(), request.getCurrency());
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request) {
        return orderService.verifyPayment(request);
    }
}
