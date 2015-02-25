package me.tobi.acmain.clicksystem;

import java.util.Date;

public class Click {
	
	public enum ClickType{
		RIGHTCLICK, LEFTCLICK;
	}
	
	private ClickType clickType;
	private Date date;
	
	public Click(ClickType type, Date date) {
		setDate(date);
		setClickType(type);
	}

	public ClickType getClickType() {
		return clickType;
	}

	public void setClickType(ClickType clickType) {
		this.clickType = clickType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
