package me.tobi.acmain.items;

import org.bukkit.inventory.ItemStack;

public class ItemHandler {
	
	public ItemHandler() {
		
	}
	
	public void registerItems() {
		
	}
	
	public static boolean isSpecialItem(ItemStack i) {
		if(i.hasItemMeta()) {
			if(i.getItemMeta().hasLore()) {
				if(i.getItemMeta().getLore().get(0).contains("Spezialitem")) {
					return true;
				}
			}
			
		}		
		return false;
	}
	
	public static boolean isOldSpecialItem(ItemStack i) {
		if(i.hasItemMeta()) {
			if(i.getItemMeta().hasLore()) {
				if(i.getItemMeta().getLore().size() == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
}
