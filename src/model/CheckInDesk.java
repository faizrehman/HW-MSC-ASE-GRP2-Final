package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.QueueObserver;
import interfaces.QueueSubject;

public class CheckInDesk implements QueueSubject,Runnable  {
	private int QueueSpeed=4000;
	private int DeskNumber=0;
	
	//private static CheckInDesk checkInInstance;
	
	AllBooking  AllBookings;

	
	
	
	public CheckInDesk(AllBooking obj,int pDeskNumber) {
		AllBookings=obj;
		DeskNumber=pDeskNumber;
	}
	public void setQueueSpeed(int speed)
	{
		QueueSpeed=speed;
	}
	//public int getQueueCount()
	//{
	//	return bookingQueue.getQueueCount();
	//}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try {
				Booking b=AllBookings.getFirstbooking();
				notifyObservers(b);
				notifyCheckInObservers(b,DeskNumber);
				Thread.sleep(QueueSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

	// OBSERVER PATTERN
			// SUBJECT must be able to register, remove and notify observers
			// list to hold any observers
			private List<QueueObserver> registeredObservers = new LinkedList<QueueObserver>();

			// methods to register, remove and notify observers
			public void registerObserver(QueueObserver obs) {
				registeredObservers.add(obs);
			}

			public void removeObserver(QueueObserver obs) {
				registeredObservers.remove(obs);
			}

			public void notifyObservers(Booking obj) {
				for (QueueObserver obs : registeredObservers)
					obs.update(obj);
			}

			@Override
			public void notifyCheckInObservers(Booking obj, int DeskNumber) {
				// TODO Auto-generated method stub
				for (QueueObserver obs : registeredObservers)
					obs.updateQueue(obj,DeskNumber);
			}
			
			
			
	
}
