package com.shopzone.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopzone.app.entity.GeneralParameter;

public interface GeneralParameterRepo extends JpaRepository<GeneralParameter, Long> {

	GeneralParameter findByParameter(String parameter);

}
