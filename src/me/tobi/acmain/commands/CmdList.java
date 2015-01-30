package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdList implements CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		for(Rasse r : Rasse.values()) {
			String online = "";
			online += r.getName();
		}
		for(Player p : ArdaCraft.getOnlinePlayers()) {
			
		}
		return true;
	}

}
