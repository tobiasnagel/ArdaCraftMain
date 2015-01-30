package lib.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONConfig {
	
	private File file;
	private JSONReader reader;
	public static JSONObject base;
	
	public JSONConfig(File config) throws IOException, JSONException {
		reader = new JSONReader(file);
		base = reader.getConfig();
		file = config;
		if(file.exists()) {
			reader = new JSONReader(file);
		}else {
			writeToFile(file, base);
		}
	}
	
	public String getString(String path) throws IOException, JSONException {
		return base.getString(path);
	}
	
	public boolean getBoolean(String path) throws IOException, JSONException {
		return base.getBoolean(path);
	}
	
	public JSONArray getArray(String path) throws IOException, JSONException {
		return base.getJSONArray(path);
	}
	
	public JSONObject getObjct(String path) throws IOException, JSONException {
		return base.getJSONObject(path);
	}
	
	public Object get(String path) throws IOException, JSONException {
		return base.get(path);
	}
	
	public void save() throws JSONException, IOException {
		if(file.exists()) {
			writeToFile(file, base);
		}else {
			writeToFile(file, base);
		}
	}
	
	public void add(String key, Object object) throws JSONException {
		base.put(key, object);
	}
	FileWriter writer;
	public void writeToFile(File f, JSONObject structure) throws IOException {
		try{
			writer = new FileWriter(f.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(writer);
			String write = structure.toString();
			write = write.replace("{", "{\n");
			write = write.replace("[", "\n[\n");
			write = write.replace("}", "\n}");
			write = write.replace("]", "\n]");
			write = write.replace(",", ",\n");
			bw.write(write);
			bw.flush();	
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	public JSONObject getConfig() {
		return base;
	}
	
}
