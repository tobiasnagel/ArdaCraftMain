package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Rank;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSec implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(Rank.get(p) == Rank.ADMIN) {
				if(args[0].equalsIgnoreCase("addtmp")) {
					if(args.length == 4) {
						if(args[2].equalsIgnoreCase("WorldEdit")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "pex user " + args[1] + "timed add worldedit.*" + Integer.valueOf(args[3])*60);
							p.sendMessage("§b" + args[1] + "§e hat für die nächsten §b" + Integer.valueOf(args[3]) * 60 + "§e Minuten die Berechtigun §bWorldEdit §ezu benutzen!");
						}
						if(args[2].equalsIgnoreCase("Fly")) {
							p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "pex user " + args[1] + "timed add " + Integer.valueOf(args[3])*60);
							p.sendMessage("§b" + args[1] + "§e hat für die nächsten §b" + Integer.valueOf(args[3]) * 60 + "§e Minuten die Berechtigun zu §bFliegen!");
						}
					}else {
						p.sendMessage("§cUsage: /sec addtmp <Player> [WorldEdit|Fly] <Minutes>");
					}
				}
				if(args[0].equalsIgnoreCase("undoWE")) {
					if(args.length == 3) {
						Bukkit.getServer().getPlayer(args[1]).performCommand("//sundo");
						p.sendMessage("§eDu hast für §b" + args[1] + "§e §bUNDO §e ausgeführt");
					}else {
						p.sendMessage("§cUsage: /sec undoWE <Player>");
					}
				}
				if(args[0].equalsIgnoreCase("setSecCon")) {
					if(args.length == 2) {
						int st = Integer.valueOf(args[1]);
						if(st < 7 && st > 0) {
							ArdaCraft.getSecurityCondition().set(st);
							for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
								if(!ArdaCraft.getSecurityCondition().isAllowed(pl)) {
									pl.kickPlayer("Die Sicherheitsbeschränkungen wurden geändert, und du kannst leider vorerst nicht spielen!");
								}
							}
						}else {
							p.sendMessage("§cBitte gib eine Gültige Zahl von 1 bis 6 an!");
						}
					}else {
						p.sendMessage("§cUsage: /sec setSecCon <1-6>");
					}
				}
			}else {
				ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_NO_PERMISSION, p);
			}
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}

}
