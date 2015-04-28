package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAddWarp implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(p.isOp()) {
				if(args.length == 2) {
					p.performCommand("esetwarp " + args[0]);
					p.getLocation().add(0, -1, 0).getBlock().setType(Material.EMERALD_BLOCK);
					p.getLocation().add(1, -1, 0).getBlock().setType(Material.GLOWSTONE);
					p.getLocation().add(1, -1, 1).getBlock().setType(Material.EMERALD_BLOCK);
					p.getLocation().add(0, -1, 1).getBlock().setType(Material.GLOWSTONE);
					p.getLocation().add(1, 0, 1).getBlock().setType(Material.SIGN_POST);
					p.getLocation().add(1, 0, 1).getBlock().setData((byte)8);
					Sign sign = (Sign) p.getLocation().add(1, 0, 1).getBlock().getState();
					sign.setLine(0, "[Warp]");
					sign.setLine(1, args[0]);
					sign.setLine(2, args[1]);
					sign.update();
					ArdaCraft.getCraftLogger().logToChat(Level.INFO, "Warppunkt " + args[0] + "(" + args[1] + ") erstellt!", p);
				
					
				
				}else {//wrong usage
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_ADDWARP_USAGE, p);
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
