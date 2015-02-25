package me.tobi.acmain.clicksystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.tobi.acmain.clicksystem.Click.ClickType;
import me.tobi.acmain.items.ItemType;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {
	
	public HashMap<Player, List<Click>> clicks = new HashMap<Player, List<Click>>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(Rasse.get(p) == Rasse.MAGIER && ItemType.getType(p.getInventory().getItemInHand()).equals(ItemType.STAB)) {
			ClickType clickType = event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK?ClickType.RIGHTCLICK:ClickType.LEFTCLICK;
			Click aktuelClick = new Click(clickType, new Date(System.currentTimeMillis()));
			List<Click> lastClicks = clicks.get(p);
			if(lastClicks == null) {
				lastClicks = new ArrayList<Click>();
			}
			long secondsAfter = (lastClicks.get(lastClicks.size()-1).getDate().getTime()-aktuelClick.getDate().getTime())/1000;
			if(secondsAfter > 2) {
				lastClicks = new ArrayList<Click>();
			}
			
			
		}
	}
	
}
