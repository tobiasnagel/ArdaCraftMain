package me.tobi.acmain.commands;

import java.util.Arrays;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.stadt.Stadt;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CmdStadt implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			Inventory inv = Bukkit.createInventory(p, 4*9, "Städte");
			for(Stadt s : Stadt.values()) {
				ItemStack i = new ItemStack(Material.BANNER,1, (short)11);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(Methoden.normalize(s.toString().toLowerCase()));
				String gesinnung = s.isGood()?"Gut":"Böse";
				im.setLore(Arrays.asList("Gründer: " + s.getOwner(), "Gesinnung: " + gesinnung));
				i.setItemMeta(im);
				inv.addItem(i);
			}
			p.openInventory(inv);
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}

}
