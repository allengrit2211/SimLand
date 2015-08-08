package com.simland.core.base;

import java.util.ArrayList;
import java.util.List;

/****
 * 笛卡尔积
 * 
 * @author Gavin
 * @Date 2015-08-08 07:23
 * 
 *
 */
public class Cartesian {

	private static String[][] xyz = null;
	private static int counterIndex;
	private static int[] counter;

	public static void main(String[] args) throws Exception {

		String[] aa = { "aa1", "aa2" };
		String[] bb = { "bb1", "bb2", "bb3" };
		String[] cc = { "cc1", "cc2", "cc3", "cc4" };
		String[] dd = { "dd1", "dd2", "dd3", "dd4", "dd5", "dd6" };
		String[] ee = { "ee1", "ee2", "ee3", "ee4" };

		List<String[]> list = cartesian(aa, bb, cc, dd, ee);
		System.out.println(list);
	}

	public static void handle() {
		counter[counterIndex]++;
		if (counter[counterIndex] >= xyz[counterIndex].length) {
			counter[counterIndex] = 0;
			counterIndex--;
			if (counterIndex >= 0) {
				handle();
			}
			counterIndex = xyz.length - 1;
		}
	}

	public static List<String[]> cartesian(String[]... elements) {
		
		List<String[]> rlist = new ArrayList<String[]>();
		
		if(elements==null||elements.length==0)
			return rlist;

		xyz = elements;
		counterIndex = xyz.length - 1;
		counter = new int[elements.length];

		int len = 1;
		for (String[] e : elements) {
			len = e.length * len;
		}

		
		for (int i = 0; i < len; i++) {
			int j = 0;
			String[] re = new String[elements.length];
			for (String[] e : elements) {
				re[j] = e[counter[j]];
				System.out.print(e[counter[j]]);
				System.out.print("\t");
				j++;
			}
			System.out.println();
			rlist.add(re);
			handle();
		}

		return rlist;
	}

}