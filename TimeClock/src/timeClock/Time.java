package timeClock;

/**
 * A general time class that will keep track of a time in seconds
 * 
 * @author Branden Sammons
 */
public class Time implements Cloneable, Comparable<Object> {
	
	
	/**
	 * The time of the class. (milliseconds)
	 */
	private long time;
	
	
	/**
	 * Constructs a Time object with the time the constructor was called. (seconds)
	 */
	public Time() {
		time = System.currentTimeMillis();
	}
	
	/**
	 * Constructs a Time object with the Date parameter given.
	 * throws NullPointerException if given a null Date.
	 * @param date
	 */
	public Time(long time) {
		if (time > 0) {
			this.time = time;
		}
		
		else {
			throw new IllegalArgumentException("Error: Time cannot be a negative number.");
		}
	}
	
	/**
	 * Returns the Time object in Seconds
	 * @return
	 */
	public int getTotalSeconds() {
		return (int) (time / 1000);
	}
	
	/**
	 * Returns the Time object in seconds since the last minute
	 * @return
	 */
	public int getSeconds() {
		return this.getTotalSeconds() % 60;
	}
	
	/**
	 * Returns the Time object in Minutes
	 * @return
	 */
	public int getTotalMinutes() {
		return this.getTotalSeconds() / 60;
	}
	
	/**
	 * Returns the Time object in minutes since the last hour
	 * @return
	 */
	public int getMinutes() {
		return this.getTotalMinutes() % 60;
	}
	
	/**
	 * Returns the Time object in Hours
	 * @return
	 */
	public int getTotalHours() {
		return this.getTotalSeconds() / 3600;
	}
	
	/**
	 * Returns the Time object in hours since the last hour
	 * @return
	 */
	public int getHours() {
		return this.getTotalHours() % 24;
	}
	
	/**
	 * Creates a copy of this Time.
	 * @return Copy of this Time.
	 */
	@Override
	public Time clone() {
		
		Time clone;
		
		try {
			
			clone = (Time) super.clone();
			clone.time = this.time;
			
		} catch (CloneNotSupportedException e){
			clone = null;
		}
		
		return clone;
	}

	/**
	 * Compares Time objects. returns this Time - arg0
	 * @param Object arg0
	 * @return 
	 */
	@Override
	public int compareTo(Object arg0) {
		if (arg0 == null || arg0.getClass() != this.getClass())
			throw new IllegalArgumentException("Error: Invalid Input");
		
		Time other = (Time) arg0;
		
		return (int) (this.time - other.time);
	}
}
