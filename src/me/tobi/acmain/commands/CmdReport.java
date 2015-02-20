package me.tobi.acmain.commands;

import java.util.List;

import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.warn.Warn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReport implements CommandExecutor {
	
	CommandSender sender;
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		sender = s;		
		if (s.hasPermission("ardacraft.team")) {
			if (args.length == 1) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p.isOnline()){
					s("§a§l==================§c§lPlayer Info§a§l=================");
					s("§6Namen: " + n(Methoden.getNames(p)));
					s("§6Charaktername: §b" + p.getDisplayName());
					if(Methoden.getWarns(p) == null){
						s("§6Verwarnungen: §aKeine");
					}else{
						s("§6Verwarnungen:");
						for(Warn w : Methoden.getWarns(p)) {
							s("     §a[§b§l" + w.getID() + "§c: " + w.getDate() + "§a] §b" + w.getMessage() + " §a(§cvon " + w.getPlayerGivenBy() + "§a)");
						}
					}
				}else {
					s("Der Spieler muss online sein!");
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
	
	@SuppressWarnings("unused")
	private String b(boolean b) {
		if(b){
			return "§atrue";
		}else {
			return "§cfalse";
		}
	}
	
	@SuppressWarnings("unused")
	private String l(Location l) {
		return (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();
	}
	
	@SuppressWarnings("unused")
	private String h(double h) {
		if(h > 10){
			return "§a" + h;
		}else {
			return "§c" + h;
		}
	}
	
	private String n(List<String> namen) {
		if(namen.size() == 1) return "§b" + namen.get(0);		
		String rtn = "§b" + namen.get(0);		
		for(int i = 1; i < namen.size(); i++) {
			rtn += "§a, §b" + namen.get(i);
		}
		return rtn;
	}

}
