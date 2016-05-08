package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class WorkshopPositionExpression extends PositionExpression{

	public WorkshopPositionExpression() {

	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		double[] closest = null;
		int[][][] terraintypes = unit.getWorld().getTerrainType();
		
		for (int x = 0; x < terraintypes.length; x++) {
			for (int y = 0; y < terraintypes[0].length; y++) {
				for (int z = 0; x < terraintypes[0][0].length; x++) {
					if (terraintypes[x][y][z] == 3) {
						double[] temp = {x+0.5,y+0.5,z+0.5};
						if (closest == null) {
							closest = temp;
						} else {
							if (Unit.getDistance(unit.getPosition(), temp) < Unit.getDistance(unit.getPosition(), closest)) {
								closest = temp;
							}
						}
					}
				}
			}
		}
		
		if (closest == null) {
			return null;
		}
		return new TypePosition(closest);
	}
	
	
	
}
