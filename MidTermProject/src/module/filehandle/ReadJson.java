package module.filehandle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJson {

	private String json = "";
	private String filepath = "";

	public ReadJson(String path) {
		this.filepath = path;
	}
	public ReadJson() {
		
	}
//use this method to convert string to JSON object
	public JsonObject getJsonObject() throws JsonSyntaxException, IOException {
		return JsonParser.parseString(readFile()).getAsJsonObject();
	}

	public String readFile() throws IOException {
		try (BufferedReader br1 = new BufferedReader(new FileReader(this.filepath));) {
			String Buffer;
			while ((Buffer = br1.readLine()) != null) {
				this.json += Buffer;
			}
			return this.json;
		}
	}
	public String readNet(String URL) throws IOException {
		URL url = new URL(URL);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
		String str = "";
		String json = "";
		while ((str = br.readLine()) != null ) {
			json+=str;
		}
		br.close();
		huc.disconnect();
		return json;
	}

	public String getPath() {
		return this.filepath;
	}

	public String getJson() {
		return this.json;
	}

}
