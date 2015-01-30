package me.tobi.acmain;

import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.stadt.Stadt;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class User {
	
	private String charakter;
	private Player player;
	private Rasse rasse;
	private Stadt stadt;
		
	public User(Player p) {
		
	}

	public String getCharakter() {
		return charakter;
	}

	public void setCharakter(String charakter) {
		this.charakter = charakter;
		player.setDisplayName(charakter);
	}

	public Rasse getRasse() {
		return rasse;
	}

	@SuppressWarnings("deprecation")
	public void setRasse(Rasse rasse) {
		PermissionUser user = PermissionsEx.getUser(player);
		String[] groups = {rasse.toString().toLowerCase()};
		user.setGroups(groups);
		this.rasse = rasse;
	}

	public Stadt getStadt() {
		return stadt;
	}

	public void setStadt(Stadt stadt) {
		this.stadt = stadt;
	}
}
