package com.manunitder.cn.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader {

	private URL url = null;
	
	/*
	 * 从URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件中的内容
	 * 1.创建一个URL对象
	 * 2.通过URL对象，创建一个HttpURLConnection的对象
	 * 3.得到InputStream
	 * 4.从InputStream中读取数据
	 * @param strUrl
	 * @return
	 */
	public String download(String strUrl){
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			//创建一个URL对象
			URL url = new URL(strUrl);
			//创建一个Http链接
			HttpURLConnection urlConn = (HttpURLConnection)url
				.openConnection();
			//使用IO流读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	/*
	 * 该函数返回整型
	 * -1：代表下载文件出错
	 * 0：代表下载成功
	 * 1：代表文件已经存在
	 */
	public int downFile(String strUrl, String path, String fileName){
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils();
			if (fileUtils.isFileExist(fileName, path)) {
				return 1;
			} else {
				inputStream = getInputStreamFromUrl(strUrl);
				File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
				if (resultFile == null) {
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	
	/**
	 * 根据URL得到输入流
	 * 
	 * @param urlStr
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public InputStream getInputStreamFromUrl(String strUrl)
			throws MalformedURLException, IOException {
		url = new URL(strUrl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}
	
}