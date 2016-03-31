package hillbillies.model;

import java.util.*;

public class Faction {
	
	public Set<Unit> unitsInFaction;
	
	/* constructor */
	public Faction() {
		unitsInFaction = new LinkedHashSet<Unit>();
	}
	
	public void addUnitToFaction(Unit unit) {
		unitsInFaction.add(unit);
	}
	
	public Set<Unit> getUnitsInFaction() {
		return unitsInFaction;
	}
	
	public void removeUnitFromFaction(Unit unit){
		unitsInFaction.remove(unit);
	}
}
