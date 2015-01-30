package me.tobi.acmain.message;

import me.tobi.acmain.ArdaCraft;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CraftLogger {
	
	public CraftLogger() {}
	
	public enum Level {
		INFO, WARN, SEVERE;
	}
	
	public enum File {
		CONFIG, LOG;
	}
	
	public static final String PREFIX_INFO = "§b§l[§a§lArdaCraft§b§l]§e ";
	public static final String PREFIX_WARN = "§b§l[§a§lArdaCraft§b§l]§c ";
	public static final String PREFIX_SEVERE = "§b§l[§a§lArdaCraft§b§l]§4 ";
	
	public void logToChat(Level level, String msg) {
		if(level == Level.INFO) {
			for(Player p : ArdaCraft.getOnlinePlayers()) {
				p.sendMessage(PREFIX_INFO + msg);
			}
		}else if(level == Level.WARN) {
			for(Player p : ArdaCraft.getOnlinePlayers()) {
				p.sendMessage(PREFIX_WARN + msg);
			}
		}else if(level == Level.SEVERE) {
			for(Player p : ArdaCraft.getOnlinePlayers()) {
				p.sendMessage(PREFIX_SEVERE + msg);
			}
		}
	}
	
	public void logToChat(Level level, String msg, Player p) {
		if(level == Level.INFO) {
			p.sendMessage(PREFIX_INFO + msg);
		}else if(level == Level.WARN) {
			p.sendMessage(PREFIX_WARN + msg);
		}else if(level == Level.SEVERE) {
			p.sendMessage(PREFIX_SEVERE + msg);
		}
	}
	
	public void logToConsole(Level level, String msg) {
		if(level == Level.INFO) {
			ArdaCraft.getACServer().getLogger().info(msg);
		}else if(level == Level.WARN) {
			ArdaCraft.getACServer().getLogger().warning(msg);
		}else if(level == Level.SEVERE) {
			ArdaCraft.getACServer().getLogger().severe(msg);
		}
	}
	
	public void logToFile(Level level, String msg) {
		if (level == Level.INFO) { // INFOtoLOG

		} else if (level == Level.WARN) {// WARNtoLOG

		} else if (level == Level.SEVERE) {// SEVEREtoLOG

		}
	}

	public void writeToConfig(String key, Object value) {
		try {
			ArdaCraft.getJSONConfig().set(key, value);
			ArdaCraft.getPlugin().saveConfig();
		}catch(Exception e) {
			
		}
	}
	
	public void chatJSON(Message[] msg, Player p) {
		FancyMessage fmsg = new FancyMessage("");
		for (Message m : msg) {
			fmsg.then(m.getText() + " ");
			if(m.getColor() != null)
				fmsg.color(m.getColor());
			if (m.getHoverText() != null)
				fmsg.tooltip(m.getHoverText());
			if (m.getCommand() != null)
				fmsg.command(m.getCommand());
		}
		IChatBaseComponent comp = ChatSerializer.a(fmsg.toJSONString());
		PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public void chatJSON(Message[] msg) {
		for(Player p : ArdaCraft.getOnlinePlayers()) {
			FancyMessage fmsg = new FancyMessage("");
			for(Message m : msg) {
				fmsg.then(m.getText() + "");
				if(m.getColor() != null)
					fmsg.color(m.getColor());
				if(m.getHoverText() != null)
					fmsg.tooltip(m.getHoverText());
				if(m.getCommand() != null)
					fmsg.command(m.getCommand());
			}
			IChatBaseComponent comp = ChatSerializer.a(fmsg.toJSONString());
			PacketPlayOutChat packet = new PacketPlayOutChat(comp);
	        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);			
		}		
	}
	
	
}
