package me.tobi.acmain.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.warn.Warn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.ViolationHistory;
import fr.neatmonster.nocheatplus.checks.ViolationHistory.ViolationLevel;

public class CmdReport implements CommandExecutor {
	
	CommandSender sender;
	
	@SuppressWarnings("resource")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		sender = s;		
		if (s.hasPermission("ardacraft.team")) {
			if (args.length == 1) {
				Player p = Bukkit.getPlayer(args[0]);
				if(p.isOnline()){
					s("§a§l==================§c§lPlayer Info§a§l=================");
					s("§6Namen: " + n(Methoden.getNames(p)));
					s("§6Charaktername: §b" + p.getDisplayName());
					sendWarnings(p);
					sendNCP(p);
					
				}else {
					s("Der Spieler muss online sein!");
				}
			} if(args.length == 2) { //TODO FIX
				if(args[1].equalsIgnoreCase("file")) {
					Player target = Bukkit.getPlayer(args[0]);
					if(target.isOnline()){
						try{
						    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
						    DateFormat dateFormatExt = new SimpleDateFormat("dd-MM-yyy, HH:mm:ss");
						    String date = dateFormat.format(new Date(System.currentTimeMillis()));
						    String dateExt = dateFormatExt.format(new Date(System.currentTimeMillis()));
							File file = new File(ArdaCraft.getPlugin().getDataFolder()+ "\\reports\\" + args[0] + "-" + date + ".txt");
							file.createNewFile();
							System.out.println(file.getPath());
							PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
							writer.println("Spieler Bericht: " + target.getName());
							writer.println("Aktuelle Uhrzeit: " + dateExt);
							writer.println("Charaktername: " + target.getDisplayName());
							writer.println("");
							if(Methoden.getWarns(target) == null){
								writer.println("Verwarnungen: Keine");
							}else{
								writer.println("Verwarnungen:");
								for(Warn w : Methoden.getWarns(target)) {
									writer.println(" [" + w.getID() + ": " + w.getDate() + "] " + w.getMessage() );
								}
							}
							writer.println("");
							
							ViolationHistory history = ViolationHistory.getHistory(target, false);
							if(history == null) {
								writer.println("NoCheatPlus Einträge: Keine");
								return true;
							}else {
								writer.println("NoCheatPlus Einträge: ");
							}
							ViolationHistory.ViolationLevel[] violations = history.getViolationLevels();
						    DateFormat df = new SimpleDateFormat("HH:mm:ss");
							for(ViolationLevel level : violations) {
								long time = level.time;
						        String[] parts = level.check.split("\\.");
						        String check = parts[(parts.length - 1)].toLowerCase();
						        String parent = parts[(parts.length - 2)].toLowerCase();
						        long sumVL = Math.round(level.sumVL);
						        if(sumVL > 10)
						        writer.println(" [" + df.format(new Date(time)) + "] " + parent + "." + check + " VL " + sumVL);
							}
							
							writer.flush();
							writer.close();
						}catch(Exception e) {
							System.out.println("ERROR");
							e.printStackTrace();
						}
					}else {
						System.out.println("FALSE");
					}
				}
			}else {// wrong usage
				s(Msg.COMMAND_PLAYER_USAGE);
			}
		} else { // No permission
			s(Msg.COMMAND_NO_PERMISSION);
		}
		return true;
	}
	
	private void sendWarnings(Player p) {
		if(Methoden.getWarns(p) == null){
			s("§6Verwarnungen: §aKeine");
		}else{
			s("§6Verwarnungen:");
			for(Warn w : Methoden.getWarns(p)) {
				s(" §a[§b§l" + w.getID() + "§c: " + w.getDate() + "§a] §b" + w.getMessage() ); //+ " §a(§cvon " + w.getPlayerGivenBy() + "§a)"
			}
		}
	}
	
	private void sendNCP(Player p) {
		ViolationHistory history = ViolationHistory.getHistory(p, false);
		if(history == null) {
			s("§6NoCheatPlus Einträge: §aKeine");
			return;
		}else {
			s("§6NoCheatPlus Einträge: ");
		}
		ViolationHistory.ViolationLevel[] violations = history.getViolationLevels();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		for(ViolationLevel level : violations) {
			long time = level.time;
	        String[] parts = level.check.split("\\.");
	        String check = parts[(parts.length - 1)].toLowerCase();
	        String parent = parts[(parts.length - 2)].toLowerCase();
	        long sumVL = Math.round(level.sumVL);
	        if(sumVL > 10)
	        s(" §a[§c" + dateFormat.format(new Date(time)) + "§a]§b§l " + parent + "." + check + "§c§l VL " + sumVL);
		}
	}
	
	
	
	private void s(String msg) {
		sender.sendMessage(msg);
	}
	
	@SuppressWarnings("unused")
	private String b(boolean b) {
		if(b){
			return "§atrue";
		}else {
			return "§cfalse";
		}
	}
	
	@SuppressWarnings("unused")
	private String l(Location l) {
		return (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();
	}
	
	@SuppressWarnings("unused")
	private String h(double h) {
		if(h > 10){
			return "§a" + h;
		}else {
			return "§c" + h;
		}
	}
	
	private String n(List<String> namen) {
		if(namen.size() == 1) return "§b" + namen.get(0);		
		String rtn = "§b" + namen.get(0);		
		for(int i = 1; i < namen.size(); i++) {
			rtn += "§a, §b" + namen.get(i);
		}
		return rtn;
	}

}
