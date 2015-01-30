package me.tobi.acmain.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemType { //TODO add more ItemTypes
	BOGEN{
		@Override
		public double getDamage() {
			return 15.0;
		}
		@Override
		public Material getMaterial() {
			return Material.BOW;
		}
		@Override
		public String getName() {
			return "Spezialbogen";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.PFEIL;
		}
	}, 
	AXT{
		@Override
		public double getDamage() {
			return 15.0;
		}
		@Override
		public Material getMaterial() {
			return Material.GOLD_AXE;
		}
		@Override
		public String getName() {
			return "Kampfaxt";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.NAHKAMPF;
		}
	}, 
	SCHWERT{
		@Override
		public double getDamage() {
			return 15.0;
		}
		@Override
		public Material getMaterial() {
			return Material.GOLD_SWORD;
		}
		@Override
		public String getName() {
			return "Menschen Schwert";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.NAHKAMPF;
		}
	}, 
	STAB{
		@Override
		public double getDamage() {
			return 12.0;
		}
		@Override
		public Material getMaterial() {
			return Material.BLAZE_ROD;
		}
		@Override
		public String getName() {
			return "Magierstab";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.WITHERKOPF;
		}
	}, 
	DOLCH{
		@Override
		public double getDamage() {
			return 5.0;
		}
		@Override
		public Material getMaterial() {
			return Material.BLAZE_ROD;
		}
		@Override
		public String getName() {
			return "Dolch";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.NAHKAMPF;
		}
	}, 
	RING{
		@Override
		public double getDamage() {
			return 1.0;
		}
		@Override
		public Material getMaterial() {
			return Material.DOUBLE_PLANT;
		}
		@Override
		public String getName() {
			return "Ring";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.NOFIGHT;
		}
	}, 
	ARMBRUST{
		@Override
		public double getDamage() {
			return 15.0;
		}
		@Override
		public Material getMaterial() {
			return Material.BLAZE_ROD;
		}
		@Override
		public String getName() {
			return "Armbrust";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.PFEIL;
		}
	}, 
	KRUMMSCHWERT{
		@Override
		public double getDamage() {
			return 15.0;
		}
		@Override
		public Material getMaterial() {
			return Material.GOLD_SWORD;
		}
		@Override
		public String getName() {
			return "Krummschwert";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.NAHKAMPF;
		}
	}, 
	SPEER{
		@Override
		public double getDamage() {
			return 10.0;
		}
		@Override
		public Material getMaterial() {
			return Material.ARROW;
		}
		@Override
		public String getName() {
			return "Speer";
		}
		@Override
		public WeaponType getWeaponType() {
			return WeaponType.PFEIL;
		}
	}, 
	NORMAL, 
	UNDEFINED{
		@Override
		public double getDamage() {
			return 0.0;
		}
	};
	
	public enum WeaponType {
		UNKNOWN, NAHKAMPF, PFEIL, WITHERKOPF, FEUERBALL, NOFIGHT;
	}
	
	public WeaponType getWeaponType() {
		return WeaponType.UNKNOWN;
	}

	public double getDamage() {
		return 0;
	}
	
	public Material getMaterial() {
		return null;
	}
	
	public String getName() {
		return null;
	}
	
	public static ItemType getType(ItemStack item) {
		if(item == null) {
			return ItemType.UNDEFINED;
		}
		if(!item.hasItemMeta()) {
			return ItemType.NORMAL;
		}
		if(!item.getItemMeta().hasLore()) {
			return ItemType.NORMAL;
		}
		if (item.getItemMeta().getLore().get(0).contains("Spezialitem")) {
			if(item.getItemMeta().hasDisplayName()){ //ALT
				String display = item.getItemMeta().getDisplayName();
				if(display.toUpperCase().contains("ARMBRUST")) {
					return ItemType.ARMBRUST;
				}else if(display.toUpperCase().contains("AXT")) {
					return ItemType.AXT;
				}else if(display.toUpperCase().contains("DOLCH")) {
					return ItemType.DOLCH;
				}else if(display.toUpperCase().contains("STAB")) {
					return ItemType.STAB;
				}else if(display.toUpperCase().contains("BOGEN")) {
					return ItemType.BOGEN;
				}else if(display.toUpperCase().contains("RING")) {
					return ItemType.RING;
				}else if(display.toUpperCase().contains("KRUMMSCHWERT")) {
					return ItemType.KRUMMSCHWERT;
				}else if(display.toUpperCase().contains("SCHWERT")) {
					return ItemType.SCHWERT;
				}else {					
					if(display.toLowerCase().contains("revolver")) {
						return ItemType.ARMBRUST;
					}else if(display.toLowerCase().contains("kampaxt")) {
						return ItemType.AXT;
					}else if(display.toLowerCase().contains("dolch")) {
						return ItemType.DOLCH;
					}else if(display.toLowerCase().contains("magierstab")) {
						return ItemType.STAB;
					}else if(display.toLowerCase().contains("elbenbogen")) {
						return ItemType.BOGEN;
					}else if(display.toLowerCase().contains("ring")) {
						return ItemType.RING;
					}else if(display.toLowerCase().contains("gondors")) {
						return ItemType.SCHWERT;
					}else {
						return ItemType.NORMAL;
					}
				}
			}else {
				if(item.getItemMeta().hasLore()) { //NEU
					String display = item.getItemMeta().getLore().get(1);
					if(display.contains("ARMBRUST")) {
						return ItemType.ARMBRUST;
					}else if(display.toUpperCase().contains("AXT")) {
						return ItemType.AXT;
					}else if(display.toUpperCase().contains("DOLCH")) {
						return ItemType.DOLCH;
					}else if(display.toUpperCase().contains("STAB")) {
						return ItemType.STAB;
					}else if(display.toUpperCase().contains("BOGEN")) {
						return ItemType.BOGEN;
					}else if(display.toUpperCase().contains("RING")) {
						return ItemType.RING;
					}else if(display.toUpperCase().contains("KRUMMSCHWERT")) {
						return ItemType.KRUMMSCHWERT;
					}else if(display.toUpperCase().contains("SCHWERT")) {
						return ItemType.SCHWERT;
					}else {
						return ItemType.NORMAL;
					}
				}
			}
		}
		return null;
	}
}
