package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import controllers.CheckInDeskController;
import controllers.QueueController;
import model.AllBooking;
import model.AllFlight;
import model.Booking;
import model.BookingQueue;
import model.Carrier;
import model.CheckInDesk;
import model.Flight;
import model.Passenger;
import views.CheckInDisplay;
import views.FlightStatusDisplay;
import views.QueueDisplay;
import views.RejectionStatusDisplay;


public class MainCheckInCounters {

	private static final int ERROR = 0;
	private static AllBooking bookings;
	private AllFlight flights;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MainCheckInCounters welcome =new MainCheckInCounters();
		welcome.Initialization();
		
	

	}
	
	
	public  void Initialization()
	{
		
		String bookingsPath="bin/Bookings.txt";
		String flightsPath="bin/FlightsInfo.txt";
		
		//Populate all bookings from CSV
		boolean retSuccessFlights = PopulateAllFlights(flightsPath);
		boolean retSuccessBookings = PopulateAllBookings(bookingsPath);
		
		
		
		
		CheckInDesk chkinmodel=new CheckInDesk(bookings,1,flights);
		CheckInDisplay view2 = new CheckInDisplay(chkinmodel,2);	
		CheckInDeskController controller2 =new CheckInDeskController(view2, chkinmodel);
	
		CheckInDesk chkinmodel2=new CheckInDesk(bookings,2,flights);
		CheckInDisplay view3 = new CheckInDisplay(chkinmodel2,1);	
		CheckInDeskController controller3 =new CheckInDeskController(view3, chkinmodel2);
	
		CheckInDesk arrChkInModel[]={chkinmodel,chkinmodel2};
		
		FlightStatusDisplay viewFlightStatusDisplay[]=new FlightStatusDisplay[flights.getAllFlights().size()];	
		int i=0;
		for(Flight f : flights.getAllFlights().values())
		{
			viewFlightStatusDisplay[i++]= new FlightStatusDisplay(arrChkInModel,f.getFlightCode(),bookings,flights);	
		}
			
		
		RejectionStatusDisplay viewRejectionBoard=new RejectionStatusDisplay(arrChkInModel);
		
		
		
		BookingQueue model=BookingQueue.getInstance(bookings,flights);
		QueueDisplay view = new QueueDisplay(bookings,model,view2,view3,viewFlightStatusDisplay,viewRejectionBoard);	
		QueueController controller =new QueueController(view, model);
		
		Thread thread1 =
				new Thread(BookingQueue.getInstance(bookings,flights));
				thread1.start();
	
				
				
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Thread thread2 =
						new Thread(chkinmodel);
						thread2.start();
						
				Thread thread3 =
						new Thread(chkinmodel2);
						thread3.start();

								
								
								
			
	}

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
						
						/* Added by Amer*/
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
							System.exit(ERROR);
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
							System.exit(ERROR);
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
	
	
	

}
