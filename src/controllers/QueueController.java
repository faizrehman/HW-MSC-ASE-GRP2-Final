package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import model.BookingQueue;
import views.QueueDisplay;


public class QueueController {
	private QueueDisplay view; // GUI to allow user to set the time

	private BookingQueue model; // model

	public QueueController(QueueDisplay view, BookingQueue model) {
		this.model = model;
		this.view = view;
		// specify the listener for the view
		view.addSetListener(new SetListener());
	}

	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//int hour = Integer.parseInt(view.getHours());
			//int min = Integer.parseInt(view.getMins());
			model.setQueueSpeed(view.getQueueSpeed());
		}
	}
	
}
