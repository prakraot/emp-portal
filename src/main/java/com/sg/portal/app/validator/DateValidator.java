package com.sg.portal.app.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateValidator {

	private SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	
	public boolean isValidDate(String dateStr) {
			
		try {
			sdf.parse(dateStr.trim());
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public boolean isValidDob(String dateStr) {
		if(isValidDate(dateStr)){
			Date dobDate=null;;
			try {
				dobDate = sdf.parse(dateStr.trim());
				if( dobDate.after(new Date())){
					return false;
				}
				return true;
			} catch (ParseException e) {
				return false;
			}
			
		}
		return false;
	}

}
