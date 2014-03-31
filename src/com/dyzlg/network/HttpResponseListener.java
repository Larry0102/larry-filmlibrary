package com.dyzlg.network;

import java.io.InputStream;

/**
 * @author larry
 * @version Created on 2014-3-15
 * 
 */
public interface HttpResponseListener {

	public abstract void succeed(InputStream inStream);
	
	public abstract void failed(String errMsg);
	
}
