package com.example.SimplestCRUDExample;

public class Practice {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 6 };
		int n = arr.length + 1;

		int sum = 0;
		for (int el : arr) {
			sum += el;
		}

		int target = n * (n + 1) / 2;

		System.out.println(target - sum);
	}
}
