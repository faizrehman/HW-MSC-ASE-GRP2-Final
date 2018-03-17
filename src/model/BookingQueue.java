package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import exceptions.InValidCheckInException;
import interfaces.QueueObserver;
import interfaces.QueueSubject;

public class BookingQueue implements QueueSubject,Runnable {

	private static BookingQueue bookingInstance;
	AllBooking  AllBookings;
	private int QueueSpeed=2000;
	
	private BookingQueue(AllBooking obj) {
		AllBookings=obj;
	}

	public void setQueueSpeed(int speed)
	{
		QueueSpeed=speed;
	}
	
	
	public static synchronized BookingQueue getInstance(AllBooking obj) {
		if (bookingInstance == null) // only if no instance
		synchronized(BookingQueue.class) { // lock block
		if (bookingInstance == null) // and re-check
			bookingInstance = new BookingQueue(obj);
		}
		return bookingInstance;
		}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while(AllBookings.getQueue().peek() != null)
		while(AllBookings.getQueue().peek() != null)
		{
			try {
				Thread.sleep(QueueSpeed);
				Booking b=AllBookings.getQueue().poll();
				Random ran = new Random();
				int randomWeight = ran.nextInt(80);
				int randomLength = ran.nextInt(19)+1;
				int randomWidth = ran.nextInt(19)+1;
				int randomheight = ran.nextInt(19)+1;
				
				
				try {
					
					b.setCheckedInWeight(randomWeight);
					b.setBaggageDimension(String.valueOf(randomLength) +  "x" + String.valueOf(randomWidth) +  "x" + String.valueOf(randomheight));
				
				} catch (InValidCheckInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(b!=null)
				{
					AllBookings.AddinActiveQueue(b);
					notifyObservers(b);
				}
				
			}
			catch (InterruptedException e) {
				System.out.println(e.getMessage());	
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
				
			}


			
}
