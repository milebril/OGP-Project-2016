package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.UnitExpression;

public class AttackStatement extends Statement{

	private UnitExpression exp;
	
	public AttackStatement(UnitExpression e) {
		super (false);
		this.exp = e;
	}
	
	public Unit getUnitExpression(Unit unit) {
		return (Unit) exp.evaluate(unit).getType();
	}
	
	@Override
	public void execute(Unit unit) {
		getUnitExpression(unit).getName();
		unit.startAttacking(getUnitExpression(unit));
		setExecutedTrue();
	}

	@Override
	public boolean canExecute(Unit unit) {
		if (isExcecuted()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

}
