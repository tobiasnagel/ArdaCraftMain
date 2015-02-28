package me.tobi.acmain.commands;

import java.util.Random;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.Statics;
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
			if(Rasse.get(p) == Rasse.HOBBIT) {
				boolean success = false;
				ItemStack item = new ItemStack(Material.AIR);
				if(!Statics.List.colldown_thief.contains(p)) {
					Random rnd = new Random();
					Object o = Methoden.pickRandomOf(Methoden.getPlayersAround(p.getLocation(), 10));
					final Player stolen;
					if(o == null){
						p.sendMessage("§cEs ist kein Spieler nah genug um ihn zu beklauen!");
						return true;
					}else {
						stolen = (Player)o;
						if(Rasse.get(stolen) == Rasse.HOBBIT) {
							p.sendMessage("Man bestiehlt doch keine anderen Hobbits!");
							return true;
						}
						
					}
					if(rnd.nextInt(2) == 1) {
						//Diebstahl geglückt
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
						success = true;						
						p.sendMessage("§cDu hast " + stolen.getDisplayName() + " " + Methoden.normalize(item.getType().toString().toLowerCase()) + "§c geklaut!");
						Statics.List.colldown_thief.add(p);
					}else {						
						p.sendMessage("§cMist! Du wurdest erwischt! Hau besser schnell ab!");
					}
					final boolean suc = success;
					final ItemStack it = item;
					ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
						@Override
						public void run() {
							Statics.List.colldown_thief.remove(p);
							if(suc) 
								stolen.sendMessage("§c" + p.getDisplayName() + "§c hat dir " + Methoden.normalize(it.getType().toString().toLowerCase()) + "§c geklaut!");
							if(!suc)
								stolen.sendMessage("§c" + p.getDisplayName() + "§c wollte dich bestehlen! Zum Glück hast du " + p.getDisplayName() + "§c rechtzeitig bemerkt!");
						}
					}, 6000);
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
