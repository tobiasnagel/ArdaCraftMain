package me.tobi.acmain.charakter;

import java.util.List;

import me.tobi.acmain.Methoden;
import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.Message;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CharakterRequest {
	
	Player requester;
	Player operator;
	String charakter;
	Rasse rasse;
	
	public CharakterRequest(Player requester, String charakter, Rasse rasse) {
		this.requester = requester;
		this.charakter = charakter;
		this.rasse = rasse;
	}
	
	public void setOperator(Player p) {
		operator = p;
	}
	
	public boolean isTaken() {
		@SuppressWarnings("unchecked")
		List<String> got = (List<String>) ArdaCraft.getJSONConfig().get("charakter.names");
		if (got.contains(Methoden.normalize(charakter.toLowerCase()))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void accept() {
		setRequesterNick();
		applyPEXGroups();
		addConfigEntry();
	}
	
	private void addConfigEntry() {
		try {
			@SuppressWarnings("unchecked")
			List<String> charakters = (List<String>) ArdaCraft.getJSONConfig().get("charakter.names");
			charakters.add(Methoden.normalize(charakter));
			ArdaCraft.getJSONConfig().set("charakter.names", charakters);
			ArdaCraft.getPlugin().saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setRequesterNick() {
		ArdaCraft.getACServer().dispatchCommand(ArdaCraft.getACServer().getConsoleSender(), "enick " + requester.getName() + " &b" + Methoden.normalize(charakter.toLowerCase()));
		
	}
	
	private void applyPEXGroups() {
		ArdaCraft.getACServer().dispatchCommand(ArdaCraft.getACServer().getConsoleSender(), "pex user " + requester.getName() + " group set " + rasse.toString().toLowerCase());
	}
	
	public void sendToOperators() {
		for(final Player p : ArdaCraft.getACServer().getOnlinePlayers()) {
			if(p.isOp()){
				String s = ("§4=====================================================");
				Message m1 = new Message("§6[§aCharakterrequest§c " + requester.getName() + "§6]§b  §d(§6[§a" + Methoden.normalize(rasse.toString().toLowerCase()) + "§6] §c" + charakter + "§d)§r ");
				Message accept = new Message("§6[§aANNEHMEN§6]  ");
				accept.setCommand("/register " + requester.getName() + " " + rasse.toString() + " " + charakter);
				Message decline = new Message("§6[§cABLEHNEN§6]");
				decline.setCommand("/register " + requester.getName() + " DECLINE");
				Message[] messages = {m1};
				Message[] messages2 = {accept, decline};
				p.sendMessage("");
				p.sendMessage(s);
				ArdaCraft.getCraftLogger().chatJSON(messages, p);
				ArdaCraft.getCraftLogger().chatJSON(messages2, p);
				p.sendMessage(s);
				ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
					@Override public void run() {
						for(int i = 0; i<100; i++) {p.playSound(p.getLocation(), Sound.ORB_PICKUP, 20, 20);}}}, 20);
				ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
					@Override public void run() {
						for(int i = 0; i<100; i++) {p.playSound(p.getLocation(), Sound.ORB_PICKUP, 20, 20);}}}, 40);
				ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
					@Override public void run() {
						for(int i = 0; i<100; i++) {p.playSound(p.getLocation(), Sound.ORB_PICKUP, 20, 20);}}}, 60);
			}
		}
	}
	
}
