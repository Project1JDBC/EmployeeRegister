package com.jensen.Model;

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
	
	TableColumnEnum(final String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return this.columnName;
	}
	
	@Override
    public String toString() {
        return this.getColumnName();
    }
}
