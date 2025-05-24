package com.booking.app.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.app.entity.GeneralParameter;

public interface GeneralParameterRepo extends JpaRepository<GeneralParameter, Long> {

	GeneralParameter findByParameter(String parameter);

}
