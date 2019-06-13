package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import module.filehandle.ReadJson;

public class TestJson {
	public static void main(String[] args) throws IOException {
//		String filepath = "F:\\ParkInfo.json";
//		String json = "";
//		try {
//			json = new ReadJson(filepath).readFile();
//			JsonObject jobject = JsonParser.parseString(json).getAsJsonObject();
//			JsonArray jarray = jobject.getAsJsonArray();
//			System.out.println(jarray);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String web = "https://data.tycg.gov.tw/opendata/datalist/datasetMeta/download?id=f4cc0b12-86ac-40f9-8745-885bddc18f79&rid=0daad6e6-0632-44f5-bd25-5e1de1e9146f";
//		URL url = new URL("http://download.post.gov.tw/post/download/County_h_10706.xml");
//		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
//		BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
//		String str = "";
//		StringBuffer sb = new StringBuffer();
//		while (null != ((str = br.readLine()))) {
//			sb.append(str);
//		}
//		br.close();
//		System.out.println(sb.toString());
//		br.close();
//		huc.disconnect();

		System.out.println(new ReadJson().readNet("http://download.post.gov.tw/post/download/County_h_10706.xml"));
		
		// JsonObject jobject = JsonParser.parseString(json).getAsJsonObject();
	}
}
