package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(p.isOp()) {
				if(args.length == 1) { //[0]Playername			
					Player pl = Bukkit.getPlayer(args[0]);
					if(pl.isOnline()){
						// TODO newby check
						if(ArdaCraft.muted.contains(pl)) {
							ArdaCraft.muted.remove(pl);
							ArdaCraft.getCraftLogger().logToChat(Level.INFO, "Du hast " + pl.getName() + " entmuted!", pl);
						}else {
							ArdaCraft.muted.add(pl);
							ArdaCraft.getCraftLogger().logToChat(Level.INFO, "Du hast " + pl.getName() + " gemuted!", pl);
						}
					}else {
						ArdaCraft.getCraftLogger().logToChat(Level.WARN, "§a" + args[0] + "§c ist nicht online!", p);
					}
				}else {//wring usage
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REGISTER_USAGE, p);
				}
			}else { //No permission
				ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_NO_PERMISSION, p);
			}
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}
	
}
