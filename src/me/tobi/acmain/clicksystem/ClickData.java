package me.tobi.acmain.clicksystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClickData {
	
	public enum ClickType{
		RIGHTCLICK, LEFTCLICK;
	}
	
	public class Click{
		
		ClickType type;
		Date date;
		
		public Click(ClickType type, Date date) {
			this.type = type;
			this.date = date;
		}
	}
	
	Date lastChanged;
	
	
	private List<Click> clicks;
	
	public ClickData() {
		clicks = new ArrayList<Click>();
	}

	public List<Click> getClicks() {
		return clicks;
	}

	public void addClick(ClickType type, Date date) {
		clicks.add(new Click(type, date));
		lastChanged = date;
	}
	
}
