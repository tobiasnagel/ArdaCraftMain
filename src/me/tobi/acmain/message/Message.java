package me.tobi.acmain.message;

import org.bukkit.ChatColor;

public class Message {
	
	private String text;
	private String hoverText = null;
	private String command = null;
	private ChatColor color = ChatColor.WHITE;
	
	public Message(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHoverText() {
		return hoverText;
	}

	public void setHoverText(String hoverText) {
		this.hoverText = hoverText;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}
	
	
	
}
