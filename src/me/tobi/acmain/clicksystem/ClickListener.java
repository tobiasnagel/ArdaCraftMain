package me.tobi.acmain.clicksystem;

import java.util.Date;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Statics;
import me.tobi.acmain.clicksystem.ClickData.ClickType;
import me.tobi.acmain.items.ItemType;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if(Rasse.get(p) == Rasse.MAGIER && ItemType.getType(p.getInventory().getItemInHand()).equals(ItemType.STAB)) {
			ClickType clickType = event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK?ClickType.RIGHTCLICK:ClickType.LEFTCLICK;
			final ClickData playerData;
			if(!Statics.List.clickdatas.containsKey(p)) {
				Statics.List.clickdatas.put(p, new ClickData());
			}
			playerData = Statics.List.clickdatas.get(p);
			playerData.addClick(clickType, new Date(System.currentTimeMillis()));
			Bukkit.getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
				@Override
				public void run() {
					if((new Date(System.currentTimeMillis()).getTime() - playerData.lastChanged.getTime())/1000 > 1){
						Statics.List.clickdatas.remove(p);
						
					}
				}
				
			}, 20);
			
		}
		
		
	}
	
}
