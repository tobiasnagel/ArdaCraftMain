package me.tobi.acmain.items;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.Statics;
import me.tobi.acmain.items.ItemType.WeaponType;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.rasse.Rasse;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {	
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null) {
				if(event.getItem().hasItemMeta()) {
					if(event.getItem().getItemMeta().hasDisplayName()) {
						if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Speer")) {
							if(event.getPlayer().getItemInHand().getAmount() > 1) {
								event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount()-1);
							}else {
								event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
							}
							Arrow ar = event.getPlayer().launchProjectile(Arrow.class);
							ar.setShooter(event.getPlayer());
							ar.setBounce(true);
							ar.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
							Statics.List.speerShooter.add(event.getPlayer());
						}
					}
				}
			}
		}
		
		if(event.getItem() == null) {
			return;
		}
		
		ItemStack item = event.getPlayer().getItemInHand();
		if(ItemHandler.isSpecialItem(item)) {
			if(ItemHandler.isOldSpecialItem(item)) {
				SpecialItem si = new SpecialItem(ItemType.getType(item));
				event.getPlayer().getInventory().remove(item);
				event.getPlayer().getInventory().addItem(si.getItem());
				ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Dein Spezialitem wurde in ein neues Format übertragen!", event.getPlayer());
				return;
			}
			ItemType type = ItemType.getType(item);
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(type == ItemType.ARMBRUST) {
					final Player p = event.getPlayer();
					if(Rasse.get(p) == Rasse.URUKHAI || Rasse.get(p) == Rasse.DUNEDAIN) {
						if(! p.hasPotionEffect(PotionEffectType.SLOW)) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 255));
							ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {
								
								@Override
								public void run() {
									Arrow ar = p.launchProjectile(Arrow.class);
									ar.setShooter(p);
									ar.setVelocity(p.getLocation().getDirection().multiply(7));
									p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);	
								}
								
							}, 20);
						}
					}
				}
				
				if(type == ItemType.RING){
					Player p = event.getPlayer();
					item.setDurability((short) 0);
					event.setCancelled(true);
					if(!p.hasPotionEffect(PotionEffectType.CONFUSION)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 5));
						p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 2));
						p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 255));
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 5));
						for(Player pl : ArdaCraft.getACServer().getOnlinePlayers()) {
							if(!( Rasse.get(p).equals(Rasse.NAZGUL) || p.hasPotionEffect(PotionEffectType.INVISIBILITY)));
							pl.hidePlayer(p);
						}
					} else {
						p.removePotionEffect(PotionEffectType.CONFUSION);
						p.removePotionEffect(PotionEffectType.NIGHT_VISION);
						p.removePotionEffect(PotionEffectType.HUNGER);
						p.removePotionEffect(PotionEffectType.WEAKNESS);
						p.removePotionEffect(PotionEffectType.INVISIBILITY);
						for(Player pl : ArdaCraft.getACServer().getOnlinePlayers()) {
							pl.showPlayer(p);
						}
					}
				}
				
				if (type == ItemType.STAB) {
					Player p = event.getPlayer();
					if(Rasse.get(p) == Rasse.MAGIER) {
						int cooldownTime = 1;
						if (Statics.List.cooldowns.containsKey(p.getName())) {
							long secondsLeft = ((Statics.List.cooldowns.get(p.getName()) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (secondsLeft > 0) {
							}else {
								Statics.List.cooldowns.put(p.getName(), System.currentTimeMillis());
								WitherSkull ws = p.launchProjectile(WitherSkull.class);
								ws.setShooter(p);
								ws.setVelocity(p.getLocation().getDirection().multiply(1.8));
							}
						}else {
							Statics.List.cooldowns.put(p.getName(), System.currentTimeMillis());
							WitherSkull ws = p.launchProjectile(WitherSkull.class);
							ws.setShooter(p);
							ws.setVelocity(p.getLocation().getDirection().multiply(1.8));
						}
					}
				}
				
			}
		}
		
		if(ItemType.getType(event.getItem()) == ItemType.BOGEN) {
			if(Rasse.get(event.getPlayer()) != Rasse.ELB) {
				event.setCancelled(true);
			}
		}
		if(ItemHandler.isSpecialItem(event.getItem())) {
			if(ItemType.getType(event.getItem()).getMaterial() == Material.BLAZE_ROD) {
				if(event.getItem().getType() == Material.STICK) {
					event.getItem().setType(Material.BLAZE_ROD);
				}
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(final ProjectileHitEvent event) {
		try{
			ArdaCraft.getACServer().getScheduler().scheduleSyncDelayedTask(ArdaCraft.getPlugin(), new Runnable() {

				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					if(event.getEntity() instanceof Arrow){
						if(Statics.List.speerShooter.contains(event.getEntity().getShooter())){
							event.getEntity().remove();
							Statics.List.speerShooter.remove((Player)event.getEntity().getShooter());
						}
					}					
				}
			}, 20);
		}catch(Exception e) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player damager = (Player)event.getDamager();
			Entity victim = event.getEntity();
			ItemStack item = damager.getItemInHand();
			if(ItemHandler.isSpecialItem(item)) {
				ItemType iType = ItemType.getType(item);
				WeaponType wType = iType.getWeaponType();
				Rasse r = Rasse.get(damager);
				if(wType == WeaponType.NAHKAMPF || wType == WeaponType.WITHERKOPF){
					if(iType == ItemType.AXT && r != Rasse.ZWERG) 
						return;
					if(iType == ItemType.AXT && r != Rasse.ORK) 
						return;
					if(iType == ItemType.DOLCH && r != Rasse.HOBBIT) 
						return;
					if(iType == ItemType.DOLCH && r != Rasse.NAZGUL) 
						return;
					if(iType == ItemType.SCHWERT && r != Rasse.MENSCH) 
						return;
					if(iType == ItemType.SCHWERT && r != Rasse.OSTLING) 
						return;
					if(iType == ItemType.STAB && r != Rasse.MAGIER) 
						return;
					if(iType == ItemType.KRUMMSCHWERT && r != Rasse.ENT) 
						return;
					if(iType == ItemType.KRUMMSCHWERT && r != Rasse.TROLL) 
						return;
					double dmg = iType.getDamage();
					double mod_dmg = dmg;
					if(victim instanceof HumanEntity){
						mod_dmg = dmg * Methoden.getDamageReduced((HumanEntity)victim);
					}
					event.setDamage(mod_dmg);
				}else {
					event.setDamage(2.0);
					
				}
			}
		}else if(event.getDamager() instanceof Projectile) {
			if(((Projectile) event.getDamager()).getShooter() instanceof Player) {
				Player damager = (Player)((Projectile) event.getDamager()).getShooter();
				Entity victim = event.getEntity();
				ItemStack item = damager.getItemInHand();
				if(ItemHandler.isSpecialItem(item)) {
					ItemType iType = ItemType.getType(item);
					WeaponType wType = iType.getWeaponType();
					Rasse r = Rasse.get(damager);
					if(wType == WeaponType.PFEIL || wType == WeaponType.WITHERKOPF || wType == WeaponType.FEUERBALL){				
						if(iType == ItemType.BOGEN && (r != Rasse.ELB || r!= Rasse.WARGREITER)) 
							return;
						if(iType == ItemType.STAB && r != Rasse.MAGIER) 
							return;
						double dmg = iType.getDamage();
						double mod_dmg = dmg;
						if(victim instanceof HumanEntity){
							mod_dmg = dmg;
						}
						event.setDamage(mod_dmg);
					}else {
						event.setDamage(2.0);
					}
				}
				
				if (Statics.List.speerShooter.contains(((Projectile) event.getDamager()).getShooter())) {
					event.setDamage(15.0);
					Statics.List.speerShooter.remove((Player)((Projectile) event.getDamager()).getShooter());
				}
				
				if(victim instanceof Player) {
					if(Rasse.get(damager) == Rasse.get((Player)victim)) {
						event.setDamage(0);
						event.setCancelled(true);
					}
				}
				
			}
		}
	}
	
}
