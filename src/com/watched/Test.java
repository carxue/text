package com.watched;

public class Test
{
public static void main(String[] args)
{
	ConcreteWatched girl=new ConcreteWatched();
	ConcreteWatcher cw1=new ConcreteWatcher();
	ConcreteWatcher cw2=new ConcreteWatcher();
	ConcreteWatcher cw3=new ConcreteWatcher();
	
	girl.addWatcher(cw1);
	girl.addWatcher(cw2);
	girl.addWatcher(cw3);
	
	girl.notifyWatchers("11111111111");
	
	girl.removeWatcher(cw2);
	girl.notifyWatchers("22222222222");
}
}
