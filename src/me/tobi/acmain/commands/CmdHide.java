package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHide implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(p.isOp()) {
				if(ArdaCraft.hidden.contains(p)) {
					for(Player player : ArdaCraft.getACServer().getOnlinePlayers()) {
						player.showPlayer(p);
						ArdaCraft.hidden.remove(p);
						p.sendMessage("Du bist nun sichtbar!");
					}
				}else {
					for(Player player : ArdaCraft.getACServer().getOnlinePlayers()) {
						player.hidePlayer(p);
						ArdaCraft.hidden.add(p);
						p.sendMessage("Du bist nun unsichtbar!");
					}
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
