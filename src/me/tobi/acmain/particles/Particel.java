package me.tobi.acmain.particles;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R2.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public enum Particel {
    HUGE_EXPLOSION("hugeexplosion", Environment.ANY), 
    LARGE_EXPLODE("largeexplode", Environment.ANY),
    FIREWORK_SPARK("fireworksSpark", Environment.ANY),
    TOWN_AURA("townaura", Environment.ANY),
    CRIT("crit", Environment.ANY),
    MAGIC_CRIT("magicCrit", Environment.ANY),
    SMOKE("smoke", Environment.ANY),
    MOB_SPELL("mobSpell", Environment.ANY),
    MOB_SPELL_AMBIENT("mobSpellAmbient", Environment.ANY),
    SPELL("spell", Environment.ANY),
    INSTANT_SPELL("instantSpell", Environment.ANY),
    WITCH_MAGIC("witchMagic", Environment.ANY),
    NOTE("note", Environment.ANY),
    PORTAL("portal", Environment.ANY),
    ENCHANTMENT_TABLE("enchantmenttable", Environment.ANY),
    EXPLODE("explode", Environment.ANY),
    FLAME("flame", Environment.ANY),
    LAVA("lava", Environment.ANY),
    FOOTSTEP("footstep", Environment.ANY),
    LARGE_SMOKE("largesmoke", Environment.ANY),
    CLOUD("cloud", Environment.ANY),
    RED_DUST("reddust", Environment.ANY),
    SNOWBALL_POOF("snowballpoof", Environment.ANY),
    DRIP_WATER("dripWater", Environment.ANY),
    DRIP_LAVA("dripLava", Environment.ANY),
    SNOW_SHOVEL("snowshovel", Environment.ANY),
    SLIME("slime", Environment.ANY),
    HEART("heart", Environment.ANY),
    ANGRY_VILLAGER("angryVillager", Environment.ANY),
    HAPPY_VILLAGER("happerVillager", Environment.ANY),
    ICONCRACK("iconcrack_%id%", Environment.UKNOWN), 		
    TILECRACK("tilecrack_%id%_%data%", Environment.UKNOWN),
    SPLASH("splash", Environment.AIR), 				
   	BUBBLE("bubble", Environment.IN_WATER), 		
	SUSPEND("suspend", Environment.UKNOWN), 
	DEPTH_SUSPEND("depthSuspend", Environment.UKNOWN); 	
    
    private final String packetName;
    private final Environment environment;
    
    private int x ,y, z;
    private int _id = 1;
    private int _data = 0;
    

    Particel (String packetName, Environment environment) {
    	this.packetName = packetName;
    	this.environment = environment;
    }
    public static void play(Player player,Particel pl){
    	pl.playToLocation(player.getLocation(), 1, 1);
    }
    public static void play(Location loc,Particel pl,int stärke){
    	pl.playToLocation(loc, stärke, 4F);
    }
      public void setStack (int stackXAxis, int stackYAxis, int stackZAxis) {
    	x= stackXAxis;
    	y= stackYAxis;
    	z = stackZAxis;
    }
    
     
   
    public void playToPlayer (Player player, int count, float speed) {
    	if(player == null)
    		return;
    	
    	CraftPlayer craftPlayer = (CraftPlayer) player;
    	
    	try {
			craftPlayer.getHandle().playerConnection.sendPacket(getParticle(player.getLocation(), x, y, z, speed, count));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void playToLocation (Location location, int count, float speed) {
    	if(location == null)
    		return;
    	
    	try {
    		for(Entity entity : location.getWorld().getEntities()) {
    			if(entity instanceof CraftPlayer) {
    				if(entity.getLocation().distance(location) < 333) {
	    				CraftPlayer craftPlayer = (CraftPlayer) entity;
	    				craftPlayer.getHandle().playerConnection.sendPacket(getParticle(location, x, y, z, speed, count));
    				}
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
   
    public Environment getEnvironment () {
    	return environment;
    }
    

    public enum Environment {
    	ANY,
    	AIR,
    	IN_WATER,
    	UKNOWN;
    }
    
 
	private PacketPlayOutWorldParticles getParticle (Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
		setValue(packet, "a", packetName.replace("%id%", ""+_id).replace("%data%", ""+_data));
		setValue(packet, "b", (float) location.getX());
		setValue(packet, "c", (float) location.getY());
		setValue(packet, "d", (float) location.getZ());
		setValue(packet, "e", offsetX);
		setValue(packet, "f", offsetY);
		setValue(packet, "g", offsetZ);
		setValue(packet, "h", speed);
		setValue(packet, "i", count);
		return packet;
	}
	
		private static void setValue(Object instance, String fieldName, Object value) throws Exception {
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
	}
}