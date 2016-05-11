package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;

public class AssignmentStatement extends Statement{

	private String name;
	private Expression<?> expression;
	
	public AssignmentStatement(String name, Expression<?> value) {
		super(false);
		this.name = name;
		this.expression = value;
	}
	
	@Override
	public void execute(Unit unit) {
		//TODO weet niet wat hier moet gebeuren
		setExecutedTrue();
	}
	
	public Expression<?> getExpression() {
		return this.expression;
	}
	
	public String getVarName() {
		return this.name;
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
