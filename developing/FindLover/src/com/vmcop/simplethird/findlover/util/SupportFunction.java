package com.vmcop.simplethird.findlover.util;

import java.util.Random;

import com.vmcop.simplethird.findlover.constant.ConstantValue;

public class SupportFunction {
	
	// Lay ra gioi tinh cua nguoi yeu
	public static String getLoverGender(String inYourGender){
		String strLoverGender = ConstantValue.EMPTY_STRING;
		if(inYourGender.equals(ConstantValue.SEX_MALE)){
			strLoverGender = ConstantValue.SEX_FEMALE;
		} 
		else if(inYourGender.equals(ConstantValue.SEX_FEMALE)) {
			strLoverGender = ConstantValue.SEX_MALE;
		}
		return strLoverGender;
	}
	
	// Random Integer
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
