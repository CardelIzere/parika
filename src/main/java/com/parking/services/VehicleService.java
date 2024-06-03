package com.parking.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.parking.dto.VehicleDto;
import com.parking.dto.VehicleListDto;

public interface VehicleService {

    VehicleDto save(VehicleDto dto);

    VehicleDto findById(Long id);

    Page<VehicleDto> findByVehicleRegistrationNumber(String search, Pageable pageable);
    
    Page<VehicleListDto> getVehicleDetails(String search, Pageable pageable);

    void delete(Long id);
}
