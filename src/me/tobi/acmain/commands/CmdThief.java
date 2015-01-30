package me.tobi.acmain.commands;

import java.util.Random;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdThief implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			final Player p = (Player)sender;
			if(Methoden.getRasse(p) == Rasse.HOBBIT) {
				if(!ArdaCraft.colldown_thief.contains(p)) {
					Random rnd = new Random();
					Object o = Methoden.pickRandomOf(Methoden.getPlayersAround(p.getLocation(), 10));
					Player stolen;
					if(o == null){
						p.sendMessage("§cEs ist kein Spieler nah genug um ihn zu beklauen!");
						return true;
					}else {
						stolen = (Player)o;
					}
					if(rnd.nextInt(2) == 1) {
						//Diebstahl geglückt
						ItemStack item = new ItemStack(Material.AIR);
						item = (ItemStack)Methoden.pickRandomOf(stolen.getInventory().getContents());
						String s = "";
						try{
							s = item.getType().toString().toLowerCase();
						}catch(Exception e) {
							p.sendMessage("§cMist!");
							return true;
						}
						int count = 0;
						while(s.contains("dia") || s.contains("enchant") || s.contains("anvil") || s.contains("bow")){
							item = (ItemStack)Methoden.pickRandomOf(stolen.getInventory().getContents());
							s = item.getType().toString().toLowerCase();
							count++;
							if(count > 20)
								break;
						}
						stolen.getInventory().remove(item);
						p.getInventory().addItem(item);
						stolen.sendMessage("§c" + p.getDisplayName() + "§c hat dir " + Methoden.normalize(item.getType().toString().toLowerCase()) + "§c geklaut!");
						p.sendMessage("§cDu hast " + stolen.getDisplayName() + " " + Methoden.normalize(item.getType().toString().toLowerCase()) + "§c geklaut!");
						ArdaCraft.colldown_thief.add(p);
						ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {

							@Override
							public void run() {
								ArdaCraft.colldown_thief.remove(p);
							}
							
						}, 6000);
						
					}else {
						stolen.sendMessage("§c" + p.getDisplayName() + "§c wollte dich bestehlen! Zum Glück hast du " + p.getDisplayName() + "§c rechtzeitig bemerkt!");
						p.sendMessage("§cMist! Du wurdest erwischt! Hau besser schnell ab!");
					}
				}else {
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Bitte warte noch bevor du diesen Befehl nochmal anwendest!", p);
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
