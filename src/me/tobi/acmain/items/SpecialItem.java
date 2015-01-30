package me.tobi.acmain.items;

import java.util.ArrayList;
import java.util.List;

import me.tobi.acmain.Methoden;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecialItem {
	
	private ItemStack item;
	private ItemMeta meta;
	private ItemType itemType;
	public static final String TAG = "§a§lSpezialitem";
	
	public SpecialItem(ItemType itemType) {
		this.item = new ItemStack(itemType.getMaterial());
		this.meta = item.getItemMeta();
		this.itemType = itemType;
		update();
	}
	
	private void updateLore() {
		List<String> lore = new ArrayList<String>();
		lore.add(TAG);
		lore.add(itemType.toString());
		meta.setLore(lore);
	}
	private void updateName() {
		List<String> lore = new ArrayList<String>();
		lore.add(TAG);
		lore.add(itemType.toString());
		meta.setLore(lore);
	}

	public ItemStack getItem() {
		update();
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
		update();
	}

	public ItemMeta getMeta() {
		update();
		return meta;
	}

	public void setMeta(ItemMeta meta) {
		this.meta = meta;
		update();
	}
	
	public void update() {
		meta.setDisplayName(Methoden.normalize(itemType.name().toLowerCase()));
		updateLore();
		updateName();
		item.setItemMeta(meta);
	}

	public double getDamage() {
		return itemType.getDamage();
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	public ItemType getItemType() {
		return this.itemType;
	}
	
}
