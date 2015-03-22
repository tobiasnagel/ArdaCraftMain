package me.tobi.acmain;

import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Scheduler {
	
	public Scheduler() {
		
	}
	
	public void start() {
		ArdaCraft.getACServer().getScheduler().scheduleSyncRepeatingTask(ArdaCraft.getPlugin(), new Runnable() {

			@Override
			public void run() {
				long time = ArdaCraft.getACServer().getWorld("world").getTime();
				if(time > 13000 && time < 23000) {
					//night
					for(Player p : ArdaCraft.getACServer().getOnlinePlayers()) {
						if(Rasse.get(p) == Rasse.ORK) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
						}
					}
				}else {
					//day
					for(Player p : ArdaCraft.getACServer().getOnlinePlayers()) {
						if(Rasse.get(p) == Rasse.ORK) {
							p.removePotionEffect(PotionEffectType.NIGHT_VISION);
							p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
						}
					}
				}
				for(Entity e : ArdaCraft.getACServer().getWorld("world").getEntities()) {
					if(e instanceof LivingEntity) {
						if(e.getLocation().distance(e.getWorld().getSpawnLocation()) < 100) {
							if(!(e instanceof Player)) {								
								((LivingEntity) e).remove();
								e.getWorld().playEffect(e.getLocation(), Effect.MOBSPAWNER_FLAMES, 20);
							}
						}
					}
				}
				for(Player p : Statics.List.shootmode) {
					Entity e = p.getWorld().spawnEntity(p.getLocation(), EntityType.GUARDIAN);
					p.setPassenger(e);
					e.getLocation().setDirection(p.getLocation().getDirection());
					Guardian g = (Guardian) e;				
					g.setTarget(Methoden.getNearestPlayer(p.getLocation(), 20));
					g.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
				}
			}
			
		}, 100, 100);
	}
	
}
