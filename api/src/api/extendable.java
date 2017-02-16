package api;

public class extendable {

	public static extendable INSTANCE = new extendable();
	
	public extendable() {
		// TODO Auto-generated constructor stub
	}
	
	public static extendable getInstance()
	{
		return INSTANCE;
	}
	
	public void sayHello(){
		System.out.println("extendable: hello");
	}
}
