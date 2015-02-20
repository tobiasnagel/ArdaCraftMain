package me.tobi.acmain.warn;

import org.bukkit.entity.Player;

public class Warn {
	
	private Player player;
	private String playerGivenBy;
	private String date;
	private int id;
	private String message;
	
	public Warn(Player p, int id, String date, String warnBy, String message) {
		this.setPlayer(p);
		this.setID(id);
		this.setDate(date);
		this.setMessage(message);
		this.setPlayerGivenBy(warnBy);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getPlayerGivenBy() {
		return playerGivenBy;
	}

	public void setPlayerGivenBy(String playerGivenBy) {
		this.playerGivenBy = playerGivenBy;
	}
	
	
}
