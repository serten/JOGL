package impl.impl;

import impl.extender;

public class Impl {

	extender e = new extender();
	
	public  Impl(){
		e.getInstance().sayHello();
	}
}
