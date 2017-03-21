package com.tk.cn.utils.maths;

import java.util.Arrays;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月21日 下午9:28:25
 */
public class BubbleSortUtils {
	public static void main(String[] args) {
		int[] array = new int[] { 1, 33, 55, 7, 8888, };

		printArray(array);
		/*
		 * printArray(array); BubbleSort(array, "asc"); System.out.println();
		 * 
		 * BubbleSort(array, "desc");
		 * 
		 * System.out.println(); printArray(array);
		 */

		/*
		 * for (int i = 0; i < array.length; i++) { for (int j = 0; j <
		 * array.length - 1 - i; j++) { if (array[j] < array[j + 1]) {
		 * swap(array, j, j + 1); }
		 * 
		 * } }
		 */

		/*
		 * System.out.println(); printArray(array);
		 */

		Arrays.sort(array);
		printArray(array);

	}

	// 排序的顺序从小到大,反之.
	public static void BubbleSort(int[] array, String str) {
		if (str.equals("asc")) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length - 1 - i; j++) {
					if (array[j] > array[j + 1]) {
						swap(array, j, j + 1);
					}
				}
			}

		} else if (str.equals("desc")) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length - 1 - i; j++) {
					if (array[j] < array[j + 1]) {
						swap(array, j, j + 1);
					}
				}
			}

		} else {
			System.out.println("输入的排序方式有错误,请重新输入.");

		}
	}

	// 排序交换数组中的两个元素
	public static void swap(int[] array, int i, int j) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// 遍历数组元素
	public static void printArray(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {

			if (i == array.length - 1) {
				System.out.print(array[i] + "}");
				break;

			}
			System.out.print(array[i] + ",");
		}
	}

}

/*
 * int temp; for(int i=0;i<array.length;i++){ for(int j=0;j<array.length;j++){
 * if(array[j]<array[j+1]){ temp = array[j]; array[j] = array[j+1];
 * array[j+1]=temp; } } // System.out.print("{"); for(int
 * i=0;i<array.length;i++){ for(int j=0;j<array.length;j++){
 * if(array[j]<array[j+1]){ temp = array[j]; array[j] = array[j+1];
 * array[j+1]=temp; } }
 */
