package com.daili;

public class ProSubject extends Subject
{
	private RealSubject realSubject;

	public void request()
	{
		this.Prerequest();
		if (null == realSubject)
		{
			realSubject=new RealSubject();
		}
		realSubject.request();
		this.Afterrequest();
	}

	public void Prerequest()
	{
		System.out.println("�뷿������");
	}

	public void Afterrequest()
	{
		System.out.println("����͵�Ҫ��");
	}

}
