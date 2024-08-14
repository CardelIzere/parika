package com.parking.services;

import com.parking.dto.DepositDto;
import com.parking.dto.PaymentListDto;

public interface TransactionService {

    PaymentListDto savePayment(PaymentListDto dto);
    DepositDto saveDeposit(DepositDto dto);
    void delete(Long id);
}
