package me.tobi.acmain.clicksystem;

import java.util.HashMap;

import me.tobi.acmain.rasse.Rasse;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {
	
	public HashMap<Player, String> clicks = new HashMap<Player, String>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(Rasse.get(p) == Rasse.MAGIER) {
			String clicktype = event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK?"r":"l";
			String pclicks;
			if(!clicks.containsKey(p)) {
				pclicks = clicktype;
			}else {
				pclicks = clicks.get(p);
			}
			
			
			
		}
	}
	
}
