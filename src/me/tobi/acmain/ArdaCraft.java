package me.tobi.acmain;

import java.util.ArrayList;
import java.util.List;

import me.tobi.acmain.commands.CmdAddWarp;
import me.tobi.acmain.commands.CmdBigJump;
import me.tobi.acmain.commands.CmdBoese;
import me.tobi.acmain.commands.CmdCheckinaktive;
import me.tobi.acmain.commands.CmdCmute;
import me.tobi.acmain.commands.CmdGut;
import me.tobi.acmain.commands.CmdHide;
import me.tobi.acmain.commands.CmdMute;
import me.tobi.acmain.commands.CmdOpen;
import me.tobi.acmain.commands.CmdPlayer;
import me.tobi.acmain.commands.CmdRegeln;
import me.tobi.acmain.commands.CmdRegister;
import me.tobi.acmain.commands.CmdRequest;
import me.tobi.acmain.commands.CmdSpawn;
import me.tobi.acmain.commands.CmdStadt;
import me.tobi.acmain.commands.CmdThief;
import me.tobi.acmain.events.EvtHandler;
import me.tobi.acmain.items.ItemHandler;
import me.tobi.acmain.message.CraftLogger;
import me.tobi.acmain.message.Msg;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ArdaCraft extends JavaPlugin{
	
	private static ArdaCraft plugin;
	private static CraftLogger logger;
	private static FileConfiguration config;
	public static List<Player> hidden = new ArrayList<Player>();
	
	@Override
	public void onDisable() {}
	
	
	@Override
	public void onEnable() {
		ItemHandler.registerItems();
		registerFiles();
		//ControlPointManager.init();
		//ControlPointManager.startScheduler();
		config = this.getConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new EvtHandler(this), this);
		getCommand("register").setExecutor(new CmdRegister());
		getCommand("checkinaktive").setExecutor(new CmdCheckinaktive());
		getCommand("request").setExecutor(new CmdRequest());
		getCommand("spawn").setExecutor(new CmdSpawn());
		getCommand("regeln").setExecutor(new CmdRegeln());
		getCommand("gut").setExecutor(new CmdGut());
		getCommand("boese").setExecutor(new CmdBoese());
		getCommand("cmute").setExecutor(new CmdCmute());
		getCommand("addwarp").setExecutor(new CmdAddWarp());
		getCommand("player").setExecutor(new CmdPlayer());
		getCommand("mute").setExecutor(new CmdMute());
		//getCommand("help").setExecutor(new CmdHelp());
		getCommand("bigjump").setExecutor(new CmdBigJump());
		getCommand("open").setExecutor(new CmdOpen());
		getCommand("hide").setExecutor(new CmdHide());
		getCommand("stadt").setExecutor(new CmdStadt());
		getCommand("thief").setExecutor(new CmdThief());
		//getCommand("list").setExecutor(new CmdList());
		ConsoleCommandSender sender = getServer().getConsoleSender();
		sender.sendMessage(Msg.PLUGIN_ENABLED);
		Scheduler scheduler = new Scheduler();
		scheduler.start();
	}
	
	public static List<Player> muted = new ArrayList<Player>();

	private void registerFiles() {
		this.saveDefaultConfig();
		plugin = this;
		logger = new CraftLogger();
		try{
			@SuppressWarnings("unused")
			List<String> list = this.getConfig().getStringList("charakter.names");
		}catch(Exception e) {
			List<String> names = new ArrayList<String>();
			names.add("Gandalf");
			this.getConfig().set("charakter.names", names);
			this.saveConfig();
			System.out.println(this.getConfig().getStringList("charakter.names"));			
		}		
	}
	
	public static Server getACServer() {
		return plugin.getServer();
	}
	
	public static CraftLogger getCraftLogger() {
		return logger;
	}
	
	public static ArdaCraft getPlugin() {
		return plugin;
	}
	
	public static FileConfiguration getJSONConfig(){
		return config;
	}
	
	
	
}