package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdRegeln implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes(
					'&',"&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
							+ "&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
							+ "&2-&3-&2-&3-&2-&3-&2-"));
			sender.sendMessage("                                   §6Regeln");
			sender.sendMessage("§a§l1: §6Kein Spam");
			sender.sendMessage("§a§l2: §6Keine Spawnkills");
			sender.sendMessage("§a§l3: §6Keine Cheats/Hacks");
			sender.sendMessage("§a§l4: §6Keine Serverwerbung");
			sender.sendMessage("§a§l5: §6Keine Rassenwerbung");
			sender.sendMessage("§a§l6: §6Keine Beleidigungen");
			sender.sendMessage("§a§l7: §6Kein Diebstahl");
			sender.sendMessage("§a§l8: §6Keine Caps");
			sender.sendMessage("§a§l9: §6Möglichst nur im HDR Stil bauen");
			sender.sendMessage("§a§l10: §6Möglichst im HDR-Stil schreiben");
			sender.sendMessage("§a§l11: §6Auf Teammitglieder hören");
			sender.sendMessage("§a§l12: §6Keine Killflucht");
			sender.sendMessage("§a§l13: §6Nicht aus dem Spawn heraus angreifen");
			sender.sendMessage("§a§l14: §6Kein Damage-/Killspamming");
			sender.sendMessage("§a§l15: §6Kein Weitergeben von Wertvollen Sachen an schlechtere Spieler");
			sender.sendMessage("§a§l16: §6Der Ring darf nicht zum Kämpfen benutzt werden!");
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}

}
