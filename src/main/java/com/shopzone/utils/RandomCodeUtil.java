package com.shopzone.utils;

import java.util.Random;
import java.util.UUID;

public class RandomCodeUtil {
	
	public static String generateOrderId() {
		String orderId= "OD"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return orderId;
	}

}
