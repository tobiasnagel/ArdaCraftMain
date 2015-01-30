package me.tobi.acmain;

import java.util.LinkedHashMap;

import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.stadt.Stadt;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class Methoden {
	
	public static String toName(String s){
		return "";
	}
	
	public static int min(int[] i) {
		int ii = 0;
		for(int in = 0; in < i.length; in++) {
		   if(i[in] < ii) {
		      ii = i[in];
		   }
		}		
		return ii;
	}
	
	public static double min(LinkedHashMap<Double, String> m) {		
		double t = Integer.MAX_VALUE;
		for (Double d : m.keySet()) {
			if(d < t) {
				t = d;
			}
		}		
		return t;
	}
	
	public static Rasse getRasse(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		if(user.getPrefix().contains("Ork")){
			return Rasse.ORK;
		}
		if(user.getPrefix().contains("Nazgul")){
			return Rasse.NAZGUL;
		}
		if(user.getPrefix().contains("Urukhai")){
			return Rasse.URUKHAI;
		}
		if(user.getPrefix().contains("Troll")){
			return Rasse.TROLL;
		}
		if(user.getPrefix().contains("Wargreiter")){
			return Rasse.WARGREITER;
		}
		if(user.getPrefix().contains("Ostling")){
			return Rasse.OSTLING;
		}
		if(user.getPrefix().contains("Elb")){
			return Rasse.ELB;
		}
		if(user.getPrefix().contains("Mensch")){
			return Rasse.MENSCH;
		}
		if(user.getPrefix().contains("Magier")){
			return Rasse.MAGIER;
		}
		if(user.getPrefix().contains("Zwerg")){
			return Rasse.ZWERG;
		}
		if(user.getPrefix().contains("Hobbit")){
			return Rasse.HOBBIT;
		}
		if(user.getPrefix().contains("Ent")){
			return Rasse.ENT;
		}
		if(user.getPrefix().contains("Dunedain")){
			return Rasse.DUNEDAIN;
		}
		return Rasse.UNREGISTERED;
	}
	
	public static double getDamageReduced(HumanEntity entity)
    {
		double reduced = 0.0;
		try {
	        PlayerInventory inv = entity.getInventory();
	        ItemStack boots = inv.getBoots();
	        ItemStack helmet = inv.getHelmet();
	        ItemStack chest = inv.getChestplate();
	        ItemStack pants = inv.getLeggings();
	        
	        if(helmet.getType() == Material.LEATHER_HELMET)reduced = reduced + 0.04;
	        else if(helmet.getType() == Material.GOLD_HELMET)reduced = reduced + 0.08;
	        else if(helmet.getType() == Material.CHAINMAIL_HELMET)reduced = reduced + 0.08;
	        else if(helmet.getType() == Material.IRON_HELMET)reduced = reduced + 0.08;
	        else if(helmet.getType() == Material.DIAMOND_HELMET)reduced = reduced + 0.12;
	        if(boots.getType() == Material.LEATHER_BOOTS)reduced = reduced + 0.04;
	        else if(boots.getType() == Material.GOLD_BOOTS)reduced = reduced + 0.04;
	        else if(boots.getType() == Material.CHAINMAIL_BOOTS)reduced = reduced + 0.04;
	        else if(boots.getType() == Material.IRON_BOOTS)reduced = reduced + 0.08;
	        else if(boots.getType() == Material.DIAMOND_BOOTS)reduced = reduced + 0.12;
	        if(pants.getType() == Material.LEATHER_LEGGINGS)reduced = reduced + 0.08;
	        else if(pants.getType() == Material.GOLD_LEGGINGS)reduced = reduced + 0.12;
	        else if(pants.getType() == Material.CHAINMAIL_LEGGINGS)reduced = reduced + 0.16;
	        else if(pants.getType() == Material.IRON_LEGGINGS)reduced = reduced + 0.20;
	        else if(pants.getType() == Material.DIAMOND_LEGGINGS)reduced = reduced + 0.24;
	        if(chest.getType() == Material.LEATHER_CHESTPLATE)reduced = reduced + 0.12;
	        else if(chest.getType() == Material.GOLD_CHESTPLATE)reduced = reduced + 0.20;
	        else if(chest.getType() == Material.CHAINMAIL_CHESTPLATE)reduced = reduced + 0.20;
	        else if(chest.getType() == Material.IRON_CHESTPLATE)reduced = reduced + 0.24;
	        else if(chest.getType() == Material.DIAMOND_CHESTPLATE)reduced = reduced + 0.32;
		}catch(Exception e) {}
        return reduced;
    }
	
	public static String normalize(String message){
	    char newChar = message.charAt(0);
	    newChar = Character.toUpperCase(newChar);
	    message = message.replaceFirst(String.valueOf(message.charAt(0)),String.valueOf(newChar));			
	    return message;
	}
	
	public static void clearEffects(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects()){
	        p.removePotionEffect(effect.getType());
		}
	}
	
	public static String getNearestCity(Location l) {
		double min = Integer.MAX_VALUE;
		String city = "";
		for(Stadt s : Stadt.values()) {
			Location loc = s.getLoc();
			double distance = l.distance(loc);
			if(min > distance) {
				min = distance;
				city = s.getName();
			}
		}
		return city + "(" + (int)min + "m)";
	}

	public static String getRank(Player p) {
		if(p.isOp()) {
			return "Admin";
		}
		if(p.hasPermission("essentials.fly")) {
			return "Moderator";
		}
		if(p.hasPermission("mb.ban")) {
			return "Chat-Moderator";
		}
		return "Spieler";
	}
	
	/*public static HashMap<String, Location> getWarps() {
		HashMap<String, Location> warps = new HashMap<String, Location>();
		World w = Plugin.getACServer().getWorld("world");
		warps.put("DolAmrot", new Location(w, -656, 67, 778)); //inbuild_AM
		warps.put("Bree", new Location(w, -1167, 72, 15)); //inbuild_BE
		warps.put("Bruchtal", new Location(w, 1685, 80, 820)); //inbuild_BR
		warps.put("Daina", new Location(w, -623, 68, 1135)); //inbuild_DA
		warps.put("Dol-Guldur", new Location(w, 1430, 120, 4250)); //inbuild_DG
		warps.put("Doriath", new Location(w, 969, 64, 3280)); //inbuild_DO
		warps.put("Edoras", new Location(w, -472, 70, 395)); //inbuild_ED
		warps.put("Elbias", new Location(w, 1223, 85, 3432)); //inbuild_EL
		warps.put("Erebor", new Location(w, 381, 94, 3341)); //inbuild_ER
		warps.put("Galtrev", new Location(w, 157, 65, 644)); //inbuild_GA
		warps.put("Gondolin", new Location(w, 1146, 63, 4437)); //inbuild_GO
		warps.put("Gungabad", new Location(w, -2377, 68, -31)); //inbuild_GU
		warps.put("HelmsKlamm", new Location(w, -619, 79, 258)); //inbuild_HK
		warps.put("Hobbingen", new Location(w, -900, 64, 96)); //inbuild_HO
		warps.put("Isengard", new Location(w, -425, 79, 235)); //inbuild_IG
		warps.put("Lorien", new Location(w, 50, 78, -74)); //inbuild_LO
		warps.put("Moria", new Location(w, -478, 63, 44)); //inbuild_MA
		warps.put("Mordor", new Location(w, 884, 71, -223)); //inbuild_MO
		warps.put("MinasThirith", new Location(w, -58, 75, 252)); //inbuild_MT
		warps.put("Osgiliath", new Location(w, 443, 76, 55)); //inbuild_OS
		warps.put("Seredane", new Location(w, 807, 77, -33)); //inbuild_SE
		warps.put("Seestadt", new Location(w, -880, 63, 833)); //inbuild_SS
		warps.put("Wetterspitze", new Location(w, -1911, 79, 364)); //inbuild_WS
		return warps;
	}*/
	
	/*public static List<Stadt> getWarps() {
		List<Stadt> warps = new ArrayList<Stadt>();
		World w = ArdaCraft.getACServer().getWorld("world");
		warps.add(new Stadt("DolAmrot", new Location(w, -656, 67, 778), "Lord_Adrian", true)); //inbuild_AM
		warps.add(new Stadt("Bree", new Location(w, -1167, 72, 15), "Nanseneut", true)); //inbuild_BE
		warps.add(new Stadt("Bruchtal", new Location(w, 1685, 80, 820), "chrito_miner", true)); //inbuild_BR
		warps.add(new Stadt("Daina", new Location(w, -623, 68, 1135), "NoobigLP04", true)); //inbuild_DA
		warps.add(new Stadt("DolGuldur", new Location(w, 1430, 120, 4250), "Nanseneut", false)); //inbuild_DG
		warps.add(new Stadt("Doriath", new Location(w, 969, 64, 3280), "chrito_miner", true)); //inbuild_DO
		warps.add(new Stadt("Edoras", new Location(w, -472, 70, 395), "Lord_Adrian", true)); //inbuild_ED
		warps.add(new Stadt("Elbias", new Location(w, 1223, 85, 3432), "goghlo", true)); //inbuild_EL
		warps.add(new Stadt("Erebor", new Location(w, 381, 94, 3341), "Lordefe", true)); //inbuild_ER
		warps.add(new Stadt("Galtrev", new Location(w, 157, 65, 644), "pirchl", false)); //inbuild_GA
		warps.add(new Stadt("Gondolin", new Location(w, 1146, 63, 4437), "carsten97", true)); //inbuild_GO
		warps.add(new Stadt("Gundabad", new Location(w, -2377, 68, -31), "Soldier_18", false)); //inbuild_GU
		warps.add(new Stadt("HelmsKlamm", new Location(w, -619, 79, 258), "Lord_Adrian", true)); //inbuild_HK
		warps.add(new Stadt("Hobbingen", new Location(w, -900, 64, 96), "Nanseneut", true)); //inbuild_HO
		warps.add(new Stadt("Isengard", new Location(w, -425, 79, 235), "lordminecraft100", false)); //inbuild_IG
		warps.add(new Stadt("Lorien", new Location(w, 50, 78, -74),"Shine_ManHD" , true)); //inbuild_LO
		warps.add(new Stadt("Moria", new Location(w, -478, 63, 44), "Infernoart", true)); //inbuild_MA
		warps.add(new Stadt("Mordor", new Location(w, 884, 71, -223), "Nanseneut", false)); //inbuild_MO
		warps.add(new Stadt("MinasThirith", new Location(w, -58, 75, 252), "Lord_Adrian", true)); //inbuild_MT
		warps.add(new Stadt("Osgiliath", new Location(w, 443, 76, 55), "Lord_Adrian", true)); //inbuild_OS
		warps.add(new Stadt("Seredane", new Location(w, 807, 77, -33), "Jusnara", true)); //inbuild_SE
		warps.add(new Stadt("Seestadt", new Location(w, -880, 63, 833), "goghlo", true)); //inbuild_SS
		warps.add(new Stadt("Wetterspitze", new Location(w, -1911, 79, 364), "Cubecrafter09", true)); //inbuild_WS
		return warps;
	}*/
}
