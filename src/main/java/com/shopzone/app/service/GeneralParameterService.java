package com.shopzone.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopzone.app.entity.GeneralParameter;

@Service
public class GeneralParameterService {
	
	@Autowired
	GeneralParameterRepo repo;

	public String fetchParameterValue(String parameter) {
		GeneralParameter generalParameter= repo.findByParameter(parameter);
		return generalParameter.getValue();
	}
}
