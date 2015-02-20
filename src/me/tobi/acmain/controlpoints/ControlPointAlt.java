package me.tobi.acmain.controlpoints;

import java.util.HashMap;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.Methoden;
import me.tobi.acmain.message.CraftLogger.Level;
import me.tobi.acmain.rasse.Rasse;
import me.tobi.acmain.rasse.Rasse.Attitude;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class ControlPointAlt {
	
	public static void createCP(Location loc, String ID) {
		Location saveloc = loc;
		loc.getWorld().getBlockAt(loc).setType(Material.SIGN_POST);
		Sign sign = (Sign)loc.getWorld().getBlockAt(loc).getState();
		sign.setLine(0, "[CP]");
		sign.setLine(1, "0");
		sign.setLine(2, ID);
		sign.update();
		clearPoint(saveloc);
		saveloc = loc;
		setIron(saveloc);
		loc.add(0, 1, 0);
		loc.getWorld().getBlockAt(loc).setType(Material.BEACON);
		loc.add(0, -1, 0);
	}
	
	public static Location[] getControlPoints(World w) {
		Location[] locs = {
				new Location(w, 911, 70, -232)//Mordor
				,new Location(w, -142, 109, 252)//Minas Thirit
				,new Location(w, 505, 65, 244)//HobbingenAlt
		};
		return locs;
	}
	
	public static void startCheckScheduler(final ArdaCraft pl) {
		pl.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for(Location loc : getControlPoints(pl.getServer().getWorld("world"))) {
					for(Player p : pl.getServer().getOnlinePlayers()) {
						if(p.getWorld().getName() == "world") {
							if(loc.distance(p.getLocation()) < 4) {
								if(Rasse.get(p).getAttitude().equals(Attitude.GOOD)) {
									addGoodGrade(loc);
								}else if(Rasse.get(p).getAttitude().equals(Attitude.GOOD)) {
									addBadGrade(loc);
								}else {
									
								}
							}
						}						
					}
				}
			}
			
		}, 80, 80);
	}
	
	public static Location getNearesCheckpoint(Location[] cps, Location player) {
		HashMap<Integer, Location> distances = new HashMap<Integer, Location>();
		int[] distance = new int[cps.length];
		@SuppressWarnings("unused")
		int count = 0;
		for(Location loc : cps) {
			distances.put((int) loc.distance(player), loc);
			count++;
		}		
		return distances.get(Methoden.min(distance));
	}
	
	public static void setGrade(Location loc, int grade) {
		if(!(grade > 16 || grade < -16)) { 
			clearPoint(loc);
			Sign sign = (Sign)loc.getWorld().getBlockAt(loc).getState();
			sign.setLine(1, String.valueOf(grade));
			sign.update();
			loc.getWorld().playSound(loc, Sound.ANVIL_LAND, 5.0F, 5.0F);
			if(grade > 0) {
				byte dat = 0;
				if(grade >= 1) {
					setGrade1(loc, dat);
				}if(grade >= 2) {
					setGrade2(loc, dat);
				}if(grade >= 3) {
					setGrade3(loc, dat);
				}if(grade >= 4) {
					setGrade4(loc, dat);
				} if(grade >= 5) {
					setGrade5(loc, dat);
				} if(grade >= 6) {
					setGrade6(loc, dat);
				} if(grade >= 7) {
					setGrade7(loc, dat);
				} if(grade >= 8) {
					setGrade8(loc, dat);
				} if(grade >= 9) {
					setGrade9(loc, dat);
				} if(grade >= 10) {
					setGrade10(loc, dat);
				} if(grade >= 11) {
					setGrade11(loc, dat);
				} if(grade >= 12) {
					setGrade12(loc, dat);
				} if(grade >= 13) {
					setGrade13(loc, dat);
				} if(grade >= 14) {
					setGrade14(loc, dat);
				} if(grade >= 15) {
					setGrade15(loc, dat);
				} if(grade >= 16) {
					setGrade16(loc, dat);
				}
			}else {
				byte dat = 7;
				
				if(grade <= -1) {
					setGrade1(loc, dat);
				} if(grade <= -2) {
					setGrade2(loc, dat);
				} if(grade <= -3) {
					setGrade3(loc, dat);
				} if(grade <= -4) {
					setGrade4(loc, dat);
				} if(grade <= -5) {
					setGrade5(loc, dat);
				} if(grade <= -6) {
					setGrade6(loc, dat);
				} if(grade <= -7) {
					setGrade7(loc, dat);
				} if(grade <= -8) {
					setGrade8(loc, dat);
				} if(grade <= -9) {
					setGrade9(loc, dat);
				} if(grade <= -10) {
					setGrade10(loc, dat);
				} if(grade <= -11) {
					setGrade11(loc, dat);
				} if(grade <= -12) {
					setGrade12(loc, dat);
				} if(grade <= -13) {
					setGrade13(loc, dat);
				} if(grade <= -14) {
					setGrade14(loc, dat);
				} if(grade <= -15) {
					setGrade15(loc, dat);
				} if(grade <= -16) {
					setGrade16(loc, dat);
				}
			}		
		}		
	}
	
	
	public static void clearPoint(Location loc) {
		Location modloc = loc;
		setGrade1(modloc, (byte)8);
		modloc = loc;
		setGrade2(modloc, (byte)8);
		modloc = loc;
		setGrade3(modloc, (byte)8);
		modloc = loc;
		setGrade4(modloc, (byte)8);
		modloc = loc;
		setGrade5(modloc, (byte)8);
		modloc = loc;
		setGrade6(modloc, (byte)8);
		modloc = loc;
		setGrade7(modloc, (byte)8);
		modloc = loc;
		setGrade8(modloc, (byte)8);
		modloc = loc;
		setGrade9(modloc, (byte)8);
		modloc = loc;
		setGrade10(modloc, (byte)8);
		modloc = loc;
		setGrade11(modloc, (byte)8);
		modloc = loc;
		setGrade12(modloc, (byte)8);
		modloc = loc;
		setGrade13(modloc, (byte)8);
		modloc = loc;
		setGrade14(modloc, (byte)8);
		modloc = loc;
		setGrade15(modloc, (byte)8);
		modloc = loc;
		setGrade16(modloc, (byte)8);
		modloc = loc;
	}
	
	public static void addGoodGrade(Location loc) {
		if(getGrade(loc)>=0) {
			setGrade(loc, getGrade(loc) + 1);
		}else {
			setGrade(loc, getGrade(loc) + 1);
		}		
	}
	
	public static void addBadGrade(Location loc) {
		if(getGrade(loc)<=0) {
			setGrade(loc, getGrade(loc) - 1);
		}else {
			setGrade(loc, getGrade(loc) - 1);
		}
		
	}
	
	public static int getGrade(Location loc) {
		Sign sign = (Sign)loc.getWorld().getBlockAt(loc).getState();
		return Integer.parseInt(sign.getLine(1));
	}
	
	public static String getID(Location loc) {
		Sign sign = (Sign)loc.getWorld().getBlockAt(loc).getState();
		return sign.getLine(2);
	}
	
	@SuppressWarnings("deprecation")
	public static void setGrade1(Location loc, byte data) {
		loc.add(-1, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(1, -1, -3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade2(Location loc, byte data) {
		loc.add(0, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(0, -1, -3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade3(Location loc, byte data) {
		loc.add(1, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-1, -1, -3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade4(Location loc, byte data) {
		loc.add(2, 1, 2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-2, -1, -2);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade5(Location loc, byte data) {
		loc.add(3, 1, 1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, -1);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade6(Location loc, byte data) {
		loc.add(3, 1, 0);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, 0);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade7(Location loc, byte data) {
		loc.add(3, 1, -1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, 1);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade8(Location loc, byte data) {
		loc.add(2, 1, -2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-2, -1, 2);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade9(Location loc, byte data) {
		loc.add(1, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-1, -1, 3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade10(Location loc, byte data) {
		loc.add(0, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(0, -1, 3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade11(Location loc, byte data) {
		loc.add(-1, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(1, -1, 3);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade12(Location loc, byte data) {
		loc.add(-2, 1, -2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(2, -1, 2);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade13(Location loc, byte data) {
		loc.add(-3, 1, -1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, 1);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade14(Location loc, byte data) {
		loc.add(-3, 1, 0);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, 0);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade15(Location loc, byte data) {
		loc.add(-3, 1, 1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, -1);
	}
	@SuppressWarnings("deprecation")
	public static void setGrade16(Location loc, byte data) {
		loc.add(-2, 1, 2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(2, -1, -2);
		if(data == (byte)0) {
			ArdaCraft.getCraftLogger().logToChat(Level.INFO, "§6[§aKontrollpunkt§6]§c Der Kontrollpunkt " + getID(loc) + " wurde von den Guten eingenommen!");
		}else if(data == (byte)7) {
			ArdaCraft.getCraftLogger().logToChat(Level.INFO, "§6[§aKontrollpunkt§6]§c Der Kontrollpunkt " + getID(loc) + " wurde von den Bösen eingenommen!");
		}
	}
	
	public static void setIron(Location loc) {
		loc.add(0, 1, 0);
		Location modloc = loc;
		modloc.add(0, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);	
		modloc = loc;	
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(2, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(0, 0, -2);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(1, 0, 1);
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = loc;
		modloc.add(-1, 0, 0);
	}
	
}
