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
public class AreaQueue extends Area { // TODO
	
	private Queue<Patient> waitQueue; 
	
	//Constructor
	public AreaQueue(String name, int time, int capacity, Position2D position, Queue<Patient> waitQueue) {
		super();
		this.waitQueue = waitQueue;
	}
	
	//añadir un paciente AL FINAL d la cola
	public void add(Patient p) {
		
	}
	
	//Devuelve el Patient que se encuentra al principio de la cola y lo elimina
	public Patient remove() {
		
		return null;
	}
	
	//Devuelve el Patient que se encuentra al principio de la cola
	public Patient peek() {
		
		return null;
	}

	@Override
	public synchronized void enter(Patient p){
		//esperamos hasta q la sala se vacíe y q el paciente sea el primero en la cola d espera
		while((this.numPatients == this.capacity) && ()) { //no pongo >= xq NO puede ser mayor
			try {
				wait(); //no hay q poner p.wait() xq la hebra q espera es qn usa el method
				this.waiting++; //hay un paciente má sesperando
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//hemos conseguido entrar
		this.numPatients++;
		this.waiting--;
	}

}
