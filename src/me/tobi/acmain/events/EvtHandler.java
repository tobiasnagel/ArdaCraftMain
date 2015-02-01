package me.tobi.acmain.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.items.ItemHandler;
import me.tobi.acmain.items.ItemType;
import me.tobi.acmain.items.ItemType.WeaponType;
import me.tobi.acmain.items.SpecialItem;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Message;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;
import net.minecraft.server.v1_8_R1.Enchantment;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EvtHandler implements Listener{
	
	ArdaCraft pl;
	
	public EvtHandler(ArdaCraft plugin) {
		pl = plugin;
	}
	
	//==============================================
	public static boolean chatmuteActive = false;
	public static List<Player> speerShooter = new ArrayList<Player>();	
	public static List<Player> bigjumpmode = new ArrayList<Player>();	
	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();	
	//==============================================
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		ArdaCraft.getCraftLogger().logToChat(Level.INFO, Msg.EVENT_JOIN_WELCOME, p);
		ArdaCraft.getCraftLogger().logToChat(Level.INFO, "§b§lZurzeit sind §a§l" + ArdaCraft.getOnlinePlayers().length + "§b§l von §c§l" + ArdaCraft.getACServer().getMaxPlayers() + "§b§l Spielern online", p);
		p.sendMessage("");
		p.sendMessage("");
		event.setJoinMessage("");
		Message m1 = new Message("§8Join§2>");
		m1.setColor(null);
		Message m2 = new Message(p.getDisplayName());
		m2.setHoverText(p.getName());
		m2.setColor(ChatColor.YELLOW);
		Message[] msg = {m1, m2};
		ArdaCraft.getCraftLogger().chatJSON(msg);
		Methoden.clearEffects(p);
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if(event.getEntity().getType() == EntityType.PLAYER) {
			((Player)event.getEntity()).setSaturation(20.0F);
		}
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent event) {
		event.setMotd("§cArdaCraft §b- §aDein Herr der Ringe Rollenspielserver!");
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
		Message m1 = new Message("§8Left§4>");
		m1.setColor(null);
		Message m2 = new Message(event.getPlayer().getDisplayName());
		m2.setHoverText(event.getPlayer().getName());
		m2.setColor(ChatColor.YELLOW);
		Message[] msg = {m1, m2};
		ArdaCraft.getCraftLogger().chatJSON(msg);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage("§c§l" + event.getDeathMessage());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Rasse.applyEffects(event.getPlayer());
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlock().getType() == Material.COBBLESTONE) {
			event.getBlock().setType(Material.STONE);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			int x = event.getClickedBlock().getLocation().getBlockX();
			int y = event.getClickedBlock().getLocation().getBlockY();
			int z = event.getClickedBlock().getLocation().getBlockZ();
			if(event.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET||
					event.getPlayer().getItemInHand().getType() == Material.LAVA) { //TODO not clickeed block but item in hand
				for(Player p : ArdaCraft.getOnlinePlayers()) {
					if(p.isOp()) p.sendMessage("§b[" + event.getPlayer().getName() + "]" + "§6 placed §cLAVA §6at §c" + x + " " + y + " " + z);
				}
			}
			if(event.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) {
				for(Player p : ArdaCraft.getOnlinePlayers()) {
					if(p.isOp()) p.sendMessage("§b[" + event.getPlayer().getName() + "]" + "§6 placed §cFIRE §6at §c" + x + " " + y + " " + z);
				}
			}
			if(event.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET ||
					event.getPlayer().getItemInHand().getType() == Material.WATER) {
				for(Player p : ArdaCraft.getOnlinePlayers()) {
					if(p.isOp()) p.sendMessage("§b[" + event.getPlayer().getName() + "]" + "§6 placed §cWATER §6at §c" + x + " " + y + " " + z);
				}
			}
			
		}
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
							speerShooter.add(event.getPlayer());
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
					if(Methoden.getRasse(p) == Rasse.URUKHAI || Methoden.getRasse(p) == Rasse.DUNEDAIN) {
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
							if(!( Methoden.getRasse(p).equals(Rasse.NAZGUL) || p.hasPotionEffect(PotionEffectType.INVISIBILITY)));
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
					if(Methoden.getRasse(p) == Rasse.MAGIER) {
						int cooldownTime = 1;
						if (cooldowns.containsKey(p.getName())) {
							long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (secondsLeft > 0) {
							}else {
								cooldowns.put(p.getName(), System.currentTimeMillis());
								WitherSkull ws = p.launchProjectile(WitherSkull.class);
								ws.setShooter(p);
								ws.setVelocity(p.getLocation().getDirection().multiply(1.8));
							}
						}else {
							cooldowns.put(p.getName(), System.currentTimeMillis());
							WitherSkull ws = p.launchProjectile(WitherSkull.class);
							ws.setShooter(p);
							ws.setVelocity(p.getLocation().getDirection().multiply(1.8));
						}
					}
				}
				
			}
		}
		if(! (ItemType.getType(event.getItem()) == ItemType.NORMAL) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			event.setCancelled(true);
		}
		if(ItemHandler.isSpecialItem(event.getItem())) {
			if(ItemType.getType(event.getItem()).getMaterial() == Material.BLAZE_ROD) {
				if(event.getItem().getType() == Material.STICK) {
					event.getItem().setType(Material.BLAZE_ROD);
				}
			}
			//ItemType t = ItemType.getType(event.getItem());
			//if(t == ItemType.AXT || t == ItemType.BOGEN || t == ItemType.KRUMMSCHWERT || t == ItemType.SCHWERT) {
			//	event.getItem().setDurability(event.getItem().getType().getMaxDurability());
			//}
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
						if(speerShooter.contains(event.getEntity().getShooter())){
							event.getEntity().remove();
							speerShooter.remove((Player)event.getEntity().getShooter());
						}
					}					
				}
			}, 20);
		}catch(Exception e) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		event.blockList().clear();
		if(event.getEntity().getType() == EntityType.WITHER_SKULL) {
			WitherSkull skull = (WitherSkull) event.getEntity();
			
			if(((Projectile)skull).getShooter().getType() == EntityType.PLAYER) {
				for(Player p : ArdaCraft.getOnlinePlayers()) {
					if(p.getLocation().distance(skull.getLocation()) < 10) {
						ItemType stab = ItemType.STAB;
						p.damage(stab.getDamage() * Methoden.getDamageReduced(p));
					}
				}
			}
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
				Rasse r = Methoden.getRasse(damager);
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
					Rasse r = Methoden.getRasse(damager);
					if(wType == WeaponType.PFEIL || wType == WeaponType.WITHERKOPF || wType == WeaponType.FEUERBALL){				
						if(iType == ItemType.BOGEN && r != Rasse.ELB) 
							return;
						if(iType == ItemType.BOGEN && r != Rasse.WARGREITER) 
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
				
				if (speerShooter.contains(((Projectile) event.getDamager()).getShooter())) {
					event.setDamage(15.0);
					speerShooter.remove((Player)((Projectile) event.getDamager()).getShooter());
				}
				
				if(victim instanceof Player) {
					if(Methoden.getRasse(damager) == Methoden.getRasse((Player)victim)) {
						event.setDamage(0);
						event.setCancelled(true);
					}
				}
				
			}
		}
		if(event.getDamager().getType() == EntityType.PLAYER) {
			if(event.getEntity().getType() == EntityType.PLAYER) {
				Player damager = (Player)event.getDamager();
				Player attacked = (Player)event.getEntity();
				if(Methoden.getRasse(damager) == Methoden.getRasse(attacked)) {
					event.setCancelled(true);
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Du kannst deine eigene Rasse nicht angreifen!", damager);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		List<String> validUUIDs = new ArrayList<String>();
		validUUIDs.add("ae99dd1d-3bd9-4d45-800b-b9676901d823");
		validUUIDs.add("a4d4dfcf-1622-452c-9022-c80d445c68b7");
		if(!validUUIDs.contains(event.getPlayer().getUniqueId().toString())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player p = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(p);
		Message msg1 = new Message("");
		if(p.isOp()) msg1.setText("§4§l* ");
		Message msg2 = new Message(user.getPrefix().replaceAll("&", "§"));
		Message msg3 = new Message(p.getDisplayName().replaceAll("&", "§"));
		msg3.setHoverText(p.getName());
		Message msg4 = new Message(user.getSuffix().replaceAll("&", "§"));
		Message msg5 = new Message("");
		if(p.isOp()) {msg5.setText("§c: ");}else {msg5.setText("§r: ");}
		Message msg6 = new Message(event.getMessage().replaceAll("&", "§"));
		Message[] messages = {msg1, msg2, msg3, msg4, msg5, msg6};
		if(ArdaCraft.muted.contains(p)) {
			ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Du bist gemuted und kannst nicht schreiben!", p);
		}else {
			if(chatmuteActive) {
				if(Methoden.getRank(p) != "Spieler" || Methoden.getRasse(p) == Rasse.UNREGISTERED) {
					ArdaCraft.getCraftLogger().chatJSON(messages);
					ArdaCraft.getACServer().getConsoleSender().sendMessage("§bCHAT: §a" + p.getName() + "§c(§a" + p.getDisplayName()
							 + "§c)§b: " + event.getMessage());
				}else {
					ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Der Chat wurde deaktiviert! Du kannst momentan nciht schreiben!", p);
				}
			}else {
				ArdaCraft.getCraftLogger().chatJSON(messages);
				ArdaCraft.getACServer().getConsoleSender().sendMessage("§bCHAT: §a" + p.getName() + "§c(§a" + p.getDisplayName()
						 + "§c)§b: " + event.getMessage());
			}
		}
	}
	
	
	@EventHandler
	public void chestDEBUG(PlayerInteractEvent event) {
		Block b = event.getClickedBlock();
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(
					b.getType() == Material.CHEST 
					|| b.getType() == Material.HOPPER
					|| b.getType() == Material.DISPENSER
					|| b.getType() == Material.DROPPER
					|| b.getType() == Material.ENCHANTMENT_TABLE
					|| b.getType() == Material.FURNACE
					|| b.getType() == Material.DIAMOND_BLOCK
					|| b.getType() == Material.GOLD_BLOCK
					|| b.getType() == Material.EMERALD_BLOCK
					|| b.getType() == Material.IRON_BLOCK
					|| b.getType() == Material.REDSTONE_BLOCK
					
					) {
				if(b.getLocation().add(1, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(1, 0, 0).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
						s.setLine(0, "[Private]");
						event.setCancelled(true);
						s.update();
					}
				}
				if (b.getLocation().add(-1, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(-1, 0, 0).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
						s.setLine(0, "[Private]");
						event.setCancelled(true);
						s.update();
					}
				}
				if (b.getLocation().add(0, 0, 1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(0, 0, 1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
						s.setLine(0, "[Private]");
						event.setCancelled(true);
						s.update();
					}		
				}
				if (b.getLocation().add(0, 0, -1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(0, 0, -1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(2, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(2, 0, 0).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(-2, 0, 0).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(-2, 0, 0).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(0, 0, -2).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(0, 0, -2).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(0, 0, 2).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(0, 0, 2).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(1, 0, 1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(1, 0, 1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(-1, 0, 1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(-1, 0, 1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(1, 0, -1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(1, 0, -1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				if (b.getLocation().add(-1, 0, -1).getBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign)b.getLocation().add(-1, 0, -1).getBlock().getState();
					if(s.getLine(0).equalsIgnoreCase("Private")){
							s.setLine(0, "[Private]");
							event.setCancelled(true);
							s.update();
					}
				}
				
			}
			if(event.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign s = (Sign)b.getState();
				if(s.getLine(0).equalsIgnoreCase("Private")){
						s.setLine(0, "[Private]");
						event.setCancelled(true);
						s.update();
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getInventory().getType() == InventoryType.WORKBENCH){
			if(event.getAction() == InventoryAction.COLLECT_TO_CURSOR ||
					event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY||
					event.getAction() == InventoryAction.PICKUP_ALL||
					event.getAction() == InventoryAction.PICKUP_HALF||
					event.getAction() == InventoryAction.PICKUP_ONE||
					event.getAction() == InventoryAction.PICKUP_SOME) {
				if(event.getSlotType() == SlotType.CRAFTING.RESULT) {
					ItemStack i = event.getCurrentItem();
					net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(event.getCurrentItem());
					if(Enchantment.DURABILITY.canEnchant(nmsItem)) {
						Random rnd = new Random();
						if(rnd.nextInt(5) == 3) {
							if(Enchantment.DAMAGE_ALL.canEnchant(nmsItem)) { //weapon
								int r = rnd.nextInt(6);
								if(r == 1)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DAMAGE_ALL, rnd.nextInt(3) + 1);
								if(r == 2)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, rnd.nextInt(3) + 1);
								if(r == 3)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, rnd.nextInt(3) + 1);
								if(r == 4)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_MOBS, rnd.nextInt(3) + 1);
							}else if(Enchantment.ARROW_DAMAGE.canEnchant(nmsItem)) { //bow
								int r = rnd.nextInt(7);
								if(r == 1)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.ARROW_DAMAGE, rnd.nextInt(3) + 1);
								if(r == 2)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, rnd.nextInt(3) + 1);
								if(r == 3)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.ARROW_FIRE, 1);
								if(r == 4)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.ARROW_INFINITE, 1);
								if(r == 5)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.ARROW_KNOCKBACK, rnd.nextInt(3) + 1);
							}else if(Enchantment.PROTECTION_ENVIRONMENTAL.canEnchant(nmsItem)) { //armor
								int r = rnd.nextInt(9);
								if(r == 1)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL, rnd.nextInt(3) + 1);
								if(r == 2)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, rnd.nextInt(3) + 1);
								if(r == 3)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_EXPLOSIONS, rnd.nextInt(3) + 1);
								if(r == 4)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_FALL, rnd.nextInt(3) + 1);
								if(r == 5)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_FIRE, rnd.nextInt(3) + 1);
								if(r == 6)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_PROJECTILE, rnd.nextInt(3) + 1);
								if(r == 7)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.THORNS, rnd.nextInt(3) + 1);
							}else if(Enchantment.LOOT_BONUS_BLOCKS.canEnchant(nmsItem)) { //tool
								int r = rnd.nextInt(5);
								if(r == 1)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, rnd.nextInt(3) + 1);
								if(r == 2)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DURABILITY, rnd.nextInt(3) + 1);
								if(r == 3)
								i.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.DIG_SPEED, rnd.nextInt(3) + 1);
							}
						}						
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(bigjumpmode.contains(event.getPlayer())){
			Player p = event.getPlayer();
			if(p.getGameMode() != GameMode.CREATIVE) {
				if(p.getLocation().subtract(0,  1, 0).getBlock().getType() != org.bukkit.Material.AIR) {
					if(!p.isFlying()) {
						p.setAllowFlight(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		if(bigjumpmode.contains(event.getPlayer())){
			Player p = event.getPlayer();
			if(p.getGameMode() == GameMode.CREATIVE)
				return;
			event.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
		}
	}
	
	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent event) {
		if(ItemHandler.isSpecialItem(event.getBrokenItem())) {
			ItemStack is = event.getBrokenItem();
			is.setItemMeta(event.getBrokenItem().getItemMeta());
			if(is.getType().getMaxStackSize() == 0)
			is.setDurability((short) 0);
			event.getPlayer().setItemInHand(is);
		}
	}
	
	@EventHandler
	public void onInventoryClickMenu(InventoryClickEvent event) {
		if(event.getInventory().getName().contains("Städte")){
			Player p = (Player) event.getInventory().getHolder();
			ArdaCraft.getACServer().dispatchCommand(ArdaCraft.getACServer().getConsoleSender(), "warp " + event.getCurrentItem().getItemMeta().getDisplayName() + " " + p.getName());
			event.setCancelled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getPlayer().getItemInHand().hasItemMeta()) {
			if (event.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
				if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Speer")) {
					event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
					Arrow ar = event.getPlayer().launchProjectile(Arrow.class);
					ar.setShooter(event.getPlayer());
					ar.setBounce(true);
					ar.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
					speerShooter.add(event.getPlayer());
				}
			}
		}
		
	}

}