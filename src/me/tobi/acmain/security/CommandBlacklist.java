package me.tobi.acmain.security;

import java.util.ArrayList;
import java.util.List;

public class CommandBlacklist {
	
	public static List<String> getList() {
		List<String> bl = new ArrayList<String>();
		bl.add("op");
		bl.add("stop");
		bl.add("reload");
		bl.add("restart");
		bl.add("rl"); //TODO FIX
		bl.add("sudo");
		bl.add("nuke");
		return bl;
	}
	
}
