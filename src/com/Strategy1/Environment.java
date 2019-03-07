package com.Strategy1;

import java.util.List;

public class Environment
{
    private SortInterface sortinterface;
    public Environment(SortInterface sortinterface)
    {
    	this.sortinterface=sortinterface;
    }
  public void sort(List<Pepole>list)
  {
	  this.sortinterface.sort(list);
  }
  public void setSortInterface(SortInterface sortinterface)
  {
	  this.sortinterface=sortinterface;
  }
}
