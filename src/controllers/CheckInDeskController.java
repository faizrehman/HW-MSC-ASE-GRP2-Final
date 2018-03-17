package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.CheckInDesk;
import views.CheckInDisplay;
import views.QueueDisplay;

public class CheckInDeskController {
	private CheckInDisplay view; // GUI to allow user to set the time

	private CheckInDesk model; // model

	public CheckInDeskController(CheckInDisplay view, CheckInDesk model) {
		this.model = model;
		this.view = view;
		// specify the listener for the view
		view.addSetListener(new SetListener());
	}

	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//int hour = Integer.parseInt(view.getHours());
			//int min = Integer.parseInt(view.getMins());
			//model.setQueueSpeed(view.getQueueSpeed());
		}
	}
}
