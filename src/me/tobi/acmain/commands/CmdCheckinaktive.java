package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.message.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCheckinaktive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean success = false;
		if(sender != null) {
			Player p = (Player)sender;
			if(p.hasPermission(Permission.CHECKINAKTIVE)) {
				if(args.length == 0) { 
					ArdaCraft.getCraftLogger().logToChat(Level.INFO, "Dieser Befehl ist noch in Arbeit!", p);
				}else {//wrong usage
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_CHECKINAKTIVE_USAGE, p);
				}
			}else { //No permission
				ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_NO_PERMISSION, p);
			}
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return success;
	}

}
