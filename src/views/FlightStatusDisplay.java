package views;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaces.QueueObserver;
import model.Booking;
import model.CheckInDesk;

public class FlightStatusDisplay extends JPanel implements QueueObserver{
	private String FlightCode;
	JTextArea textPane = new JTextArea(5,22);
	
	public FlightStatusDisplay(CheckInDesk[] obj, String pFlightCode)
	{
		for(CheckInDesk chk: obj)
		{
			chk.registerObserver(this);
		}
		FlightCode=pFlightCode;
		
		
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
			textPane.append(obj.getBookingReference() + obj.getFlightCode() + "\n");
			
		}
		
	}
	
}
