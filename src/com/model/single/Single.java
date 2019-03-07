package com.model.single;

public class Single {
	private static Single single = new Single();

	private Single() {
	}

	public static Single newInstance() {
		return single;
	}

	public static void main(String[] args) {
		Single s1 = Single.single;
		Single s2 = Single.newInstance();
		Single s3 = Single.newInstance();
		if(s3.equals(s2)){
			System.out.println("s3==s2 "+s3.hashCode()+":"+s2.hashCode());
		}
	}
}
