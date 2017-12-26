package com.geekcattle.config.utils;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileToJsonStrUtils {

	private static final String PREFIX = "META-INF/";
	
	private static Map<String,String> cachedJson = new ConcurrentHashMap<String, String>(256);

	public static String getJSONStr(String path) {
		if(path == null){
			return null;
		}
		String json = cachedJson.get(path);
		if(json != null){
			return json;
		}
		StringBuffer sb = new StringBuffer();
		InputStream input = null;
		BufferedReader r = null;
		input = ClassLoader.getSystemResourceAsStream(PREFIX+path);
		try {
			r = new BufferedReader(new InputStreamReader(input, "utf-8"));
			int lc = 1;
			while(lc > 0){
				String ln = r.readLine();
		        if (ln == null) {
		        	lc = -1;
		        	break;
		        }
		        int ci = ln.indexOf('#');
		        if (ci >= 0) {
		            ln = ln.substring(0, ci);
		        }
		        ln = ln.trim();
		        sb.append(ln);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cachedJson.putIfAbsent(path, sb.toString());
		return sb.toString();
	}

}
