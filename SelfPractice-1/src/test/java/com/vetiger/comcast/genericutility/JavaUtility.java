package com.vetiger.comcast.genericutility;

import java.util.Random;

/**
 * 
 * @author DELL
 *
 */
public class JavaUtility {
	/**
	 * Its use to generate RandomNumber
	 * @return int data
	 */
	public int getRandomNum() {
		Random ran=new Random();
		int random=ran.nextInt(1000);
		return random;
	}
}
