package com.dyzlg.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 输入输出流转换 larry create on 2014-3-29
 */
public class IOUtil {

	/**
	 * 将流转变成字节
	 * 
	 * @param in
	 * @return
	 */
	public static byte[] convertInputStream(InputStream in) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[100];
		int len;
		try {
			while ((len = in.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	/**
	 * 将流转变成字符串
	 * 
	 * @param in
	 * @return
	 */
	public static String convertInputStreamToString(InputStream in) {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		int n;
		try {
			while ((n = in.read(b)) != -1) {
				out.append(new String(b, 0, n));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toString();
	}

}
