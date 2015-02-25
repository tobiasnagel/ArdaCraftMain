package me.tobi.acmain;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public enum Rank {
	
	CMOD{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("ardacraft.team.cmod");
			permissions.add("essentials.warp.*");
			permissions.add("essentials.warp");
			permissions.add("essentials.warps.*");
			permissions.add("essentials.warps");
			permissions.add("mb.*");
			permissions.add("essentials.ban");
			permissions.add("simplewarnings.*");
			permissions.add("essentials.unban");
			return permissions;
		}
	}, MOD{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("ardacraft.team.mod");
			permissions.add("essentials.*");
			permissions.add("mb.*");
			permissions.add("simplewarnings.*");
			return permissions;
		}
	}, ADMIN{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("*");
			return permissions;
		}
	}, OWNER{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("*");
			return permissions;
		}
	}, SPIELER{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("essentials.msg");
			return permissions;
		}
	};
	
	public List<String> getPermissions() {
		return null;
	}
	
	public static Rank get(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		if(user.getSuffix() == null) {
			
			return Rank.SPIELER;
		}
		if(user.getSuffix().contains("C-Mod")) {
			return Rank.SPIELER;
		}
		if(user.getSuffix().contains("Mod")) {
			return Rank.MOD;
		}
		if(user.getSuffix().contains("Admin")) {
			return Rank.ADMIN;
		}
		if(user.getSuffix().contains("Owner")) {
			return Rank.OWNER;
		}
		return null;
	}
	
}
