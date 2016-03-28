package hillbillies.model;

import java.util.Set;

public class Faction {
	
	private Set<Faction> listOfFactions;
	private Set<Unit> unitsInFaction;
	
	public Faction() {
		
	}
	
	public Faction getFaction(Unit unit) {
		for (Faction f : listOfFactions) {
			for (Unit u : f.unitsInFaction) {
				if (u == unit) {
					return f;
				}
			}
		}
		return null;
	}
	
}
