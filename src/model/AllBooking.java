package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exceptions.InValidCheckInException;



public class AllBooking  {

	private HashMap<String, Booking> bookingList = new HashMap<String, Booking>();
	private static AllBooking bookingInstance;
	private Queue<Booking> QAllBookings = new LinkedList<Booking>();
	private  Queue<Booking> BookingQue = new LinkedList<Booking>();

	public   int getQueueCount()
	{
		
		return BookingQue.size();
	}
	
	public  void AddinActiveQueue(Booking b)
	{
		
	 BookingQue.add(b);
	
	}
	
	public synchronized  Booking getFirstbooking()
	{
		synchronized(AllBooking.class) { // lock block
			
		return BookingQue.poll();
		}
	}
	
	public  synchronized  Queue<Booking> getActiveQueue()
	{
		synchronized(AllBooking.class) { // lock block
			return BookingQue;
			}
	}
	
	private AllBooking() {
	}

	public  Queue<Booking> getQueue() {
		return QAllBookings;
	}

	
	public static synchronized AllBooking getInstance() {
		if (bookingInstance == null) // only if no instance
		synchronized(AllBooking.class) { // lock block
		if (bookingInstance == null) // and re-check
			bookingInstance = new AllBooking();
		}
		return bookingInstance;
		}
	
	
	
	
	public Booking IsValidBooking(String BookingReference, String PassengerLName) throws IllegalStateException {
		if (BookingReference.trim().length() == 0
				|| !BookingReference.toUpperCase().matches("^[A-Za-z]{3}[0-9]{3}\\z")) {
			throw new IllegalStateException("Booking Reference must be 3 characters followed by 3 digits");

			
		}
		Booking a = bookingList.get(BookingReference.toUpperCase());
		if (a != null) {

			/* Modified by Amer */
			if (a.IsCheckedIn() == false) {

				if (a.getPassenger().getPassengerLName().toLowerCase().equals(PassengerLName.toLowerCase())) {
					return a;
				} else {
					throw new IllegalStateException("Not a valid passenger name");
				}
			} else {
				throw new IllegalStateException("passenger has already checked-in");
			}
		}
		
		
		return null;

	}

	/* Added by Amer */
	public boolean IsValidBookingReference(String BookingReference) throws IllegalStateException {
		if (BookingReference.trim().length() == 0
				|| !BookingReference.toUpperCase().matches("^[A-Za-z]{3}[0-9]{3}\\z")) {
			throw new IllegalStateException("Booking Reference must be 3 characters followed by 3 digits");

		}

		return true;

	}

	/* Added by Amer */
	public boolean IsValidFlightCode(String FlightCode) throws IllegalStateException {
		if (FlightCode.trim().length() == 0 || !FlightCode.toUpperCase().matches("^[A-Za-z]{2}[0-9]{3}\\z")) {
			throw new IllegalStateException("Flight Code must be 2 characters followed by 3 digits");

		}

		return true;

	}

	public boolean UpdateCheckInStatus(String BookingReference, Integer CheckedInWeight, String BaggageDimension)
			throws InValidCheckInException {
		Booking a = bookingList.get(BookingReference);
		if (a != null) {
			bookingList.get(BookingReference).SetCheckedIn(CheckedInWeight, BaggageDimension);

			return true;
		}
		return false;

	}

	public void Add(Booking a) throws Exception {
		/* Modified by Amer */
		if (IsValidBookingReference(a.getBookingReference()))
			if (IsValidFlightCode(a.getFlightCode()))
			{
				bookingList.put(a.getBookingReference(), a);
				QAllBookings.add(a);
			}
	}

	public String BookingDetails() {
		StringBuffer allEntries = new StringBuffer();

		for (Booking details : bookingList.values()) {
			allEntries.append(details.getBookingReference() + "		" + details.getPassenger().getPassengerFName() + " "
					+ details.getPassenger().getPassengerLName() + "		" + details.IsCheckedIn());
			allEntries.append('\n');
		}
		return allEntries.toString();

	}

	public HashMap<String, Booking> getAllBookings() {
		return bookingList;
	}
	
}
