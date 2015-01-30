package me.tobi.acmain;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	Inventory menu;
	
	public Menu(String title, int zeilen) {
		Player p = Bukkit.getPlayer("BOT");
		menu = Bukkit.createInventory(p, zeilen*9, title);
	}
	
	public void show(Player p) {
		p.openInventory(menu);
	}
	
	public void set(int index, ItemStack content) {
		menu.setItem(index, content);
	}
	
	public void set(int index, String title, Material material) {
		ItemStack i = new ItemStack(material);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(title);
		i.setItemMeta(m);
		menu.setItem(index, i);
	}
	public void set(int index, String title, Material material, String[] lore) {
		ItemStack i = new ItemStack(material);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(title);
		m.setLore(Arrays.asList(lore));
		i.setItemMeta(m);
		menu.setItem(index, i);
	}
	
}
