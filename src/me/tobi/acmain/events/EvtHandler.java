package me.tobi.acmain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.Rank;
import me.tobi.acmain.Statics;
import me.tobi.acmain.items.ItemHandler;
import me.tobi.acmain.items.ItemType;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.message.Message;
import me.tobi.acmain.message.Msg;
import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.rasse.Rasse.Attitude;
import net.minecraft.server.v1_8_R1.Enchantment;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
import org.spigotmc.event.entity.EntityDismountEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EvtHandler implements Listener{
	
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
		String prefix = Rasse.get(p).getAttitude() == Attitude.BAD?"&7":"&2";
		ArdaCraft.getACServer().dispatchCommand(ArdaCraft.getACServer().getConsoleSender(), "ne prefix " + p.getName() + " " + prefix);
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
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager().getType() == EntityType.PLAYER) {
			if(event.getEntity().getType() == EntityType.PLAYER) {
				Player damager = (Player)event.getDamager();
				Player attacked = (Player)event.getEntity();
				if(Rasse.get(damager) == Rasse.get(attacked)) {
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
		if(Statics.List.muted.contains(p)) {
			ArdaCraft.getCraftLogger().logToChat(Level.WARN, "Du bist gemuted und kannst nicht schreiben!", p);
		}else {
			if(Statics.chatmuteActive) {
				if(Rank.get(p) != Rank.SPIELER || Rasse.get(p) == Rasse.UNREGISTERED) {
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
		if(Statics.List.bigjumpmode.contains(event.getPlayer())){
			Player p = event.getPlayer();
			if(p.getGameMode() != GameMode.CREATIVE) {
				if(p.getLocation().subtract(0,  1, 0).getBlock().getType() != org.bukkit.Material.AIR) {
					if(!p.isFlying()) {
						p.setAllowFlight(true);
					}
				}
			}
		}
		
		if(Rasse.get(event.getPlayer()) == Rasse.ELB){
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
		if(Statics.List.bigjumpmode.contains(event.getPlayer())){
			Player p = event.getPlayer();
			if(p.getGameMode() == GameMode.CREATIVE)
				return;
			event.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
		}
		if(Rasse.get(event.getPlayer()) == Rasse.ELB){
			Player p = event.getPlayer();
			if(p.getGameMode() == GameMode.CREATIVE)
				return;
			event.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(1.01).setY(1));
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
					Statics.List.speerShooter.add(event.getPlayer());
				}
			}
		}		
	}
	
	@EventHandler
	public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
		if(event.getTarget() instanceof Player){
			if(Rasse.get((Player)event.getTarget()) == Rasse.ORK) {
				if(event.getEntity().getType() == EntityType.ZOMBIE){
					event.setTarget(null);
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDismount(EntityDismountEvent event) {
		if(event.getEntity()instanceof Guardian) {
			if(event.getDismounted() instanceof Player) {
				
			}
		}
	}

}