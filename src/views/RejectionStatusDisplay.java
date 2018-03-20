package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interfaces.QueueObserver;
import model.Booking;
import model.CheckInDesk;

public class RejectionStatusDisplay  extends JPanel implements QueueObserver{
	private CheckInDesk bookingQueue;
	JTextArea textPane = new JTextArea(15,32);
	
	
public RejectionStatusDisplay(CheckInDesk objCheckInDesk[])
{
	
	
	for(CheckInDesk objinn:objCheckInDesk)
	{
		objinn.registerObserver(this);
		
	}
 
	
	JPanel jpanel=new JPanel();
	jpanel.setPreferredSize(new Dimension(1240, 200));
	JLabel lblNewLabel = new JLabel("Rejected Passenger");
	lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
    jpanel.add(lblNewLabel);
     
    JScrollPane scroll = new JScrollPane(textPane);
    scroll.setPreferredSize(new Dimension(1230, 180));
    scroll.createHorizontalScrollBar();
    jpanel.add(lblNewLabel); 
    jpanel.add(scroll);
    
    
    textPane.append("REF #" + "	" +  "FLIGHT"
	 + "	" + "STATUS" + "	" + "REASON" + "\n");
	add(jpanel);
	
	setPreferredSize(new Dimension(1240, 200));
	setVisible(true);
	
	
}

//add listener to update button
			public void addSetListener(ActionListener al) {
				//btnSet.addActionListener(al);
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
public void updateFlightBoard(Booking obj, String FlightCode) {
	// TODO Auto-generated method stub
	
}


@Override
public void updateRejectionBoard(Booking obj, String FlightCode) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub

	try
	{
		//JOptionPane.showMessageDialog(this,FlightCode,"Alert", JOptionPane.ERROR_MESSAGE);
		
	textPane.append(obj.getBookingReference() + "	" +  obj.getFlightCode()
	 + "	" + "Rejected" + "	" +  "Check-In closed" + "\n");
	}catch(Exception ex)
	{}
	}

@Override
public void OpenCheckInCounter() {
	// TODO Auto-generated method stub
	
}
}
	
