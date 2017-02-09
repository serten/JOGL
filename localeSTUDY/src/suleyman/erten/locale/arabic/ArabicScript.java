package suleyman.erten.locale.arabic;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ArabicScript {

	private static ArabicScript INSTANCE = new ArabicScript();
	
	private ArabicScript()
	{
		
	}
	
	public static ArabicScript getInstance()
	{
		return INSTANCE;
	}
	public void print(String msg, Object... args) {
	    try {
	        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
	        ps.println(String.format(msg, args));
	    } catch (UnsupportedEncodingException error) {
	        System.err.println(error);
	        System.exit(0);
	    }
	}
	
}
