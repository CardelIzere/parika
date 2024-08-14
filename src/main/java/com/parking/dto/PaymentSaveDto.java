package com.parking.dto;

import com.parking.model.ParkingTicket;
import com.parking.model.Payment;
import com.parking.model.Transaction;
import com.parking.model.VehiculeAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSaveDto {
	
	private Long id;
    private VehiculeAccountDto account;
    private ParkingTicketDto parkingTicket;
    
    public static PaymentSaveDto fromEntity(Payment payment) {
    	if(payment == null) {
    		return null;
    	}
    	
    	return PaymentSaveDto.builder()
    			.id(payment.getId())
    			.account(VehiculeAccountDto.fromEntity(payment.getVehiculeAccount()))
    			.parkingTicket(ParkingTicketDto.fromEntity(payment.getParkingTicket()))
    			.build();
    }
    
    public static Payment toEntity(PaymentSaveDto paymentSaveDto) {
    	if(paymentSaveDto == null) {
    		return null;
    	}
    	
    	Payment payment = new Payment();
    	payment.setId(paymentSaveDto.getId());
    	
    	// Set the transaction
        Transaction transaction = new Transaction();
        VehiculeAccount account = VehiculeAccountDto.toEntity(paymentSaveDto.getAccount());
        transaction.setAccount(account);
        payment.setTransaction(transaction);

        // Set the parking ticket
        ParkingTicket parkingTicket = ParkingTicketDto.toEntity(paymentSaveDto.getParkingTicket());
        payment.setParkingTicket(parkingTicket);

        return payment;
    }
}
