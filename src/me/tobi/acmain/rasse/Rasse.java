package me.tobi.acmain.rasse;

import java.util.ArrayList;
import java.util.List;

import me.tobi.acmain.Methoden;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum Rasse {
	
	ORK{
		@Override
		public String getName() {
			return "Ork";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			//no potion effects => Added by scheduler
			return rtn;
		}
		@Override
		public String getAbility() {
			return "Kampfaxt [Item]";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, NAZGUL{
		@Override
		public String getName() {
			return "Nazgul";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, URUKHAI{
		@Override
		public String getName() {
			return "Urukhai";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
			rtn.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, TROLL{
		@Override
		public String getName() {
			return "Troll";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, WARGREITER{
		@Override
		public String getName() {
			return "Wargreiter";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, OSTLING{
		@Override
		public String getName() {
			return "Ostling";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
			rtn.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.BAD;};
	}, ELB{
		@Override
		public String getName() {
			return "Elb";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "Elbenbogen [Item]";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, MENSCH{
		@Override
		public String getName() {
			return "Mensch";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "Menschenschwer [Item]";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, MAGIER{
		@Override
		public String getName() {
			return "Magier";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 4));
			rtn.add(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
			return rtn; 
		}
		@Override
		public String getAbility() {
			return "Koordinaten anderer spieler herausfinden";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, ZWERG{
		@Override
		public String getName() {
			return "Zwerg";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
			rtn.add(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "Kampfaxt [Item]";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, HOBBIT{
		@Override
		public String getName() {
			return "Hobbit";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "Dolch [Item]";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, ENT{
		@Override
		public String getName() {
			return "Ent";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, DUNEDAIN{
		@Override
		public String getName() {
			return "Dunedain";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> rtn = new ArrayList<PotionEffect>();
			rtn.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
			return rtn;
		}
		@Override
		public String getAbility() {
			return "In Arbeit";
		}
		@Override public Attitude getAttitude(){return Attitude.GOOD;};
	}, UNREGISTERED{
		@Override
		public String getName() {
			return "Unregistriert";
		}
		@Override
		public List<PotionEffect> getEffects() {
			List<PotionEffect> effects = new ArrayList<PotionEffect>();
			return effects;
		}
		@Override
		public String getAbility() {
			return "Nicht vorhanden!";
		}
		@Override public Attitude getAttitude(){return Attitude.UNKNOWN;};
	};
	
	
	public List<PotionEffect> getEffects() {
		List<PotionEffect> effects = new ArrayList<PotionEffect>();
		return effects;
	}
	
	public String getName() {
		return null;
	}
	
	public Attitude getAttitude() {
		return Attitude.UNKNOWN;
	}
	
	public String getAbility() {
		return null;
	}
	
	public static void applyEffects(Player p) {
		p.addPotionEffects(Methoden.getRasse(p).getEffects());
	}
	
	public enum Attitude {
		GOOD, BAD, NEUTRAL, UNKNOWN;
	}	
}