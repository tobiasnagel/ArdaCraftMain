package me.tobi.acmain.commands;

import java.util.Date;

import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.Msg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CmdPlayer implements CommandExecutor {
	
	CommandSender sender;
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		sender = s;		
		if (s.hasPermission("ardacraft.team")) {
			if (args.length == 1) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p.isOnline()){
					s("§b§lSpieler Info: §a§l" + p.getName() + "/" + p.getDisplayName());
					s("§6Health: " + h(((CraftPlayer)p).getHealth()));
					s("§6Food-Level: " + h(p.getFoodLevel()));
					s("§6Position: §7" + l(p.getLocation()));
					s("§6Nearest City: §7" + Methoden.getNearestCity(p.getLocation()));
					s("§6Rank: §7" + Methoden.getRank(p));
					s("§6Can Warp: " + b(p.hasPermission("essentials.warp")));
					s("§6Is Flying: " + b(p.isFlying()));
				}else {
					s("§b§lSpieler Info: §c§l" + p.getName());
					s("§6Banned: " + b(p.isBanned()));
					s("§6Last Seen: §7" + d(p.getLastPlayed()));
					s("§6Position: §7" + l(p.getLocation()));
				}
			} else {// wrong usage
				s(Msg.COMMAND_PLAYER_USAGE);
			}
		} else { // No permission
			s(Msg.COMMAND_NO_PERMISSION);
		}
		return true;
	}
	
	private void s(String msg) {
		sender.sendMessage(msg);
	}
	
	private String b(boolean b) {
		if(b){
			return "§atrue";
		}else {
			return "§cfalse";
		}
	}
	
	private Date d(long l) {
		return new Date(l);
	}
	
	private String l(Location l) {
		return (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();
	}
	
	private String h(double h) {
		if(h > 10){
			return "§a" + h;
		}else {
			return "§c" + h;
		}
	}

}
