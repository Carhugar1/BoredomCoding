package timeClock;

/**
 * A general class to keep track of the time spent working on something
 * 
 * @author Branden Sammons
 */
public class TimeClock {

	/**
	 * The time clocked in
	 */
	private Time in;
	
	/**
	 * The time clocked out
	 */
	private Time out;
	
	/**
	 * Default Constructor
	 */
	public TimeClock() {
		in = null;
		out = null;
	}
	
	/**
	 * The clock in function of the timeclock.
	 */
	public void clockIn() {
		in = new Time();
	}
	
	/**
	 * The clock out function of the timeclock.
	 * 
	 * throws IllegalStateException if in == null
	 */
	public void clockOut() {
		
		if (in != null) {
			out = new Time();
		}
		
		else {
			throw new IllegalStateException("Error: Cannot clock out without clocking in first.");
		}
	}
	
	/**
	 * Calculates the time between the clock in and clock out in the format specified.
	 * 
	 * throws IllegalStateException if in or out is null
	 * 
	 * @param format the format that you want the method to return. Acceptable ones are seconds, minutes, and hours.
	 * throws IllegalArgumentException if not an acceptable format
	 * 
	 * @return the time between the clock in and clock out in the format specified.
	 */
	public int timeUsed(String format) {
		
		if (in == null || out == null) {
			throw new IllegalStateException("Error: Cannot calculate a null time.");
		}
		
		else {
			
			if (format.equalsIgnoreCase("seconds")) {
				return out.getTotalSeconds() - in.getTotalSeconds();
			}
			
			else if (format.equalsIgnoreCase("minutes")) {
				return out.getTotalMinutes() - in.getTotalMinutes();
			}
			
			else if (format.equalsIgnoreCase("hours")) {
				return out.getTotalHours() - in.getTotalHours();
			}
			
			else {
				throw new IllegalArgumentException("Error: Format " + format + " is not a valid format.");
			}
			
		}
	}
	
}
