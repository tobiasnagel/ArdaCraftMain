package me.tobi.acmain.controlpoints;

import java.util.ArrayList;
import java.util.List;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.rasse.Rasse.Attitude;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ControlPointManager {

	private static List<ControlPoint> cps = new ArrayList<ControlPoint>();
	
	public static void init() {
		World w = ArdaCraft.getACServer().getWorld("world");
		ControlPoint hobbingen_alt = new ControlPoint(new Location(w, 505, 65, 244), "HO_ALT");
		hobbingen_alt.reset();
		ControlPoint minas_thirit = new ControlPoint(new Location(w, -142, 109, 252), "MT");
		minas_thirit.reset();
		ControlPoint mordor = new ControlPoint(new Location(w, 911, 70, -232), "MO");
		mordor.reset();
	}
	
	public static List<ControlPoint> getControlPoints() {
		return cps;
	}
	
	public static void startScheduler() {
		ArdaCraft.getACServer().getScheduler().scheduleSyncRepeatingTask(ArdaCraft.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for(Player p : ArdaCraft.getACServer().getOnlinePlayers()) {
					for(ControlPoint cp : cps) {
						if(p.getLocation().distance(cp.getLocation()) < 5) {
							if(Rasse.get(p).getAttitude() == Attitude.GOOD) {
								cp.addGoodGrade();
							}
							if(Rasse.get(p).getAttitude() == Attitude.BAD) {
								cp.addGoodGrade();
							}
						}
					}
				}
			}
			
		}, 20, 20);
	}
	
}
