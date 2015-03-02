package me.tobi.acmain;

import java.util.ArrayList;
import java.util.HashMap;

import me.tobi.acmain.clicksystem.ClickData;

import org.bukkit.entity.Player;

public class Statics {
	
	public static boolean chatmuteActive = false;
	
	public static class List {
		public static java.util.List<Player> hidden = new ArrayList<Player>();
		public static java.util.List<Player> muted = new ArrayList<Player>();
		public static java.util.List<Player> colldown_thief = new ArrayList<Player>();
		public static java.util.List<Player> shootmode = new ArrayList<Player>();	
		public static java.util.List<Player> speerShooter = new ArrayList<Player>();	
		public static java.util.List<Player> bigjumpmode = new ArrayList<Player>();	
		public static HashMap<String, Long> cooldowns = new HashMap<String, Long>();
		public static HashMap<Player, ClickData> clickdatas = new HashMap<Player, ClickData>();
	}
	
}
