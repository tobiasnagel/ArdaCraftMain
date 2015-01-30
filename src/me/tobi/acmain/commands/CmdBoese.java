package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.Message;
import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.rasse.Rasse.Attitude;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBoese implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("§7Böse Rassen:");
		int count = 0;
		for(Rasse r : Rasse.values()) {			
			if(r.getAttitude() == Attitude.BAD) {
				Message m = new Message("§a#" + String.valueOf(count+1) + " §6" + r.getName());
				m.setHoverText(r.getAbility());
				Message[] msg = {m};
				ArdaCraft.getCraftLogger().chatJSON(msg, (Player)sender);
				count++;
			}
		}
		return true;
	}

}
