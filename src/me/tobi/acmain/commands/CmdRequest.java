package me.tobi.acmain.commands;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.charakter.CharakterRequest;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdRequest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender != null) {
			Player p = (Player)sender;
			if(args.length == 2) {
				try{
					Rasse r = Rasse.valueOf(args[0].toUpperCase());
					String c = args[1];
					if(!(c.contains("1") || c.contains("2") || c.contains("3") || c.contains("4") || 
							c.contains("5") || c.contains("6") || c.contains("7") || c.contains("8") || 
									c.contains("9") || c.contains("0"))){
						CharakterRequest request = new CharakterRequest(p, args[1], r);
						if(!request.isTaken()){
							request.sendToOperators();
						}else {
							ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REQUEST_CHARAKTERTAKEN, p);
						}							
					}else {
						ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REQUEST_INVALIDNAME, p);
					}
				}catch(Exception e) {
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REQUEST_INVALIDRACE, p);
				}
			}else {
				ArdaCraft.getCraftLogger().logToChat(Level.WARN, Msg.COMMAND_REQUEST_USAGE, p);
			}
		}else {
			System.out.println(Msg.COMMAND_PLAYER_ONLY);
		}
		return true;
	}

}
