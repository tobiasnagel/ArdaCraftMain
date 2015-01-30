package me.tobi.acmain;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
	
	WARP{
		@Override
		public List<String> getPermissions() {
			List<String> permissions = new ArrayList<String>();
			permissions.add("essentials.warp.*");
			permissions.add("essentials.warp");
			permissions.add("essentials.warps.*");
			permissions.add("essentials.warps");
			permissions.add("ardacraft.team.warp");
			return permissions;
		}
	}, CMOD{
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
	};
	
	public List<String> getPermissions() {
		return null;
	}
	
}
