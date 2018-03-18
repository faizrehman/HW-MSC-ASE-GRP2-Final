package interfaces;

import model.Booking;

public interface QueueSubject {

	/**
	 * Register an observer with this subject
	 */
	public void registerObserver(QueueObserver obs);

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(QueueObserver obs);

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers(Booking obj);
	
	public void notifyCheckInObservers(Booking obj,int DeskNumber);
	
	public void notifyFlightBoard(Booking obj,String FlightCode);
	
	
}

