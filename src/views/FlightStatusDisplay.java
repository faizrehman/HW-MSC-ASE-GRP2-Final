package views;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaces.QueueObserver;
import model.AllBooking;
import model.AllFlight;
import model.Booking;
import model.CheckInDesk;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlightStatusDisplay extends JPanel implements QueueObserver{
	private String FlightCode;
	AllBooking objbookings;
	JTextArea textPane = new JTextArea(5,22);
	
	public FlightStatusDisplay(CheckInDesk[] obj, String pFlightCode,AllBooking pobjbookings,AllFlight objflights)
	{
		

		
		for(CheckInDesk chk: obj)
		{
			chk.registerObserver(this);
		}
		FlightCode=pFlightCode;
		objbookings=pobjbookings;
		
		JPanel jpanel=new JPanel();
		jpanel.setPreferredSize(new Dimension(270, 200));
		JLabel lblNewLabel = new JLabel("Flight: " + pFlightCode);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
	    jpanel.add(lblNewLabel);
		
		textPane.setFont(new Font("Serif", Font.ITALIC, 16));
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		
		jpanel.add(textPane);
		add(jpanel);
		
		JButton btnNewButton = new JButton("CheckIn Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					objflights.getFlightByID(FlightCode).setCheckInOpen(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textPane.enable(false);
			}
		});
		jpanel.add(btnNewButton);
		
		setPreferredSize(new Dimension(270, 300));
		setVisible(true);

		
	}

	@Override
	public void update(Booking obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQueue(Booking obj, int DeskNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFlightBoard(Booking obj, String pFlightCode) {
		// TODO Auto-generated method stub
		if(FlightCode.equals(pFlightCode.trim()))
		{
			HashMap<String, Booking> objreturned = objbookings.BookingsByFlightCode(FlightCode);
			int count=0;
			int totalcount=objreturned.size();
			for(Booking value: objreturned.values()) {
				  if (value.IsCheckedIn()==true) {
				    count++;
				  }
				}
			
			textPane.setText( pFlightCode + "\n" +
					count + " checked-in of " + objreturned.size() + "\n" +
					"Hold is " + (count*100/totalcount) + "%");
				
			
			
			
		}
		
	}

	@Override
	public void updateRejectionBoard(Booking obj, String FlightCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OpenCheckInCounter() {
		// TODO Auto-generated method stub
		
	}
	
}
