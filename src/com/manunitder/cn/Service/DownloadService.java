package com.manunitder.cn.Service;

import com.manunitder.cn.Model.MLInfo;
import com.manunitder.cn.Utils.HttpDownloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service{

	private static final String strURL = "http://localhost:8080/ml101/";
	private static final String strFileExt = ".png";
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		MLInfo mlInfo = (MLInfo)intent .getSerializableExtra("mlInfo");
		System.out.println("mlInfo---->" + mlInfo);
		DownloadThread downloadThread = new DownloadThread(mlInfo);
		Thread thread = new Thread(downloadThread);
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}

	class DownloadThread implements Runnable{
		private MLInfo mlInfo = null;
		public DownloadThread(MLInfo mlInfo){
			this.mlInfo = mlInfo;
		}
		public void run() {
			// TODO Auto-generated method stub
			//下载地址http://localhost:8080/ml101/
			String fileName = mlInfo.getMlName() + strFileExt;
			String mlUrl = strURL + fileName;
			HttpDownloader httpDownloader = new HttpDownloader();
			int result = httpDownloader.downFile(mlUrl, "ml101/", fileName);
		}
	}
}
