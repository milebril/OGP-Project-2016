package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.programs.expression.AndExpression;
import hillbillies.model.programs.expression.AnyExpression;
import hillbillies.model.programs.expression.BooleanExpression;
import hillbillies.model.programs.expression.BoulderPositionExpression;
import hillbillies.model.programs.expression.CarriesItemExpression;
import hillbillies.model.programs.expression.EnemyExpression;
import hillbillies.model.programs.expression.Expression;
import hillbillies.model.programs.expression.FalseExpression;
import hillbillies.model.programs.expression.FriendExpression;
import hillbillies.model.programs.expression.HereExpression;
import hillbillies.model.programs.expression.IsAliveExpression;
import hillbillies.model.programs.expression.IsEnemyExpression;
import hillbillies.model.programs.expression.IsFriendExpression;
import hillbillies.model.programs.expression.IsPassableExpression;
import hillbillies.model.programs.expression.IsSolidExpression;
import hillbillies.model.programs.expression.LiteralPositionExpression;
import hillbillies.model.programs.expression.LogPositionExpression;
import hillbillies.model.programs.expression.NextToExpression;
import hillbillies.model.programs.expression.NotExpression;
import hillbillies.model.programs.expression.OrExpression;
import hillbillies.model.programs.expression.PositionExpression;
import hillbillies.model.programs.expression.PositionOfExpression;
import hillbillies.model.programs.expression.ThisExpression;
import hillbillies.model.programs.expression.UnitExpression;
import hillbillies.model.programs.expression.WorkshopPositionExpression;
import hillbillies.model.programs.expression.type.TypeBool;
import hillbillies.model.programs.expression.type.TypePosition;
import hillbillies.model.programs.expression.type.TypeUnit;
import hillbillies.model.programs.statement.PrintStatement;
import hillbillies.model.programs.statement.Statement;
import hillbillies.model.programs.statement.WhileStatement;
import hillbillies.model.programs.statement.AssignmentStatement;
import hillbillies.model.programs.statement.AttackStatement;
import hillbillies.model.programs.statement.BreakStatement;
import hillbillies.model.programs.statement.IfStatement;
import hillbillies.model.programs.statement.MoveToStatement;
import hillbillies.model.programs.statement.SequenceStatement;
import hillbillies.model.programs.statement.WorkStatement;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.part3.programs.TaskParser;
import hillbillies.tests.facade.Part3TestPartial;
import javafx.scene.shape.MoveTo;

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
public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

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
		
		if (activity instanceof SequenceStatement) {
			for (Statement statement : ((SequenceStatement) activity).getSequence()) {
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
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
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
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression) condition, body);
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
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new IfStatement((BooleanExpression) condition, ifBody, elseBody);
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
		return new BreakStatement();
	}

	/**
	 * Create a print statement that prints the value obtained by evaluating the
	 * given expression.
	 * 
	 * @param value
	 *            The expression to evaluate and print
	 */
	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
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
	public Statement createMoveTo(Expression<?> position, SourceLocation sourceLocation) {
		return new MoveToStatement((PositionExpression) position);
	}

	/**
	 * Create a work statement
	 * 
	 * @param position
	 *            The position on which to work
	 */
	@Override
	public Statement createWork(Expression<?> position, SourceLocation sourceLocation) {
		return new WorkStatement((PositionExpression) position);
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
	public Statement createAttack(Expression<?> unit, SourceLocation sourceLocation) {
		return new AttackStatement((UnitExpression) unit);
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
	public Expression<TypeBool> createIsSolid(Expression<?> position, SourceLocation sourceLocation) {
		return new IsSolidExpression((PositionExpression) position);
	}
	
	/**
	 * Create an expression that evaluates to true when the given position
	 * evaluates to a passable position; false otherwise.
	 * 
	 * @param position
	 *            The position expression
	 */
	@Override
	public Expression<TypeBool> createIsPassable(Expression<?> position, SourceLocation sourceLocation) {
		return new IsPassableExpression((PositionExpression) position);
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of the same faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression<TypeBool> createIsFriend(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((UnitExpression) unit);
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit of another faction; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression<TypeBool> createIsEnemy(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((UnitExpression) unit);
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that is alive; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression<TypeBool> createIsAlive(Expression<?> unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((UnitExpression) unit);
	}

	/**
	 * Create an expression that evaluates to true when the given unit evaluates
	 * to a unit that carries an item; false otherwise.
	 * 
	 * @param unit
	 *            The unit expression
	 */
	@Override
	public Expression<TypeBool> createCarriesItem(Expression<?> unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((UnitExpression) unit);
	}

	/**
	 * Create an expression that evaluates to true when the given expression
	 * evaluates to false, and vice versa.
	 * 
	 * @param expression
	 */
	@Override
	public Expression<TypeBool> createNot(Expression<?> expression, SourceLocation sourceLocation) {
		return new NotExpression((BooleanExpression) expression);
	}

	/**
	 * Create an expression that evaluates to true when both the left and right
	 * expression evaluate to true; false otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to false.
	 */
	@Override
	public Expression<TypeBool> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new AndExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	/**
	 * Create an expression that evaluates to false only when the left and right
	 * expression evaluate to false; true otherwise.
	 * 
	 * @note short-circuit: the right expression does not need to be evaluated
	 *       when the left expression evaluates to true.
	 */
	@Override
	public Expression<TypeBool> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new OrExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	/**
	 * Create an expression that evaluates to the current position of the unit
	 * that is executing the task.
	 */
	@Override
	public Expression<TypePosition> createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}

	/**
	 * Create an expression that evaluates to the position of a log.
	 * 
	 * @note for groups of two students, this needs to be the log closest to the
	 *       unit that is executing the task.
	 */
	@Override
	public Expression<TypePosition> createLogPosition(SourceLocation sourceLocation) {
		return new LogPositionExpression();
	}

	/**
	 * Create an expression that evaluates to the position of a boulder.
	 * 
	 * @note for groups of two students, this needs to be the boulder closest to
	 *       the unit that is executing the task.
	 */
	@Override
	public Expression<TypePosition> createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderPositionExpression();
	}

	/**
	 * Create an expression that evaluates to the position of a workshop.
	 * 
	 * @note for groups of two students, this needs to be the workshop closest
	 *       to the unit that is executing the task.
	 */
	@Override
	public Expression<TypePosition> createWorkshopPosition(SourceLocation sourceLocation) {
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
	public Expression<TypePosition> createNextToPosition(Expression<?> position, SourceLocation sourceLocation) {
		return new NextToExpression((PositionExpression) position);
	}

	/**
	 * Create an expression that evaluates to the position of the given unit.
	 * 
	 * @param unit
	 */
	@Override
	public Expression<TypePosition> createPositionOf(Expression<?> unit, SourceLocation sourceLocation) {
		return new PositionOfExpression((UnitExpression) unit);
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
	public Expression<TypePosition> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPositionExpression(x, y, z);
	}

	/**
	 * Create an expression that evaluates to the unit that is currently
	 * executing the task.
	 */
	@Override
	public Expression<TypeUnit> createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	/**
	 * Create an expression that evaluates to a unit that is part of the same
	 * faction as the unit currently executing the task.
	 */
	@Override
	public Expression<TypeUnit> createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	/**
	 * Create an expression that evaluates to a unit that is not part of the
	 * same faction as the unit currently executing the task.
	 */
	@Override
	public Expression<TypeUnit> createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	/**
	 * Create an expression that evaluates to any unit (other than this).
	 */
	@Override
	public Expression<TypeUnit> createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	/**
	 * Create an expression that evaluates to true.
	 */
	@Override
	public Expression<TypeBool> createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	/**
	 * Create an expression that evaluates to false.
	 */
	@Override
	public Expression<TypeBool> createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	} 
}
