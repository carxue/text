package com.watched;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched
{
	List<Watcher> list=new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher watcher)
	{
		list.add(watcher);

	}

	@Override
	public void removeWatcher(Watcher watcher)
	{
		list.remove(watcher);
  
	}

	@Override
	public void notifyWatchers(String str)
	{
		for(Watcher watcher:list)
		{
			watcher.update(str);
		}

	}

}
