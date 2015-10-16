package com.vmcop.simplethird.findlover.util;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

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
	
	// Overlay bitmap2 len bitmap1 (Phai khac size cua 2 bitmap)
	public static Bitmap overlayBitmap(Bitmap bmp1, Bitmap bmp2) {
		Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp1, new Matrix(), null);
		canvas.drawBitmap(bmp2, bmp1.getWidth() - bmp2.getWidth(), 0, null);
		return bmOverlay;
	}
}
