package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.charakter.CharakterRequest;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdRegister implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(p.isOp()) {
				if(args.length == 3 || args.length == 2) { //[0]Playername||[1]Rasse||[2]Charakter					
					Player pl = Bukkit.getPlayer(args[0]);
					if(pl.isOnline()){
						// TODO newby check
						if(args.length == 2) {
							if(args[1].equalsIgnoreCase("DECLINE")){
								pl.sendMessage("§6[§aCharakterrequest§6] §cDeine Anfrage wurde Abgelehnt!");
							}else {
								ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REGISTER_USAGE, p);
							}
						}else {
							CharakterRequest request = new CharakterRequest(pl, args[2], Rasse.valueOf(args[1].toUpperCase()));
							if (!request.isTaken()) {
								request.accept();
							} else {
								ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REGISTER_TAKEN, p);
							}
						}
					}else {
						ArdaCraft.getCraftLogger().logToChat(Level.WARN, "§a" + args[0] + "§c ist nicht online!", p);
					}
				}else {//wring usage
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REGISTER_USAGE, p);
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
