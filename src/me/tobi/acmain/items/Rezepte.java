package me.tobi.acmain.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class Rezepte {
	
	public static void bogen(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.BOGEN);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND);
    	RezeptElbenbogen.setIngredient('E', Material.IRON_INGOT);
    	RezeptElbenbogen.setIngredient('S', Material.STRING);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);
	}
	
	public static void axt(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.AXT);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND);
    	RezeptElbenbogen.setIngredient('E', Material.IRON_INGOT);
    	RezeptElbenbogen.setIngredient('I', Material.STICK);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);
	}
	
	public static void dolch(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.DOLCH);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND);
    	RezeptElbenbogen.setIngredient('I', Material.IRON_INGOT);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);		
	}
	
	public static void stab(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.STAB);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND);
    	RezeptElbenbogen.setIngredient('S', Material.IRON_INGOT);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);
	}
	
	public static void ring(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.RING);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND_BLOCK);
    	RezeptElbenbogen.setIngredient('G', Material.GOLD_INGOT);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);		
	}
	
	public static void schwert(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.SCHWERT);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND);
    	RezeptElbenbogen.setIngredient('G', Material.GOLD_INGOT);
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);		
	}
	
	public static void armbrust(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.ARMBRUST);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('D', Material.DIAMOND_BLOCK);
    	RezeptElbenbogen.setIngredient('E', Material.IRON_INGOT);
    	RezeptElbenbogen.setIngredient('B', Material.IRON_BLOCK);
    	RezeptElbenbogen.setIngredient('O', Material.OBSIDIAN);    	
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);
	}
	
	public static void speer(String line1, String line2, String line3) {
		SpecialItem item = new SpecialItem(ItemType.SPEER);
		ShapedRecipe RezeptElbenbogen = new ShapedRecipe(item.getItem());
    	RezeptElbenbogen.shape(line1, line2, line3);
    	RezeptElbenbogen.setIngredient('I', Material.STICK);
    	RezeptElbenbogen.setIngredient('O', Material.STONE); 	
    	Bukkit.getServer().addRecipe(RezeptElbenbogen);
	}
}
