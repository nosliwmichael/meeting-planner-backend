package com.meeting.validation;

import java.sql.Timestamp;
import java.util.regex.Pattern;

/**
 * 
 * This class can be used to string together some basic validation objects on any object.
 * 
 * @author Michael Wilson
 *
 */
public class Validator {
	
	private boolean valid = true;
	private Pattern regex;
		
	/**
	 * Checks if the object is not null. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isNotNull(Object object) {
		valid = object != null ? valid : false;
		return this;
	}
	
	/**
	 * Checks if the object is null. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isNull(Object object) {
		valid = object == null ? valid : false;
		return this;
	}

	/**
	 * Checks if the object is of a certain type. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isType(Object object, Class<?> type) {
		this.isNotNull(object);
		if (valid) {
			valid = object.getClass() == type ? valid : false;
		}
		return this;
	}

	/**
	 * Checks if the object is an Integer and equal to a certain amount. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isEqualTo(Object object, int number) {
		this.isType(object, Integer.class);
		if (valid) {
			valid = (int)object == number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}

	/**
	 * Checks if the object is an Integer and less than a certain amount. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isLessThan(Object object, int number) {
		this.isType(object, Integer.class);
		if (valid) {
			valid = (int)object < number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}

	/**
	 * Checks if the object is an Integer and greater than a certain amount. 
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isGreaterThan(Object object, int number) {
		this.isType(object, Integer.class);
		if (valid) {
			valid = (int)object > number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * Checks if the object is a String and equal to a certain length.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isStringLength(Object object, int number) {
		this.isType(object, String.class);
		if (valid) {
			valid = object.toString().length() == number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * Checks if the object is a String and less than a certain length.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isStringLessThan(Object object, int number) {
		this.isType(object, String.class);
		if (valid) {
			valid = object.toString().length() < number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * Checks if the object is a String and longer than a certain length.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isStringLongerThan(Object object, int number) {
		this.isType(object, String.class);
		if (valid) {
			valid = object.toString().length() > number ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * Checks if the object is a Timestamp and comes before a given Timestamp.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isTimeBefore(Object object, Timestamp time) {
		this.isType(object, Timestamp.class);
		if (valid) {
			valid = ((Timestamp)object).before(time) ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * Checks if the object is a Timestamp and comes after a given Timestamp.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isTimeAfter(Object object, Timestamp time) {
		this.isType(object, Timestamp.class);
		if (valid) {
			valid = ((Timestamp)object).after(time) ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}

	/**
	 * Checks if the object is a String and matches an email pattern.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isEmail(Object object) {
		this.isType(object, String.class);
		if (valid) {
			regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			valid = regex.matcher((String)object).find() ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}

	/**
	 * Checks if the object is a String and matches a phone number pattern.
	 * This can be chained with other validation objects.
	 * @return Validator
	 */
	public Validator isPhoneNumber(Object object) {
		this.isType(object, String.class);
		if (valid) {
			regex = Pattern.compile("^(\\+[0-9]{1,3}-)?\\(?[0-9]{3}\\)?\\-?\\(?[0-9]{3}\\)?\\-?\\(?[0-9]{4}\\)?$");
			valid = regex.matcher((String)object).find() ? valid : false;
		} else {
			valid = false;
		}
		return this;
	}
	
	/**
	 * This ends the Validator chain. 
	 * If any one method in the chain was false, this will be false.
	 * This method also resets the "valid" property so that the instance can be reused for future tests.
	 * @return boolean
	 */
	public boolean isValid() {
		boolean result = this.valid;
		this.valid = true;
		return result;
	}
	
	/**
	 * Static method that returns current time as a Timestamp so external classes don't need to import Timestamp.
	 * @return boolean
	 */
	public static Timestamp currentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}