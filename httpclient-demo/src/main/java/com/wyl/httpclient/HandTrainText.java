package com.wyl.httpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class HandTrainText {
	
	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		NioReader reader = new NioReader("/Users/wangyulin/work/北京.txt");
		String source = reader.getNextLine();
		String [] trains = source.split("\n");
		
		for(int i = 0; i < trains.length -1 ; i++) {
			String str = trains[i];
			if(StringUtils.isBlank(str)) {
				continue;
			}
			//System.out.println(str);
			String[] one = str.split(";");
			String key = one[2] + "-" + one[3];
			if(map.containsKey(key)) {
				System.out.println(map.get(key) + " ---- " + str);
			}
			map.put(key, str);
		}
		System.out.println(map.size());
		
		FileOutputStream fos = null; 
		FileChannel fc = null;
		try {
			fos = new FileOutputStream(new File("/Users/wangyulin/work/" + "北京发往全国各地历时最小K次车" + ".txt"));
			fc = fos.getChannel();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		for(String k : map.keySet()) {
			ByteBuffer bf = Charset.forName("utf8").encode(map.get(k) + "\n"); 
			int length = 0;  
			try {
				while ((length = fc.write(bf)) != 0) {
				    System.out.println("写入长度:" + length);  
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
