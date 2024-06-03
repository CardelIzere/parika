package com.parking.repository;

import java.util.Optional;

import com.parking.model.VehicleType;
import com.parking.projection.VehicleProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "select v from Vehicle v where v.id = :id")
    Vehicle findVehicleById(@Param("id") Long id);

    @Query(value = "select ve from Vehicle ve order by ve.id desc")
    Page<Vehicle> findAllVehicle(Pageable pageable);

    @Query(value = "select ve from Vehicle ve where ve.registrationNumber = :number")
    Optional<Vehicle> findVehicleByRegistrationNumber(@Param("number") String number);

    @Query(value = "select ve from Vehicle ve join VehicleType vt on ve.vehicleType.id=vt.id where UPPER(ve.registrationNumber) like CONCAT('%',UPPER(?1),'%' ) OR UPPER(vt.vehiculeTypeName) like CONCAT('%',UPPER(?1),'%' ) order by ve.id desc ")
    Page<Vehicle> findByVehiculeRegistrationNumberLike(String search, Pageable pageable);
    
    @Query("SELECT v.registrationNumber AS registrationNumber, v.vehicleType AS vehicleTypeDto, va.accountNumber AS accountNumber, " +
    		"COALESCE(SUM(t.transactionAmount), 0) AS solde, va.qrCodeImage AS qrCodeImage " +
    		"FROM Vehicle v " +
    		"JOIN v.vehicleType vt " +
    		"JOIN v.account va " +
    		"LEFT JOIN va.transactions t " +
    		"WHERE UPPER(v.registrationNumber) like CONCAT('%', UPPER(:search),'%') AND UPPER(vt.vehiculeTypeName) like CONCAT('%', UPPER(:search), '%') AND UPPER(va.accountNumber) like CONCAT('%', UPPER(:search), '%') " +
    		"GROUP BY v.registrationNumber, vt, va.accountNumber, va.qrCodeImage")
    Page<VehicleProjection> findVehicleDetailsWithSearch(String search, Pageable pageable);
    
    @Query("SELECT v.registrationNumber AS registrationNumber, v.vehicleType AS vehicleTypeDto, va.accountNumber AS accountNumber, " +
    		"COALESCE(SUM(t.transactionAmount), 0) AS solde, va.qrCodeImage AS qrCodeImage " +
    		"FROM Vehicle v " +
    		"JOIN v.vehicleType vt " +
    		"JOIN v.account va " +
    		"LEFT JOIN va.transactions t " +
    		"GROUP BY v.registrationNumber, vt, va.accountNumber, va.qrCodeImage")
    Page<VehicleProjection> findVehicleDetails(Pageable pageable);
    
}
