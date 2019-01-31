package com.game.util.sort;

public class SortUtil
{

	/**
	 * 快速排序算法
	 *
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	private static int quickSort(int[] a, int left, int right)
	{
		int tmp;
		// 进行一趟快速排序,返回中心记录位置
		int pivot = a[left];// 把中心置于a[0]
		while (left < right)
		{
			while (left < right && a[right] >= pivot)
				right--;

			tmp = a[right]; // 将比中心记录小的移到低端
			a[right] = a[left];
			a[left] = tmp;
			while (left < right && a[left] <= pivot)
				left++;

			tmp = a[right];
			a[right] = a[left];
			a[left] = tmp;
			// 将比中心记录大的移到高端
		}
		a[left] = pivot; // 中心移到正确位置
		return left; // 返回中心位置
	}

	
}
