package JUnitTests;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.*;

import model.AllBooking;
import model.AllFlight;
import model.Booking;
import model.Carrier;
import model.Flight;
import model.Passenger;

public class AllFlightTest {

	
	private AllBooking bookings;
	private AllFlight flights;
	
	@Before
	public void setUp() {
		String bookingsPath="bin/Bookings.txt";
		String flightsPath="bin/FlightsInfo.txt";
		
		PopulateAllBookings(bookingsPath);
		PopulateAllFlights(flightsPath);
	 
	}

	
	@SuppressWarnings("resource")
	public boolean PopulateAllBookings(String filePath)
	{
		// Fetching Data from CSV and initializing and populating bookings object
		
				bookings = AllBooking.getInstance();
				Passenger PassengerData;
				int PassengerId=0;
				BufferedReader buff=null;
				String data []=new String[4];
				
				try {
					buff=new BufferedReader(new FileReader(filePath));
					String inputLine=buff.readLine();
					while(inputLine !=null) {
						data=inputLine.split(",");
						/* Added by Faisal*/
						int variableCount = data.length;
						if(variableCount == 5) 
						{
						
						String bookingReference = data[0].length() == 0 ? "" : data[0];
						String PassengerFName = data[1].length() == 0 ? "" : data[1];
						String PassengerLName = data[2].length() == 0 ? "" : data[2];
						String FlightCode = data[2].length() == 0 ? "" : data[3];
						String CheckIn = data[3].length() == 0 ? "" : data[4];
						
						
						
						PassengerId+=1;
						PassengerData = new Passenger(PassengerId, PassengerFName, PassengerLName);		
						Booking b = new Booking(bookingReference, PassengerData,FlightCode,Boolean.getBoolean(CheckIn));
						
						try {
							bookings.Add(b);
						} catch (Exception e) {
							System.out.println( "booking ref :  " + b.getBookingReference() + " has not added.  " + e.getMessage()  );
							b=null;
						}
						inputLine=buff.readLine();
						} 
						/* Added by Amer*/
						else {
							System.out.println("the booking files is not correct format, please check the formate and then rerun the application");
							return false;
						}
						
					}
					
					
				}
				catch(FileNotFoundException e)
				{
					System.out.println(e.getMessage());
					return false;
					
					
				}
				catch(IllegalArgumentException e) 
				{

					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Booking found with in valid parametes","Alert", JOptionPane.ERROR_MESSAGE);
			    	return false;
					
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return false;
					
					
				}

				return true;
		
	}
	
	@SuppressWarnings("resource")
	public boolean PopulateAllFlights(String filePath)
	{
		// Fetching Data from CSV and initializing and populating bookings object
		
				flights = new AllFlight();
				
				Carrier carrierData;
				int carrierId=0;
				
				BufferedReader buff=null;
				String data []=new String[4];
				
				try {
					buff=new BufferedReader(new FileReader(filePath));
					String inputLine=buff.readLine();
					while(inputLine !=null) {
						data=inputLine.split(",");
						/* Added by Faisal*/
						int variableCount = data.length;
						
						if(variableCount == 5) 
						{
							String FlightCode = data[0].length() == 0 ? "" : data[0];
							String CarrierName = data[1].length() == 0 ? "" : data[1];
							String FlightTime = data[2].length() == 0 ? "" : data[2];
							String MaxAllowedWeight = data[3].length() == 0 ? "" : data[3];	
							String ExtraChargePerKg = data[4].length() == 0 ? "" : data[4];
						/* Added by Amer*/
						carrierId+=1;
						carrierData = new Carrier(carrierId,CarrierName );	
						Flight b = new Flight(FlightCode,carrierData,FlightTime,Integer.parseInt(MaxAllowedWeight),Integer.parseInt(ExtraChargePerKg));
						try {
							flights.Add(b);
						} catch (Exception e) {
							System.out.println( "Flight Code :  " + b.getFlightCode() + " has not added.  " + e.getMessage()  );
							b=null;

						}
						inputLine=buff.readLine();
						
						}
						/* Added by Amer*/
						else {
							System.out.println("the Flight file is not correct format, please check the formate and then rerun the application");
							return false;
						}
					}
					/*Added by Faisal*/
					buff.close();
				}
				catch(FileNotFoundException e)
				{
					System.out.println(e.getMessage());
					return false;
					
					
				}
				catch(IllegalArgumentException e) 
				{

					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Flight Info with in valid parametes","Alert", JOptionPane.ERROR_MESSAGE);
			    	return false;
					
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return false;
					
					
				}
				
				return true;
		
	}
	
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testFlightConstructor() {
		
		String FlightCode="";
		Carrier Carrier=new Carrier(0,"");
		String FlightTime="";
		int MaxAllowedWeight=0;
		int ExtraChargePerKg = 0;
		
		Flight flight=new Flight(FlightCode, Carrier, FlightTime, MaxAllowedWeight,ExtraChargePerKg);
	}
	

}
