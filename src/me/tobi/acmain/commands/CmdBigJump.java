package me.tobi.acmain.commands;

import me.tobi.acmain.events.EvtHandler;
import me.tobi.acmain.message.Msg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBigJump implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null){
			Player p = (Player) sender;
			if (p.isOp()) {
				if(EvtHandler.bigjumpmode.contains(p)) {
					EvtHandler.bigjumpmode.remove(p);
				}else{
					EvtHandler.bigjumpmode.add(p);
				}
			} else { // No permission
				p.sendMessage(Msg.COMMAND_NO_PERMISSION);
			}
		}
		return true;
	}

}
