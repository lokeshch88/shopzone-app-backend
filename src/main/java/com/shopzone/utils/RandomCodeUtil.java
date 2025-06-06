package com.shopzone.utils;

import java.util.Random;
import java.util.UUID;

public class RandomCodeUtil {
	
	public static String generateOrderId() {
		String orderId= "OD"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return orderId;
	}
	
	public static String generateTransactionId() {
		//return String.valueOf(Math.abs(new Random().nextLong())).substring(0, 10);

		return String.format("%010d", new Random().nextInt(1_000_000_000));

	}

}
