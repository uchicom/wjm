// (c) 2017 uchicom
package com.uchicom.wjm.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uchicom.wjm.entity.Search;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class CustomSearch {
	private String cx;
	public CustomSearch(String cx) {
		this.cx = cx;
	}

	public Search execute(String key, String search) throws UnsupportedEncodingException, IOException {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("https://www.googleapis.com/customsearch/v1?cx=")
		.append(cx)
		.append("&key=")
		.append(key)
		.append("&q=")
		.append(URLEncoder.encode(search.replaceAll(" ", "+"), "utf-8"));

		long start = System.currentTimeMillis();
		URL url = new URL(strBuff.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
//			con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
		con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
		con.setRequestProperty("User-Agent", "WJM/1.0");
		if (200 ==con.getResponseCode()) {
			System.out.println("OK:" + con.getURL());
			InputStream is = con.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024 * 4 * 10];
			int length = 0;
			while ((length = is.read(bytes)) > 0) {
				baos.write(bytes, 0, length);
			}
			String string = new String(baos.toByteArray(), "utf-8");
	//		System.out.println(string);
//			System.out.println(baos.size());
//			System.out.println("load:" + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
//			System.out.println("html:" + (System.currentTimeMillis() - start));
			is.close();
			return mapper.readValue(string, Search.class);
		} else {
			System.out.println("NG:" + con.getURL());
			return null;
		}


	}

}
