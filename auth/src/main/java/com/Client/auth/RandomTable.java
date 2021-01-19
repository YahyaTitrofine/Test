package com.Client.auth;

import java.util.Random;

public class RandomTable {

	
	public static int[] randomtab(final int size)
	{
		Random r = new Random();
		int[] tab = new int[size];
		
		for (int i = 0; i < size; i++) {
			
			tab[i] = r.nextInt(100);
		}
		return tab;
	}
	
}
