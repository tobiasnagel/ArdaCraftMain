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
		if(p.getUniqueId().toString() == "ae99dd1d-3bd9-4d45-800b-b9676901d823" || p.getUniqueId().toString() == "a4d4dfcf-1622-452c-9022-c80d445c68b7") {
			return Rank.ADMIN;
		}
		if(user.getSuffix().contains("C-Mod")) {
			return Rank.CMOD;
		}
		if(user.getSuffix().contains("Mod")) {
			return Rank.MOD;
		}
		if(user.getSuffix() == null) {
			return Rank.SPIELER;
		}
		return null;
	}
	
}
