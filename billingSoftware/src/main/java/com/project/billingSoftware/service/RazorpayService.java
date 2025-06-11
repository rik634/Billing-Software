package com.project.billingSoftware.service;

import com.razorpay.RazorpayException;
import com.project.billingSoftware.io.RazorpayOrderResponse;

public interface RazorpayService {

    RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;
}
