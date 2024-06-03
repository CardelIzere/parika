package com.parking.projection;

import java.math.BigDecimal;

import com.parking.model.VehicleType;

public interface VehicleProjection {
	
	String getRegistrationNumber();
	VehicleType getVehicleTypeDto();
	String getAccountNumber();
	BigDecimal getSolde();
	byte[] getQrCodeImage();
	
}
