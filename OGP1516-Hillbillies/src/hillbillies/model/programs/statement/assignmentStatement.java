package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;

public class AssignmentStatement extends Statement{

	private String name;
	private Expression expression;
	
	public AssignmentStatement(String name, Expression value) {
		super(true);
		this.name = name;
		this.expression = value;
	}
	
	@Override
	public void execute(Unit unit) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getAssignmentName() {
		return name;
	}

	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
