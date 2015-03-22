package me.tobi.acmain.controlpoints;

import me.tobi.acmain.ArdaCraft;
import me.tobi.acmain.message.CraftLogger.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R2.block.CraftSign;

public class ControlPoint {
	
	Location l;
	String name;
	int state; //-16 bis 16
	
	public ControlPoint(Location loc, String name) {
		l = loc;
		this.name = name;
		state = 0;
		reset();
	}
	
	public void reset(){
		setSign();
		setBase();
		setState(0);
		setBeacon();
	}
	
	private void setBeacon() {
		l.add(0, 2, 0);
		l.getWorld().getBlockAt(l).setType(Material.BEACON);
		l.add(0, -2, 0);
	}
	
	private void setSign() {
		l.getWorld().getBlockAt(l).setType(Material.SIGN_POST);
		Sign sign = (Sign)l.getWorld().getBlockAt(l).getState();
		sign.setLine(0, "[CP]");
		sign.setLine(1, "0");
		sign.setLine(2, name);
		sign.update();
	}
	
	public Location getLocation() {
		return this.l;
	}
	
	public void setState(int state) {
		this.state = state;
		updateState();
	}
	
	public int getState() {
		return state;
	}
	
	private void updateState() {
		if(!(state > 16 || state < -16)) { 
			clearPoint(l);
			l.add(0, -1, 0);
			System.out.println(l.getWorld().getBlockAt(l).getState());
			Sign sign = (CraftSign)l.getWorld().getBlockAt(l).getState();
			sign.setLine(1, String.valueOf(state));
			sign.update();
			l.add(0, 1, 0);
			l.getWorld().playSound(l, Sound.ANVIL_LAND, 5.0F, 5.0F);
			if(state > 0) {
				byte dat = 0;
				if(state >= 1) {
					setState1(l, dat);
				}if(state >= 2) {
					setState2(l, dat);
				}if(state >= 3) {
					setState3(l, dat);
				}if(state >= 4) {
					setState4(l, dat);
				} if(state >= 5) {
					setState5(l, dat);
				} if(state >= 6) {
					setState6(l, dat);
				} if(state >= 7) {
					setState7(l, dat);
				} if(state >= 8) {
					setState8(l, dat);
				} if(state >= 9) {
					setState9(l, dat);
				} if(state >= 10) {
					setState10(l, dat);
				} if(state >= 11) {
					setState11(l, dat);
				} if(state >= 12) {
					setState12(l, dat);
				} if(state >= 13) {
					setState13(l, dat);
				} if(state >= 14) {
					setState14(l, dat);
				} if(state >= 15) {
					setState15(l, dat);
				} if(state >= 16) {
					setState16(l, dat);
				}
			}else {
				byte dat = 7;
				
				if(state <= -1) {
					setState1(l, dat);
				} if(state <= -2) {
					setState2(l, dat);
				} if(state <= -3) {
					setState3(l, dat);
				} if(state <= -4) {
					setState4(l, dat);
				} if(state <= -5) {
					setState5(l, dat);
				} if(state <= -6) {
					setState6(l, dat);
				} if(state <= -7) {
					setState7(l, dat);
				} if(state <= -8) {
					setState8(l, dat);
				} if(state <= -9) {
					setState9(l, dat);
				} if(state <= -10) {
					setState10(l, dat);
				} if(state <= -11) {
					setState11(l, dat);
				} if(state <= -12) {
					setState12(l, dat);
				} if(state <= -13) {
					setState13(l, dat);
				} if(state <= -14) {
					setState14(l, dat);
				} if(state <= -15) {
					setState15(l, dat);
				} if(state <= -16) {
					setState16(l, dat);
				}
			}		
		}else {
			state = 0;
		}
	}
	
	private void clearPoint(Location loc) {
		Location modloc = loc;
		setState1(modloc, (byte)8);
		modloc = loc;
		setState2(modloc, (byte)8);
		modloc = loc;
		setState3(modloc, (byte)8);
		modloc = loc;
		setState4(modloc, (byte)8);
		modloc = loc;
		setState5(modloc, (byte)8);
		modloc = loc;
		setState6(modloc, (byte)8);
		modloc = loc;
		setState7(modloc, (byte)8);
		modloc = loc;
		setState8(modloc, (byte)8);
		modloc = loc;
		setState9(modloc, (byte)8);
		modloc = loc;
		setState10(modloc, (byte)8);
		modloc = loc;
		setState11(modloc, (byte)8);
		modloc = loc;
		setState12(modloc, (byte)8);
		modloc = loc;
		setState13(modloc, (byte)8);
		modloc = loc;
		setState14(modloc, (byte)8);
		modloc = loc;
		setState15(modloc, (byte)8);
		modloc = loc;
		setState16(modloc, (byte)8);
		modloc = loc;
	}
	
	public void addGoodGrade() {
		if(state>=0) {
			setState(state + 1);
		}else {
			setState(state + 1);
		}		
	}
	
	public void addBadGrade() {
		if(state<=0) {
			setState(state - 1);
		}else {
			setState(state - 1);
		}
		
	}

	private void setBase() {
		l.add(0, 1, 0);
		Location modloc = l;
		modloc.add(0, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, 1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, -1);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(2, 0, 0);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(0, 0, -2);//
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(1, 0, 1);
		modloc.getWorld().getBlockAt(modloc).setType(Material.IRON_BLOCK);
		modloc = l;
		modloc.add(-1, 0, 0);

	}
	
	@SuppressWarnings("deprecation")
	private void setState1(Location loc, byte data) {
		loc.add(-1, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(1, -1, -3);
	}
	@SuppressWarnings("deprecation")
	private void setState2(Location loc, byte data) {
		loc.add(0, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(0, -1, -3);
	}
	@SuppressWarnings("deprecation")
	private void setState3(Location loc, byte data) {
		loc.add(1, 1, 3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-1, -1, -3);
	}
	@SuppressWarnings("deprecation")
	private void setState4(Location loc, byte data) {
		loc.add(2, 1, 2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-2, -1, -2);
	}
	@SuppressWarnings("deprecation")
	private void setState5(Location loc, byte data) {
		loc.add(3, 1, 1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, -1);
	}
	@SuppressWarnings("deprecation")
	private void setState6(Location loc, byte data) {
		loc.add(3, 1, 0);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, 0);
	}
	@SuppressWarnings("deprecation")
	private void setState7(Location loc, byte data) {
		loc.add(3, 1, -1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-3, -1, 1);
	}
	@SuppressWarnings("deprecation")
	private void setState8(Location loc, byte data) {
		loc.add(2, 1, -2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-2, -1, 2);
	}
	@SuppressWarnings("deprecation")
	private void setState9(Location loc, byte data) {
		loc.add(1, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(-1, -1, 3);
	}
	@SuppressWarnings("deprecation")
	private void setState10(Location loc, byte data) {
		loc.add(0, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(0, -1, 3);
	}
	@SuppressWarnings("deprecation")
	private void setState11(Location loc, byte data) {
		loc.add(-1, 1, -3);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(1, -1, 3);
	}
	@SuppressWarnings("deprecation")
	private void setState12(Location loc, byte data) {
		loc.add(-2, 1, -2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(2, -1, 2);
	}
	@SuppressWarnings("deprecation")
	private void setState13(Location loc, byte data) {
		loc.add(-3, 1, -1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, 1);
	}
	@SuppressWarnings("deprecation")
	private void setState14(Location loc, byte data) {
		loc.add(-3, 1, 0);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, 0);
	}
	@SuppressWarnings("deprecation")
	private void setState15(Location loc, byte data) {
		loc.add(-3, 1, 1);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(3, -1, -1);
	}
	@SuppressWarnings("deprecation")
	private void setState16(Location loc, byte data) {
		loc.add(-2, 1, 2);
		loc.getWorld().getBlockAt(loc).setType(Material.WOOL);
		loc.getWorld().getBlockAt(loc).setData((byte)data);
		loc.add(2, -1, -2);
		if(data == (byte)0) {
			ArdaCraft.getCraftLogger().logToChat(Level.INFO, "§6[§aKontrollpunkt§6]§c Der Kontrollpunkt " + this.name + " wurde von den Guten eingenommen!");
		}else if(data == (byte)7) {
			ArdaCraft.getCraftLogger().logToChat(Level.INFO, "§6[§aKontrollpunkt§6]§c Der Kontrollpunkt " + this.name + " wurde von den Bösen eingenommen!");
		}
	}
}
