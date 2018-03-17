package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import interfaces.QueueObserver;
import model.AllBooking;
import model.Booking;
import model.BookingQueue;
import model.CheckInDesk;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class QueueDisplay extends JFrame implements QueueObserver {

	private BookingQueue bookingqueue;
	private AllBooking objbooking;
	JTextArea txtrReferenceNoPassenger = new JTextArea();
	
	String[] header = {
		   "Reference No", "Passeneger Name",
		     "Baggage Weight"," Baggage Dimension", "Flight"};
	
	DefaultTableModel dtm = new DefaultTableModel(0, 0);
	JLabel lblNewLabel = new JLabel("There are currently 0 people waiting in the queue:");
	JTable table = new JTable();
	private final JLabel lblSetQueueSpeed = new JLabel("Set Queue Speed");
	private final JTextField textField = new JTextField();
	private final JButton btnSet = new JButton("Set");

	
	
	/**
	 * Create the panel.
	 */
	public QueueDisplay(AllBooking bobj,BookingQueue obj,CheckInDisplay chkin1,CheckInDisplay chkin2) {
		objbooking=bobj;
		textField.setText("2000");
		textField.setColumns(5);
		
		obj.registerObserver(this);
		//chkin1.registerObserver(this);
		
		bookingqueue=obj;
		
		// add header in table model     
				dtm.setColumnIdentifiers(header);
		
				JPanel jpanel=new JPanel();
				jpanel.setPreferredSize(new Dimension(830, 220));
		        
				
				 table.setModel(dtm);
				// table.setPreferredSize(new Dimension(630, 180));
				 
				 table.getColumnModel().getColumn(0).setMinWidth(80);
				 table.getColumnModel().getColumn(1).setMinWidth(130);
				 table.getColumnModel().getColumn(2).setMinWidth(100);
				 table.getColumnModel().getColumn(3).setMinWidth(100);
				 table.getColumnModel().getColumn(4).setMinWidth(100);
		     
	        JScrollPane scroll = new JScrollPane(table);
	        scroll.setPreferredSize(new Dimension(830, 180));
	        scroll.createHorizontalScrollBar();
	        jpanel.add(lblNewLabel);
	       lblSetQueueSpeed.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	       jpanel.add(lblSetQueueSpeed);
	       
	       jpanel.add(textField);
	       jpanel.add(btnSet);
	       jpanel.add(scroll);
	    
	       
	       jpanel.setVisible(true);
	       
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	       setLayout(new BorderLayout());
	     	
			// the display classes DO need to know about the Clock object
			// (to register as observers and to get the data)
			// add analog display in the middle
			add(jpanel, BorderLayout.NORTH);
			// add analog display in the middle
			add(chkin1, BorderLayout.EAST);
			
			add(chkin2, BorderLayout.WEST);
			
			
			setSize(840, 480);
			this.setLocationRelativeTo(null);
		      
			setVisible(true);
	       
	}

	
	// add listener to update button
		public void addSetListener(ActionListener al) {
			btnSet.addActionListener(al);
		}
		
		public int getQueueSpeed()
		{
			return Integer.parseInt(textField.getText());
		}
	

	@Override
	public void update(Booking obj) {
		// TODO Auto-generated method stub
		
	
		dtm.addRow(new Object[] { obj.getBookingReference(), obj.getPassenger().getPassengerFullName() , obj.getCheckedInWeight(),
                obj.getBaggageDimension(), obj.getFlightCode()});
		
		if(!objbooking.getActiveQueue().isEmpty())
		{
		lblNewLabel.setText("There are currently " + objbooking.getActiveQueue().size() + " people waiting in the queue:");
		}
		else
		{

			lblNewLabel.setText("There are currently " + "0" + " people waiting in the queue:");
		}
	}


	


	@Override
	public void updateQueue(Booking obj, int DeskNumber) {
		// TODO Auto-generated method stub
		
	}


	
}