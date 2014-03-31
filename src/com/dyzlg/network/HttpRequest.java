package com.dyzlg.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.dyzlg.config.Config;

import android.util.Log;

/**
 * @author larry
 * @version Created on 2014-3-15
 * 传入地址后缀，返回数据流
 */
public class HttpRequest implements Runnable{
	
	private String zUrl = Config.Network.home;
	private URL url;
	private HttpResponseListener listener;

	public HttpRequest(String url, HttpResponseListener listener) {
		super();
		StringBuilder sb = new StringBuilder();
		this.zUrl = sb.append(zUrl).append(url).toString();
		this.listener = listener;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			url = new URL(zUrl);
			Log.i("larry", "本次请求地址：" + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			conn.setConnectTimeout(8 * 1000);
			if (conn.getResponseCode() == 200) {
//				Log.d("larry", "服务器返回的内容编码格式：" + conn.getContentEncoding());
				this.listener.succeed(conn.getInputStream());
			} else {
				this.listener.failed(conn.getResponseMessage());
			}
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			this.listener.failed(e.getMessage());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			this.listener.failed(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.listener.failed(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			this.listener.failed(e.getMessage());
			e.printStackTrace();
		}
	}
}
