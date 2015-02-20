package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHelp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(Rasse.get(p).equals(Rasse.UNREGISTERED)) {
				p.sendMessage(ChatColor.translateAlternateColorCodes(
						'&',"&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
								+ "&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
								+ "&2-&3-&2-&3-&2-&3-&2-"));
				p.sendMessage("                                   §6§lHilfe");
				p.sendMessage("§a§lFreischalten:");
				p.sendMessage("§6Als erstes musst du überlegen ob du &2Gut §6oder §8Böse §6sein willst!");
				p.sendMessage("§6Gib dann /gut oder /boese ein (§4§l!§r§6/b§6§loe§r§6se)");
				p.sendMessage("§6Such die dort eine Rasse aus. Wenn du mit der Maus im Chat über die Namen fährst, siehst du die "
						+ "Fähigkeit der Rasse!");
				p.sendMessage("§6Such dir jetzt einen Charakter für die Rasse aus. \"Superminer1000\" ist kein Charaktername!"
						+ "ein Charaktername muss im Herr der Ringe Stil sein. Also z.B. \"Gandalf\". Der Charaktername muss einmalig sein"
						+ "wenn ihr einen bereits vergebenen Namen verwenden wollt, wird euch ein Teammitglied sagen, dass der Name vergeben ist!"
						+ "Euer Name muss acuh zu eurer Rasse passen! Ein Ork kann nicht Gandalf heißen!");
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes(
					'&',"&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
							+ "&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-&2-&3-"
							+ "&2-&3-&2-&3-&2-&3-&2-"));
			p.sendMessage("                                   §6§lHilfe");
			p.sendMessage("§a§lBefehle:");
			p.sendMessage("§6/regeln §6| §6Zeigt dir die Regeln!"); //regeln
			p.sendMessage("§6/msg <Spieler> <Nachricht> §6| §6Sendet <Spieler> deine <Nachricht>!"); //msg
			p.sendMessage("§6/warp <Stadtname> §6| §6Teleportiert dich nach <Stadtname>!"); //warp
			
		}else {
			ArdaCraft.getCraftLogger().logToConsole(Level.WARN, Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}

}
