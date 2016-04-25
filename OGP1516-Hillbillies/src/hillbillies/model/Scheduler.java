package hillbillies.model;

import java.util.Set;

public class Scheduler {

	public Scheduler(){
		//TODO constructor
	}
	
	public void addTask (Task task){
		//TODO add task
	}
	
	public void removeTask(Task task){
		//TODO remove task
	}
	
	public void replaceTask (Task oldTask, Task newTask){
		//TODO replace old task with new task
	}
	
	public boolean isPartOf(Task task){
		//TODO check if the given task is a part of the scheduler
		return false;
	}
	
	public Task getHighestPriorityTask(){
		//TODO return the task with the highest priority
		return null;
	}
	
	public Set<Task> generalMethod(){
		//TODO a general method to return all the tasks that satisfy some condition 
		//(e.g. positive priority, being executing).
		return null;
	}
	
	
	
}
