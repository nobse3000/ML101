package com.manunitder.cn.Activity;

import com.manunitder.cn.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

public class StartPageActivity extends Activity implements Runnable{
	
	private static final int DEFAULT_MSG = 0;
	private static final int DELAY_DEFAULT_TIME = 1500;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        
        setContentView(R.layout.load);
        
        Thread thread = new Thread(this);
        thread.start();
        
    }
    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg){
    		Intent mainIntent = new Intent(StartPageActivity.this, MLListActivity.class);
    		StartPageActivity.this.startActivity(mainIntent);
    		StartPageActivity.this.finish();
    	}
    };
	public void run() {
		// TODO Auto-generated method stub
		initializeDate();
		mHandler.sendEmptyMessageDelayed(DEFAULT_MSG,DELAY_DEFAULT_TIME);
	}
	private void initializeDate() {
		// TODO Auto-generated method stub
	}
    
    
}