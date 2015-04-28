package me.tobi.acmain.security;

import me.tobi.acmain.Methoden;
import me.tobi.acmain.Rank;

import org.bukkit.entity.Player;

public class SecurityCondition {
	
	private int status;
	
	public SecurityCondition() {
		set(1);
	}
	
	public int get() {
		return status;
	}
	
	public void set(int st) {
		if(st > 0 && st < 7) {
			status = st;
		}
	}
	
	public boolean isAllowed(Player p) {
		if(get() == 1) {
			return true;
		}else if(get() == 2) {
			if(Rank.get(p) == Rank.SPIELER || Rank.get(p) == Rank.CMOD || Rank.get(p) == Rank.MOD || Rank.get(p) == Rank.ADMIN) {
				return true;
			}else {
				return false;
			}
		}else if(get() == 3) {
			if(Rank.get(p) == Rank.CMOD || Rank.get(p) == Rank.MOD || Rank.get(p) == Rank.ADMIN) {
				return true;
			}else {
				if(Methoden.getWarns(p) != null) {
					if(Methoden.getWarns(p).size() <= 3) {
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
		}else if(get() == 4) {
			if(Rank.get(p) == Rank.CMOD || Rank.get(p) == Rank.MOD || Rank.get(p) == Rank.ADMIN) {
				return true;
			}else {
				return false;
			}
		}else if(get() == 5) {
			if(Rank.get(p) == Rank.MOD && Rank.get(p) == Rank.ADMIN) {
				return true;
			}else {
				return false;
			}
		}else if(get() == 6) {
			if(Rank.get(p) == Rank.ADMIN) {
				return true;
			}else {
				return false;
			}
		}
		
		return false;
	}
	
}
