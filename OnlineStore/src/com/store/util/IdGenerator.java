package com.store.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdGenerator {
	
	public static String generateGUID(){
		return new BigInteger(165, new Random()).toString(36).toUpperCase();
	}
	
	public static String generateOrderNumber(){
		Date date = new Date();
		DateFormat df= new SimpleDateFormat();
		String time = df.format(date);
		return time + System.nanoTime();
	}
}
