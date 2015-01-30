package lib.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
	
	private String content;
	private JSONObject config;
	private File file;
	
	public JSONReader(File f) throws IOException, JSONException {
		file = f;
		readContent();
	}
	
	private void readContent() throws JSONException, IOException {
		content = "";
		FileReader fr = new FileReader(file);
		BufferedReader br3 = new BufferedReader(fr);
		String line = br3.readLine();		
		while(line != null) {
			content = content + line;
			line = br3.readLine();
		}
		br3.close();
		createStructure();
	}
	
	private void createStructure() throws JSONException {		
		if(content == null || content.length() < 2 || (!content.startsWith("{"))) {
			config = JSONConfig.base;
		}else {
			config = new JSONObject(content);
			JSONConfig.base = config;
		}
	}
	
	public JSONObject getConfig() {
		return config;
	}
	
}
