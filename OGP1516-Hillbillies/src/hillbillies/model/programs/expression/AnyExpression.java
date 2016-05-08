package hillbillies.model.programs.expression;

import java.util.Random;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class AnyExpression extends UnitExpression{

	public AnyExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		Random rand = new Random();
		int n = rand.nextInt(unit.getWorld().getAmountOfUnits());
		
		Unit temp = (Unit) unit.getWorld().getSetOfUnits().toArray()[n];
		
		while (temp.equals(unit)) {
			n = rand.nextInt(unit.getWorld().getAmountOfUnits());
			temp = (Unit) unit.getWorld().getSetOfUnits().toArray()[n];
		}
		
		return new TypeUnit(temp);
		
	}

}
