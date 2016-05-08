package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.programs.expression.Expression;
import hillbillies.model.programs.expression.WorkshopPositionExpression;
import hillbillies.model.programs.statement.Statement;
import hillbillies.model.programs.statement.sequenceStatement;
import hillbillies.model.programs.statement.workStatement;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.part3.programs.TaskParser;
import hillbillies.tests.facade.Part3TestPartial;

/**
 * A task factory is used by the parser ({@link TaskParser}) to construct an
 * in-memory representation of your program. For example, when reading the task
 * description
 * 
 * <pre>
 * name: "test"
 * priority: 1
 * activities: moveTo here;
 * </pre>
 * 
 * the parser will create a Task object by (conceptually) executing the
 * following code:
 * 
 * <pre>
 * factory.createTask("test", 1, factory.createMoveTo(factory.createHerePosition()))
 * </pre>
 * 
 * on the returned factory object.
 * 
 * <p>
 * For testing, you may use the methods from {@link TaskParser} yourself, as
 * demonstrated in the partial test file {@link Part3TestPartial}.
 * 
 * <p>
 * You should declare your class as follows:<code><pre>
 * public class TaskFactory implements ITaskFactory&lt;MyExpression, MyStatement, Task&gt;
 * </pre></code> where MyExpression and MyStatement are your classes for
 * representing expressions and statements, respectively.
 * 
 * <p>
 * The SourceLocation object in the methods defined by this factory refers to
 * the location (line and column) in the text file where the statement or
 * expression begins.
 * 
 * @param <E>
 *            Your class for representing an expression.
 * @param <S>
 *            Your class for representing a statement.
 * @param <T>
 *            Your class for representing a task (should be Task).
 * 
 * 
 */
public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {

	/* TASKS */

	/**
	 * Create a list of tasks from the given arguments.
	 * 
	 * @param name
	 *            The name of the task
	 * @param priority
	 *            The initial priority of the task
	 * @param activity
	 *            The activity of the task. Most likely this is a sequence
	 *            statement.
	 * @param selectedCubes
	 *            A list of cube coordinates (each represented as an array {x,
	 *            y, z}) that were selected by the player in the GUI.
	 * @return A list of new task instances. One task instance should be created
	 *         for each selectedCube coordinate. If selectedCubes is empty and
	 *         the 'selected' expression does not occur in the activity, a list
	 *         with exactly one Task instance should be returned.
	 */

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) { //TODO selectedCubes
		System.out.println("[createTasks] " + activity.whoAmI());
		
		List<Task> taskList = new ArrayList<Task>();
		
		if (activity instanceof sequenceStatement) {
			for (Statement statement : ((sequenceStatement) activity).getSequence()) {
				taskList.add(new Task(name, priority, statement));
			}
		} else {
			taskList.add(new Task(name, priority, activity));
		}
		
		return taskList;
	}

	/* STATEMENTS */

	/**
	 * Create a statement that represents the assignment of a variable.
	 * 
	 * @param variableName
	 *            The name of the assigned variable
	 * @param value
	 *            An expression that evaluates to the assigned value
	 */
	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a statement that represents a while loop.
	 * 
	 * @param condition
	 *            The condition of the while loop
	 * @param body
	 *            The body of the loop (most likely this is a sequence
	 *            statement).
	 */
	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an if-then-else statement.
	 * 
	 * @param condition
	 *            The condition of the if statement
	 * @param ifBody
	 *            * The body of the if-part, which must be executed when the
	 *            condition evaluates to true
	 * @param elseBody
	 *            The body of the else-part, which must be executed when the
	 *            condition evaluates to false. Can be null if no else clause is
	 *            specified.
	 */
	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a break statement that immediately terminates the enclosing loop.
	 * 
	 * @param sourceLocation
	 * 
	 * @note Students working alone may return null.
	 */
	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a print statement that prints the value obtained by evaluating the
	 * given expression.
	 * 
	 * @param value
	 *            The expression to evaluate and print
	 */
	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a sequence of statements.
	 * 
	 * @param statements
	 *            The statements that must be executed in the given order.
	 */
	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a moveTo statement
	 * 
	 * @param position
	 *            The position to which to move
	 */
	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a work statement
	 * 
	 * @param position
	 *            The position on which to work
	 */
	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new workStatement(position);
	}

	/**
	 * Create a follow statement
	 * 
	 * @param unit
	 *            The unit to follow
	 */
	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an attack statement
	 * 
	 * @param unit
	 *            The unit to attack
	 */
	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* EXPRESSIONS */

	/**
	 * Create an expression that evaluates to the current value of the given
	 * variable.
	 * 
	 * @param variableName
	 *            The name of the variable to read.
	 */
	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given position
	 * evaluates to a solid position; false otherwise.
	 * 
	 * @param position
	 *            The position expression
	 */
	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Create an expression that evaluates to true when the given position
	 * evaluates to a passable position; false otherwise.
	 * 
	 * @param position
	 *            The position expression
	 */
	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of the same faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of another faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that is alive; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that carries an item; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when the given expression
	 * evaluates to false, and vice versa.
	 * 
	 * @param expression
	 */
	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true when both the left and right
	 * expression evaluate to true; false otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to false.
	 */
	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to false only when the left and right
	 * expression evaluate to false; true otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to true.
	 */
	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the current position of the unit
	 * that is executing the task.
	 */
	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a log.
	 * 
	 * @note for groups of two students, this needs to be the log closest to the
	 *       unit that is executing the task.
	 */
	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a boulder.
	 * 
	 * @note for groups of two students, this needs to be the boulder closest to
	 *       the unit that is executing the task.
	 */
	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of a workshop.
	 * 
	 * @note for groups of two students, this needs to be the workshop closest
	 *       to the unit that is executing the task.
	 */
	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression();
	}

	/**
	 * Create an expression that evaluates to the position selected by the user
	 * in the GUI.
	 * 
	 * @note Students working alone may return null.
	 */
	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to a position next to the given
	 * position.
	 * 
	 * @param position
	 * 
	 */
	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the position of the given unit.
	 * 
	 * @param unit
	 */
	@Override
	public Expression createPositionOf(Expression unit, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to a static position with a given
	 * coordinate.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to the unit that is currently
	 * executing the task.
	 */
	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to a unit that is part of the same
	 * faction as the unit currently executing the task.
	 */
	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to a unit that is not part of the
	 * same faction as the unit currently executing the task.
	 */
	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to any unit (other than this).
	 */
	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to true.
	 */
	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create an expression that evaluates to false.
	 */
	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}  //TODO moet <Expression, Statement, Task zijn>
	
}
