package net.scapeemulator.game.model.membership;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Membership system model
 * 
 * @author my-swagger
 * @version 1.0
 */
public class Membership {
	/**
	 * If the user is a member
	 */
	private boolean member;
	/**
	 * The Calendar to represent the time membership is valid to
	 */
	private Calendar membership;
	
	/**
	 * Constructs a new Membership
	 * By default users are not members and have 0 day membership
	 */
	public Membership() {
		member = false;
		membership = new GregorianCalendar();
	}
	
	/**
	 * Updates the users membership status. 
	 */
	public void update() {
		if (member) {
			Date current = new Date();
			if (current.after(membership.getTime())) {
				member = false;
			}
		}
	}
	
	/**
	 * Extends the membership by the amount of days specified
	 * 
	 * @param days The days to extend the membership by
	 */
	public void extend(int days) {
		if (!member) {
			member = true;
		}
		membership.add(Calendar.DATE, days);
	}
	
	/**
	 * Cancels the users membership status and removes all the days 
	 * they have remaining
	 */
	public void cancel() {
		if (member) {
			member = false;
		}
		membership.clear();
	}
	
	/**
	 * Gets the amount of membership days the user has remaining
	 * 
	 * @return The amount of days remaining
	 */
	public int getDaysLeft() {
	    return (int) ((membership.getTime().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24) + 1);
	}
	
	/**
	 * Gets the membership as a date time
	 * 
	 * @return The date as a String representative
	 */
	public String getEndTimeDate() {
		return new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(membership.getTime());
	}

	/**
	 * Sets the membership status of the user
	 * 
	 * @param member True if the user is a member
	 */
	public void setMember(boolean member) {
		this.member = member;
	}
	
	/**
	 * Gets the current membership status of the user
	 * 
	 * @return membership
	 */
	public boolean isMember() {
		update();
		return member;
	}
}