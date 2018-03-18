package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaces.QueueObserver;
import model.Booking;
import model.CheckInDesk;

public class CheckInDisplay extends JPanel implements QueueObserver{
	private CheckInDesk bookingQueue;
	JTextArea textPane = new JTextArea(5,32);
	private static CheckInDisplay CheckInObj;
	
	public CheckInDisplay(CheckInDesk obj, int Counter)
	{
		obj.registerObserver(this);
		bookingQueue=obj;
	
		JPanel jpanel=new JPanel();
		jpanel.setPreferredSize(new Dimension(400, 200));
		JLabel lblNewLabel = new JLabel("Counter " + Counter);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
	    jpanel.add(lblNewLabel);
	     
		
		textPane.setText("Waiting For Passenger...");
		
		
		textPane.setFont(new Font("Serif", Font.ITALIC, 16));
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		
		jpanel.add(textPane);
		
		add(jpanel);
		
		setPreferredSize(new Dimension(400, 200));
		setVisible(true);
	}


	// add listener to update button
			public void addSetListener(ActionListener al) {
				//btnSet.addActionListener(al);
			}

			

			@Override
			public void update(Booking b) {
				// TODO Auto-generated method stub
				try
				{
				textPane.setText(b.getPassenger().getPassengerFullName() + " is dropping off " +
							"1 bag of " + String.valueOf(b.getCheckedInWeight()) + "KG for flight " + b.getFlightCode() +  ". A baggage fee of " + b.getWeightOverCharge() +  " AED is due.");
			
				}catch(Exception ex)
				{}
				}


			


			@Override
			public void updateQueue(Booking obj, int DeskNumber) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void updateFlightBoard(Booking obj, String FlightCode) {
				// TODO Auto-generated method stub
				
			}
}
