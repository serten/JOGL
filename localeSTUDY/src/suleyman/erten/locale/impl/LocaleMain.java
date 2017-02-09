package suleyman.erten.locale.impl;

import suleyman.erten.locale.arabic.ArabicScript;

public class LocaleMain {

	public static void main (String[] args)
	{
		String arabicString = "كيف حالك";
		
		System.out.println("كيف حالك");
	   
		ArabicScript.getInstance().print(arabicString);
	}
}
