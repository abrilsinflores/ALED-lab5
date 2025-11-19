package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */
public class AreaQueue extends Area { 
	
	private Queue<Patient> waitQueue; 
	
	//Constructor
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		this.waitQueue = new LinkedList<Patient>();
	}

	//para entrar numPatients debe ser menor q capacity y Patient el primero de la cola d espera
	@Override
	public synchronized void enter(Patient patient){
		System.out.println("Patient " + patient.getNumber() + " trying to enter " + this.getName());
		this.waiting++;
		this.waitQueue.add(patient); // Add to the end of the queue
		try {
			while (numPatients >= capacity || this.waitQueue.peek() != patient) {
				System.out.println("Patient " + patient.getNumber() + " waiting for " + this.getName()
						+ ". Front of the queue?: " + this.waitQueue.peek().equals(patient));
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt(); // Restore interrupted status
		}
		this.waitQueue.remove(); // Dequeue from the front
		this.numPatients++;
		this.waiting--;
		System.out.println("Patient " + patient.getNumber() + " has entered " + this.name);
	}

}
