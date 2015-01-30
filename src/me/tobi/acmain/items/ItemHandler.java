package me.tobi.acmain.items;

import org.bukkit.inventory.ItemStack;

public class ItemHandler {
	
	public ItemHandler() {
		
	}
	
	public static void registerItems() {
		Rezepte.bogen(" ES", "D S", " ES");
		Rezepte.axt("EEE", "EDE", " I ");
		Rezepte.dolch("   ", " D ", " I ");
		Rezepte.stab(" D ", " S ", " S ");
		Rezepte.ring("GGG", "GDG", "GGG");
		Rezepte.schwert(" G ", " G ", " D ");
		Rezepte.armbrust("BEO", "ED ", "E  ");
		Rezepte.speer("  O", " I ", "I  ");
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
