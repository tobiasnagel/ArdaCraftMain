package me.tobi.acmain.stadt;

import org.bukkit.Location;


public enum Stadt {
	
	BREE{
		@Override
		public String getName() {return "Bree";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Nanseneut";}
		@Override
		public boolean isGood() {return true;}
	}, BRUCHTAL{
		@Override
		public String getName() {return "Bruchtal";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "chrito_miner";}
		@Override
		public boolean isGood() {return true;}
	}, DAINA{
		@Override
		public String getName() {return "Daina";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "NoobigLP04";}
		@Override
		public boolean isGood() {return true;}
	}, DOLAMROTH{
		@Override
		public String getName() {return "Dol Amroth";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lord_Adrian";}
		@Override
		public boolean isGood() {return true;}
	}, DOLGULDUR{
		@Override
		public String getName() {return "Dol Guldur";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Nanseneut";}
		@Override
		public boolean isGood() {return false;}
	}, DORIATH{
		@Override
		public String getName() {return "Doriath";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "chrito_miner";}
		@Override
		public boolean isGood() {return true;}
	}, EDORAS{
		@Override
		public String getName() {return "Edoras";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lord_Adrian";}
		@Override
		public boolean isGood() {return true;}
	}, ELBIAS{
		@Override
		public String getName() {return "Elbias";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "goghlo";}
		@Override
		public boolean isGood() {return true;}
	}, EREBOR{
		@Override
		public String getName() {return "Erebor";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lordefe";}
		@Override
		public boolean isGood() {return true;}
	}, GALTREV{
		@Override
		public String getName() {return "Galtrev";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "pirchl";}
		@Override
		public boolean isGood() {return false;}
	}, GONDOLIN{
		@Override
		public String getName() {return "Gondolin";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "carsten97";}
		@Override
		public boolean isGood() {return true;}
	}, GUNDABAD{
		@Override
		public String getName() {return "Gundabad";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Soldier_18";}
		@Override
		public boolean isGood() {return false;}
	}, HELMSKLAMM{
		@Override
		public String getName() {return "Helms Klamm";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lord_Adrian";}
		@Override
		public boolean isGood() {return true;}
	}, HOBBINGEN{
		@Override
		public String getName() {return "Hobbingen";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Nanseneut";}
		@Override
		public boolean isGood() {return true;}
	}, ISENGARD{
		@Override
		public String getName() {return "Isengard";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "lordminecraft100";}
		@Override
		public boolean isGood() {return false;}
	}, LORIEN{
		@Override
		public String getName() {return "Lorien";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Shine_ManHD";}
		@Override
		public boolean isGood() {return true;}
	}, MORIA{
		@Override
		public String getName() {return "Moria";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Infernoart";}
		@Override
		public boolean isGood() {return true;}
	}, MORDOR{
		@Override
		public String getName() {return "Mordor";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Nanseneut";}
		@Override
		public boolean isGood() {return false;}
	}, MINASTHIRITH{
		@Override
		public String getName() {return "Minas Thirith";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lord_Adrian";}
		@Override
		public boolean isGood() {return true;}
	},NARGOTHROND{
		@Override
		public String getName() {return "Nargothrond";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Paul_und_Max";}
		@Override
		public boolean isGood() {return true;}
	}, OSGILIATH{
		@Override
		public String getName() {return "Osgiliath";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Lord_Adrian";}
		@Override
		public boolean isGood() {return true;}
	}, SEREDANE{
		@Override
		public String getName() {return "Seredane";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Jusnara";}
		@Override
		public boolean isGood() {return true;}
	}, SEESTADT{
		@Override
		public String getName() {return "Seestadt";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "goghlo";}
		@Override
		public boolean isGood() {return true;}
	}, WETTERSPITZE{
		@Override
		public String getName() {return "Wetterspitze";}
		@Override
		public Location getLoc() {return null;}
		@Override
		public String getOwner() {return "Cubecrafter09";}
		@Override
		public boolean isGood() {return true;}
	};
	
	/*private String name;
	private Location loc;
	private String owner;
	private boolean isGood;
	
	public Stadt(String name, Location loc) {
		this.setName(name);
		this.setLoc(loc);
	}
	
	public Stadt(String name, Location loc, String owner) {
		this.setName(name);
		this.setLoc(loc);
		this.setOwner(owner);
	}

	public Stadt(String name, Location loc, boolean isGood) {
		this.setName(name);
		this.setLoc(loc);
		this.setGood(isGood);
	}
	
	public Stadt(String name, Location loc, String owner, boolean isGood) {
		this.setName(name);
		this.setLoc(loc);
		this.setOwner(owner);	
		this.setGood(isGood);	
	}*/

	public String getName() {
		return null;
	}

	public Location getLoc() {
		return null;
	}

	public String getOwner() {
		return null;
	}

	public boolean isGood() {
		return true;
	}
	
}
