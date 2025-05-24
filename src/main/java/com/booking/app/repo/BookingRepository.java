package com.booking.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long userId);

}
