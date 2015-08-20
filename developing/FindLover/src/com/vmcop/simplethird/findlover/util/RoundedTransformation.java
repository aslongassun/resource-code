package com.vmcop.simplethird.findlover.util;

import java.io.File;

import com.vmcop.simplethird.findlover.MainActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class RoundedTransformation  implements com.squareup.picasso.Transformation {

	public RoundedTransformation() {
	
	}

	@SuppressLint("NewApi")
	@Override
	public Bitmap transform(final Bitmap source) {
	    int size = Math.min(source.getWidth(), source.getHeight());
	
	    int x = (source.getWidth() - size) / 2;
	    int y = (source.getHeight() - size) / 2;
	
	    Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);          
	    if (squaredBitmap != source) {
	        source.recycle();
	    }
	
	    Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
	
	    Canvas canvas = new Canvas(bitmap);
	
	    Paint paint = new Paint();
	    
	    
	    BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
	    paint.setShader(shader);
	    paint.setAntiAlias(true);
	    
	    float r = size/2f; 
	    canvas.drawCircle(r, r, r, paint);
	    
		squaredBitmap.recycle();
	    
	    return bitmap;
	
	}

	@Override
	public String key() {
	    return "rounded";
	}
}