package com.jensen.Model;
/**
 * 
 * This Class is the EnumTypes for Column Names
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public enum TableColumnEnum {
	/* Employee */
	E_EMPLOYEEID("Employee ID"),
	E_FIRSTNAME("First Name"),
	E_LASTNAME("Last Name"),
	E_ROLE("Role"),
	E_LOCATION("Location"),
	E_REGISTRATIONDATE("Registration Date"),
	
	/* Skill */
	S_SKILLID("Skill ID"),
	S_SKILL("Skill"),
	
	/* Role */
	R_ROLEID("Role ID"),
	R_ROLE("Role"),
	
	/* Location */
	L_LOCATIONID("Location ID"),
	L_LOCATION("Location");
	
	private String columnName;
	/**
	 * A EnumType Constructor that takes a finalized Column Name as a String 
	 * and sets it to the appropriate Enumeration
	 * 
	 * @param columnName Used to define the String of a Column Name
	 */
	TableColumnEnum(final String columnName) {
		this.columnName = columnName;
	}
	/**
	 * This Method is being used whenever this EnumType is being called and will 
	 * return a String with a Column Name Value
	 * 
	 * @return A String with a Column Name Value
	 */
	public String getColumnName() {
		return this.columnName;
	}
	/**
	 * Overrides the toString() Method to return a Column Name directly
	 */
	@Override
    public String toString() {
        return this.getColumnName();
    }
}