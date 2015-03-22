package me.tobi.acmain.commands;

import java.util.Set;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdOpen implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(p.isOp()) {
				Set<Material> set = null;
				if(p.getTargetBlock(set, 500).getType() == Material.CHEST) {
					Chest c = (Chest)p.getTargetBlock(set, 500).getState();
					p.openInventory(c.getInventory());
				}else {
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Du musst auf eine Kiste gucken!", p);
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
