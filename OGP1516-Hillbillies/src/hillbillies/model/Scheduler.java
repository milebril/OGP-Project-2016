package hillbillies.model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class Scheduler {

	public Scheduler(){
		//TODO constructor
	}
	
	public void addTask (Task task){
		this.priorityQueue.add(task);
	}
	
	public void removeTask(Task task){
		this.priorityQueue.remove(task);
	}
	
	public void replaceTask (Task oldTask, Task newTask){
		this.priorityQueue.remove(oldTask);
		this.priorityQueue.add(newTask);
	}
	
	public boolean isPartOf(Task task){
		return this.priorityQueue.contains(task);
	}
	
	public Task getHighestPriorityTask(){
		return this.priorityQueue.peek();
	}
	
	public Set<Task> generalMethod(){
		//TODO a general method to return all the tasks that satisfy some condition 
		//(e.g. positive priority, being executing).
		return null;
	}
	
	
	public Iterator<Task> getIterator(){
		return this.priorityQueue.iterator();
	}
	
	/**
	 * priorityQueue registering all tasks in the scheduler.
	 */
	private PriorityQueue<Task> priorityQueue = new PriorityQueue<Task>(10, new Comparator<Task>(){
		public int compare(Task task1, Task task2){
			if(task1.getPriority() > task2.getPriority()) return 1;
			else if(task1.getPriority() < task2.getPriority())return -1;
			else return 0;

		}
	});
}
