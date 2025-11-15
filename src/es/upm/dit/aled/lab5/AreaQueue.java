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
	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		this.waitQueue = new LinkedList<Patient>();
	}

	//para entrar numPatients debe ser menor q capacity y Patient el primero de la cola d espera
	@Override
	public synchronized void enter(Patient p){
		int i = 0; //para saber si ha esperado!!!
		//esperamos a q haya un hueco en la sala Y seamos los primeros
		while((this.numPatients == this.capacity)&&(!p.equals(waitQueue.peek()))){ 
			try {
				this.waiting++; 
				i++;
				this.waitQueue.add(p); //añadimos paciente a la cola d espera
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//hemos conseguido entrar
		this.numPatients++;
		this.waitQueue.remove(p); //salimos d la cola
		if(i>0) {this.waiting--;} 
		System.out.println("Patient "+p.getNumber()+ ", has entered to "+p.getLocation().getName());
	}

}
