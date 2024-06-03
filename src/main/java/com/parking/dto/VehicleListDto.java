package com.parking.dto;

import java.math.BigDecimal;

import com.parking.projection.VehicleProjection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleListDto {
	
	private Long idVehicle;
	private String registrationNumber;
	private VehicleTypeDto vehicleTypeDto;
	private String accountNumber;
	private BigDecimal solde;
	private byte[] qrCodeImage;
	
	public static VehicleListDto fromEntity(VehicleProjection vehicleProjection) {
		if(vehicleProjection == null) {
			return null;
		}
		
		return VehicleListDto.builder()
				.idVehicle(vehicleProjection.getIdVehicle())
				.registrationNumber(vehicleProjection.getRegistrationNumber())
				.vehicleTypeDto(VehicleTypeDto.fromEntity(vehicleProjection.getVehicleTypeDto()))
				.accountNumber(vehicleProjection.getAccountNumber())
				.solde(vehicleProjection.getSolde())
				.qrCodeImage(vehicleProjection.getQrCodeImage())
				.build();
	}
	
}
