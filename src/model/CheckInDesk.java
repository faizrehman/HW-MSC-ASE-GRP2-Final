package model;

import java.util.LinkedList;
import java.util.List;

import exceptions.InValidCheckInException;
import interfaces.QueueObserver;
import interfaces.QueueSubject;

public class CheckInDesk implements QueueSubject,Runnable  {
	private int QueueSpeed=4000;
	private int DeskNumber=0;
	
	//private static CheckInDesk checkInInstance;
	
	AllBooking  AllBookings;
	AllFlight Allflights;

	
	
	
	public CheckInDesk(AllBooking obj,int pDeskNumber,AllFlight pAllflights) {
		AllBookings=obj;
		Allflights=pAllflights;
		DeskNumber=pDeskNumber;
		
	}
	public void setQueueSpeed(int speed)
	{
		QueueSpeed=speed;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try {
				if(AllBookings.getQueueCount()!=0)
				{
				Booking b=AllBookings.getFirstbooking();	
				notifyObservers(b);
				notifyCheckInObservers(b,DeskNumber);
				
				if(Allflights.getFlightByID(b.getFlightCode()).isCheckInOpen()){
						
					AllBookings.UpdateCheckInStatus(b.getBookingReference(), b.getCheckedInWeight(), b.getBaggageDimension());
						Thread.sleep(QueueSpeed);
						notifyFlightBoard(b, b.getFlightCode());
						}
				else
					{
					notifyRejectionBoard(b, b.getFlightCode());					
					Thread.sleep(QueueSpeed);
					}
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InValidCheckInException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e)
			{
				
				
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
			@Override
			public void notifyFlightBoard(Booking obj, String FlightCode) {
				// TODO Auto-generated method stub
				for (QueueObserver obs : registeredObservers)
					obs.updateFlightBoard(obj, FlightCode);
			}
			@Override
			public void notifyRejectionBoard(Booking obj, String FlightCode) {
				// TODO Auto-generated method stub
				for (QueueObserver obs : registeredObservers)
					obs.updateRejectionBoard(obj, FlightCode);
				
			}
			@Override
			public void notifyToOpenCheckInCounter() {
				// TODO Auto-generated method stub
				
			}
			
			
			
	
}
