package interfaces;

import model.Booking;

public interface QueueObserver {

	/**
	 * Tell Observer to update itself
	 */
	public void update(Booking obj);
	
	public void updateQueue(Booking obj,int DeskNumber);
	
}

