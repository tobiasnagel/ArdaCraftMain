package me.tobi.acmain.rasse;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public enum Rasse { //TODO add getAttitude
	
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
			return "Dolch [Item]";
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
			return "Armbrust [Item]";
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
			return "Krummschwert [Item]";
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
			return "Elbenbogen [Item]";
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
			return "Menschenschwert [Item]";
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
			return "Menschenschwert [Item]";
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
			return "Magierstab [Item]";
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
			return "Krummschwert [Item]";
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
			return "Armbust [Item]";
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
		p.addPotionEffects(get(p).getEffects());
	}
	
	public enum Attitude {
		GOOD, BAD, NEUTRAL, UNKNOWN; //TODO braucht man beide???
	}
	
	public static Rasse get(Player p) {
		PermissionUser user = PermissionsEx.getUser(p);
		if (user.getPrefix().contains("Ork")) {
			return Rasse.ORK;
		}
		if (user.getPrefix().contains("Nazgul")) {
			return Rasse.NAZGUL;
		}
		if (user.getPrefix().contains("Urukhai")) {
			return Rasse.URUKHAI;
		}
		if (user.getPrefix().contains("Troll")) {
			return Rasse.TROLL;
		}
		if (user.getPrefix().contains("Wargreiter")) {
			return Rasse.WARGREITER;
		}
		if (user.getPrefix().contains("Ostling")) {
			return Rasse.OSTLING;
		}
		if (user.getPrefix().contains("Elb")) {
			return Rasse.ELB;
		}
		if (user.getPrefix().contains("Mensch")) {
			return Rasse.MENSCH;
		}
		if (user.getPrefix().contains("Magier")) {
			return Rasse.MAGIER;
		}
		if (user.getPrefix().contains("Zwerg")) {
			return Rasse.ZWERG;
		}
		if (user.getPrefix().contains("Hobbit")) {
			return Rasse.HOBBIT;
		}
		if (user.getPrefix().contains("Ent")) {
			return Rasse.ENT;
		}
		if (user.getPrefix().contains("Dunedain")) {
			return Rasse.DUNEDAIN;
		}
		return Rasse.UNREGISTERED;
		
	}
}