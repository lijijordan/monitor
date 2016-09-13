package com.monitor.device.web.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;

/**
 * @author 275636
 * @date 2015年11月10日
 */
public class HttpUtil {
	private String baseUrl;

	public HttpUtil(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String post(String json, String path) {
		HttpURLConnection conn = null;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		StringBuilder resultStr = null;
		try {
			URL url = new URL(baseUrl + path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 提交模式
			conn.setConnectTimeout(10000);// 连接超时 单位毫秒
			conn.setReadTimeout(20000);// 读取超时 单位毫秒
			conn.setDoOutput(true);// 是否输入参数
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			conn.setRequestProperty("accept", "*/*");
			printWriter = new PrintWriter(conn.getOutputStream());
			printWriter.write(json);
			printWriter.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println(" Error===" + responseCode);
			} else {
				System.out.println("Post Success!");
			}
			bufferedReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			resultStr = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				resultStr.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求异常");
		} finally {
			conn.disconnect();
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resultStr.toString();
	}

	public static String httpGet(String path) throws IOException {
		StringBuilder result = null;
		BufferedReader buffer = null;
		HttpURLConnection connet = null;
		try {
			URL url = new URL(path);
			connet = (HttpURLConnection) url.openConnection();
			connet.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8");
			connet.setRequestProperty("accept", "*/*");

			connet.connect();
			if (connet.getResponseCode() > 300) {
				System.out.println(connet.getResponseCode() + " "
						+ connet.getResponseMessage());
				throw new RuntimeException("请求异常");
			}
			buffer = new BufferedReader(new InputStreamReader(
					connet.getInputStream(), "UTF-8"));
			result = new StringBuilder();
			String line;

			while ((line = buffer.readLine()) != null) {
				// line = new String(line.getBytes(),"GBK");
				result.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException("请求异常");
		} finally {
			if (buffer != null) {
				buffer.close();
			}
			if (connet != null) {
				connet.disconnect();
			}
		}

		return result.toString();
	}

	public static void main(String[] a) throws IOException {
		String url = "http://106.185.35.79:9099/monitor-device-web/datafacade/getList/1/Temperature/Last24Hour";
		String response = httpGet(url);

		System.out.print(response);
	}
}
