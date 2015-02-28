package me.tobi.acmain.commands;

import me.tobi.acmain.Statics;
import me.tobi.acmain.message.Msg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdShoot implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null){
			Player p = (Player) sender;
			if (p.isOp()) {
				if(Statics.List.shootmode.contains(p)) {
					Statics.List.shootmode.remove(p);
					p.sendMessage("ßaDu Schieﬂt nicht mehr!");
				}else{
					Statics.List.shootmode.add(p);
					p.sendMessage("ßaDu Schieﬂt jetzt!");
				}
			} else { // No permission
				p.sendMessage(Msg.COMMAND_NO_PERMISSION);
			}
		}
		return true;
	}
}
