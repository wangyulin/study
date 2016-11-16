package com.wyl.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wyl.httpasyncclient.HttpGetDemo;

public class CheckTrain {

	public static JSONArray data = getStationData_V2();
	public static TreeMap<String, String> fromStations = new TreeMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			this.put("VAP", "北京北"); // 北京北
			this.put("BJP", "北京"); // 北京
			this.put("VNP", "北京南"); // 北京南
			this.put("BXP", "北京西"); // 北京西
			this.put("BOP", "北京东"); // 北京东
		}
	};

	public static void main(String[] args) throws IOException {
		//work();
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<List<String>>> resultList = new ArrayList<Future<List<String>>>();
		// 创建10个任务并执行
		for (String from : fromStations.keySet()) {
			// 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
			Future<List<String>> future = executorService.submit(new TaskWithResult(from));
			// 将任务执行结果存储到List中
			resultList.add(future);
		}

		// 遍历任务的结果
		for (Future<List<String>> fs : resultList) {
			try {
				while (!fs.isDone())
					;// Future返回如果没有完成，则一直循环等待，直到Future返回完成
				System.out.println(fs.get().size()); // 打印各个线程（任务）执行的结果
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
				executorService.shutdown();
			}
		}
	}

	public static void getOneFromStationToArriveStations(String from, String fromStationName) {
		FileOutputStream fos = null; 
		FileChannel fc = null;
		try {
			fos = new FileOutputStream(new File("/Users/wangyulin/work/" + fromStationName + ".txt"));
			fc = fos.getChannel();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < data.size(); i++) {
			JSONObject one = (JSONObject) data.get(i);
			String toStation = one.getString("c");
			if (fromStations.containsKey(toStation)) {
				continue;
			}
			JSONObject resp = HttpGetDemo.getTrainTicket("2016-12-24", from, toStation);
			if(resp == null) {
				return;
			}
			JSONArray list = (JSONArray) resp.get("data");
			if(list == null) {
				return;
			}
			// 处理两地之间所有车次信息
			// 定义一个变量保存两地之间历时最小的小时数
			int minLishi = 100000;
			String temp_train = "";
			String minLishiStr = "";
			for (int e = 0; e < list.size(); e++) {
				JSONObject item = (JSONObject) ((JSONObject) list.get(e)).get("queryLeftNewDTO");
				String trainCode = item.getString("station_train_code");
				String message = item.getString("controlled_train_message");
				if (!trainCode.startsWith("K") || message.equals("列车运行图调整,暂停发售")) {
					continue;
				}
				String lishi = item.getString("lishi");
				int hour = Integer.parseInt(lishi.split(":")[0]);
				if (minLishi > hour) {
					minLishi = hour;
					temp_train = trainCode;
					minLishiStr = lishi;
				}
			}
			if (!temp_train.equals("")) {
				String str = fromStations.get(from) + ";" + one.getString("n") + ";" + temp_train + ";"
						+ minLishiStr + "\n";
				ByteBuffer bf = Charset.forName("utf8").encode(str); 
				int length = 0;  
				try {
					while ((length = fc.write(bf)) != 0) {  
					    /*  
					     * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读  
					     */  
					    System.out.println("写入长度:" + length);  
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(str);
			}
		}
	}
	
	public static void work() {
		for (String fromStation : fromStations.keySet()) {
			for (int i = 0; i < data.size(); i++) {
				JSONObject one = (JSONObject) data.get(i);
				String toStation = one.getString("c");
				if (fromStations.containsKey(toStation)) {
					continue;
				}
				JSONObject resp = HttpGetDemo.getTrainTicket("2016-12-24", fromStation, toStation);
				JSONArray list = (JSONArray) resp.get("data");
				// 处理两地之间所有车次信息
				// 定义一个变量保存两地之间历时最小的小时数
				int minLishi = 100000;
				String temp_train = "";
				String minLishiStr = "";
				for (int e = 0; e < list.size(); e++) {
					JSONObject item = (JSONObject) ((JSONObject) list.get(e)).get("queryLeftNewDTO");
					String trainCode = item.getString("station_train_code");
					String message = item.getString("controlled_train_message");
					if (!trainCode.startsWith("K") || message.equals("列车运行图调整,暂停发售")) {
						continue;
					}
					String lishi = item.getString("lishi");
					int hour = Integer.parseInt(lishi.split(":")[0]);
					if (minLishi > hour) {
						minLishi = hour;
						temp_train = trainCode;
						minLishiStr = lishi;
					}
				}
				if (!temp_train.equals("")) {
					System.out.println(fromStations.get(fromStation) + ";" + one.getString("n") + ";" + temp_train + ";"
							+ minLishiStr);
				}
			}
		}
	}
	
	//public static 

	public static JSONArray getStationData_V2() {
		if (data == null) {
			synchronized (CheckTrain.class) {
				if (data == null) {
					StringBuffer context = new StringBuffer();
					ClassLoader loader = Thread.currentThread().getContextClassLoader();
					URL url = loader.getResource("station_name.json");
					File file = new File(url.getFile());

					if (file.isFile() && file.exists()) {
						InputStreamReader read = null;
						try {
							read = new InputStreamReader(new FileInputStream(file));
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;

							while ((lineTxt = bufferedReader.readLine()) != null) {
								context.append(lineTxt);
							}
							bufferedReader.close();
							read.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						return new com.alibaba.fastjson.JSONArray();
					}

					Pattern pattern = Pattern.compile("\'(.*?)\'");
					String str = null;
					Matcher m = pattern.matcher(context.toString());
					while (m.find()) {
						str = m.group();
					}

					if (StringUtils.isNotBlank(str)) {
						str = str.substring(1, str.length() - 1);
					} else {
						return new com.alibaba.fastjson.JSONArray();
					}

					com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
					String[] array = str.split("@");
					for (String it : array) {
						if (StringUtils.isNotBlank(it)) {
							com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
							String[] info = it.split("\\|");
							json.put("n", info[1]);
							json.put("c", info[2]);
							jsonArray.add(Integer.parseInt(info[5]), json);
						}
					}
					data = jsonArray;
				}
			}
		}
		return data;
	}
}

class TaskWithResult implements Callable<List<String>> {
	private int id;
	private String from;
	private String fromStationName;

	public TaskWithResult(String from) {
		this.from = from;
		this.fromStationName = CheckTrain.fromStations.get(from);
	}

	/**
	 * 任务的具体过程，一旦任务传给ExecutorService的submit方法， 则该方法自动在一个线程上执行
	 */
	@SuppressWarnings("serial")
	public List<String> call() throws Exception {
		CheckTrain.getOneFromStationToArriveStations(this.from, this.fromStationName);
		// 该返回结果将被Future的get方法得到
		return new ArrayList<String>() {
			{
				this.add("call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName());
			}
		};
	}
}
