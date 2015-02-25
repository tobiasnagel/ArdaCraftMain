package me.tobi.acmain.clicksystem;

public class Click {
	
	public enum ClickType{
		RIGHTCLICK, LEFTCLICK;
	}
	
	private ClickType clickType;
	
	public Click(ClickType type, long time) {
		
	}

	public ClickType getClickType() {
		return clickType;
	}

	public void setClickType(ClickType clickType) {
		this.clickType = clickType;
	}
	
}
