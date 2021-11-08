package InternalSort;

public class FinalEdition {
	public static void main(String[] args) {
		Array arr = new Array();
//		System.out.println("插入排序------比较次数、交换次数、排序时间分析：");
//		System.out.println("数组长度为50000时：");
//		arr.insertSort(arr.creatArray(50000, 1000));
//		arr.insertSort(arr.creatArray(50000, 10000));
//		arr.insertSort(arr.creatArray(50000, 100000));
//		arr.insertSort(arr.creatArray(50000, 1000000));
//		arr.insertSort(arr.creatArray(50000, 10000000));
//		System.out.println("数组长度为100000时：");
//		arr.insertSort(arr.creatArray(100000, 1000));
//		arr.insertSort(arr.creatArray(100000, 10000));
//		arr.insertSort(arr.creatArray(100000, 100000));
//		arr.insertSort(arr.creatArray(100000, 1000000));
//		arr.insertSort(arr.creatArray(100000, 10000000));
//		System.out.println("------------------------------------------------------------------------------------------");
//		System.out.println("冒泡排序------比较次数、交换次数、排序时间分析：");
//		System.out.println("数组长度为50000时：");
//		arr.bubbleSort(arr.creatArray(50000, 1000));
//		arr.bubbleSort(arr.creatArray(50000, 10000));
//		arr.bubbleSort(arr.creatArray(50000, 100000));
//		arr.bubbleSort(arr.creatArray(50000, 1000000));
//		arr.bubbleSort(arr.creatArray(50000, 10000000));
//		System.out.println("数组长度为100000时：");
//		arr.bubbleSort(arr.creatArray(100000, 1000));
//		arr.bubbleSort(arr.creatArray(100000, 10000));
//		arr.bubbleSort(arr.creatArray(100000, 100000));
//		arr.bubbleSort(arr.creatArray(100000, 1000000));
//		arr.bubbleSort(arr.creatArray(100000, 10000000));
//		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("快速排序------比较次数、交换次数、排序时间分析：");
		System.out.println("数组长度为50000时：");
		arr.quickSort(arr.creatArray(50000, 1000));
		arr.quickSort(arr.creatArray(50000, 10000));
		arr.quickSort(arr.creatArray(50000, 100000));
		arr.quickSort(arr.creatArray(50000, 1000000));
		arr.quickSort(arr.creatArray(50000, 10000000));
		System.out.println("数组长度为100000时：");
		arr.quickSort(arr.creatArray(100000, 1000));
		arr.quickSort(arr.creatArray(100000, 10000));
		arr.quickSort(arr.creatArray(100000, 100000));
		arr.quickSort(arr.creatArray(100000, 1000000));
		arr.quickSort(arr.creatArray(100000, 10000000));
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("简单选择排序------比较次数、交换次数、排序时间分析：");
		System.out.println("数组长度为50000时：");
		arr.selectSort(arr.creatArray(50000, 1000));
		arr.selectSort(arr.creatArray(50000, 10000));
		arr.selectSort(arr.creatArray(50000, 100000));
		arr.selectSort(arr.creatArray(50000, 1000000));
		arr.selectSort(arr.creatArray(50000, 10000000));
		System.out.println("数组长度为100000时：");
		arr.selectSort(arr.creatArray(100000, 1000));
		arr.selectSort(arr.creatArray(100000, 10000));
		arr.selectSort(arr.creatArray(100000, 100000));
		arr.selectSort(arr.creatArray(100000, 1000000));
		arr.selectSort(arr.creatArray(100000, 10000000));
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("堆排序------比较次数、交换次数、排序时间分析：");
		System.out.println("数组长度为50000时：");
		arr.heapSort(arr.creatArray(50000, 1000));
		arr.heapSort(arr.creatArray(50000, 10000));
		arr.heapSort(arr.creatArray(50000, 100000));
		arr.heapSort(arr.creatArray(50000, 1000000));
		arr.heapSort(arr.creatArray(50000, 10000000));
		System.out.println("数组长度为100000时：");
		arr.heapSort(arr.creatArray(100000, 1000));
		arr.heapSort(arr.creatArray(100000, 10000));
		arr.heapSort(arr.creatArray(100000, 100000));
		arr.heapSort(arr.creatArray(100000, 1000000));
		arr.heapSort(arr.creatArray(100000, 10000000));
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("归并排序------比较次数、交换次数、排序时间分析：");
		System.out.println("数组长度为50000时：");
		arr.mergeSort(arr.creatArray(50000, 1000));
		arr.mergeSort(arr.creatArray(50000, 10000));
		arr.mergeSort(arr.creatArray(50000, 100000));
		arr.mergeSort(arr.creatArray(50000, 1000000));
		arr.mergeSort(arr.creatArray(50000, 10000000));
		System.out.println("数组长度为100000时：");
		arr.mergeSort(arr.creatArray(100000, 1000));
		arr.mergeSort(arr.creatArray(100000, 10000));
		arr.mergeSort(arr.creatArray(100000, 100000));
		arr.mergeSort(arr.creatArray(100000, 1000000));
		arr.mergeSort(arr.creatArray(100000, 10000000));
	}
}

class Array {
	public static Integer[] creatArray(int len, int max) {
		Integer[] arr = new Integer[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max);
		}
		return arr;
	}

	// 插入排序
	public static void insertSort(Integer[] array) {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		int i, j;
		Integer temp;
		long count1 = 0, count2 = 0;
		for (i = 1; i < array.length; i++) {
			temp = array[i];
			j = i - 1;
			if (j > -1)
				count1++;
			while (j > -1 && temp < array[j]) {
				array[j + 1] = array[j];
				count2++;// 交换次数
				j--;
				if (j > -1)
					count1++;
			}
			array[j + 1] = temp;
			count2++;
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("比较次数：" + count1 + "，交换次数：" + count2 + ",运行时间" + (endTime - startTime) + "ms");
	}

	// 冒泡排序
	public static void bubbleSort(Integer[] array) {
		long count1 = 0, count2 = 0;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				count1++;
				if (array[j] > array[j + 1]) {
					Integer temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					count2++;
				}
			}
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("比较次数：" + count1 + "，交换次数：" + count2 + ",运行时间" + (endTime - startTime) + "ms");
	}

	// 快速排序
	public static void quickSort(Integer[] array) {
		long[] count = new long[2];
		count[0] = 0;
		count[1] = 0;
		long startTime = System.currentTimeMillis();
		count = kspx(array, 0, array.length - 1, count);
		long endTime = System.currentTimeMillis();
		System.out.println("比较次数：" + count[0] + "，交换次数：" + count[1] + ",运行时间" + (endTime - startTime) + "ms");
	}

	public static long[] kspx(Integer[] array, int t, int w, long[] count) {
		int i, j;
		Integer x;
		if (t < w) {
			i = t;
			j = w;
			x = array[i];
			while (i < j) {
				while (array[j] >= x && j > i) {
					j--;
					count[0]++;
				}

				if (i < j) {
					array[i] = array[j];
					count[1]++;
					i++;
				}
				while (array[i] <= x && j > i) {
					i++;
					count[0]++;
				}

				if (i < j) {
					array[j] = array[i];
					count[1]++;
					j--;
				}
			}
			array[i] = x;
			count = kspx(array, t, j - 1, count);
			count = kspx(array, j + 1, w, count);

		}
		return count;
	}

	// 简单选择排序
	public static void selectSort(Integer array[]) {
		int i = 0, j = 0, k = 0;
		long count1 = 0, count2 = 0;
		Integer x;
		long startTime = System.currentTimeMillis();
		for (i = 1; i < array.length; i++) {
			k = i;
			for (j = i + 1; j < array.length; j++) {
				count1++;
				if (array[j] < array[k]) {
					k = j;
				}
				if (i != k) {
					x = array[i];
					array[i] = array[k];
					array[k] = x;
					count2++;
				}
			}
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("比较次数：" + count1 + "，交换次数：" + count2 + ",运行时间" + (endTime - startTime) + "ms");
	}

	// 堆排序
	public static void heapSort(Integer[] array) {
		int i;
		long[] count = new long[2];
		count[0] = 0;
		count[1] = 0;
		Integer x;
		long startTime = System.currentTimeMillis();
		for (i = (array.length - 1) / 2; i >= 0; i--) {
			count = screenMethod(array, i, array.length, count);
		}
		for (i = array.length - 1; i >= 1; i--) {
			x = array[0];
			array[0] = array[i];
			array[i] = x;
			count = screenMethod(array, 0, i, count);
			count[1]++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("比较次数：" + count[0] + "，交换次数：" + count[1] + ",运行时间" + (endTime - startTime) + "ms");
	}

	public static long[] screenMethod(Integer[] array, int t, int w, long[] count) {
		int i, j, flag;
		Integer x;
		i = t;
		x = array[i];
		j = 2 * i + 1;
		flag = 0;
		while (j < w && flag != 1) {
			if (j < w - 1)
				count[0]++;
			if ((j < w - 1) && (array[j] < array[j + 1]))
				j++;
			count[0]++;
			if (x <= array[j]) {
				array[i] = array[j];
				i = j;
				j = i * 2 + 1;
				count[1]++;
			} else
				flag = 1;
		}
		array[i] = x;
		return count;
	}

	// 归并排序
	public static void mergeSort(Integer[] array) {
		long[] count = new long[2];
		count[0] = 0;
		count[1] = 0;
		long startTime = System.currentTimeMillis();
		Integer[] temp = new Integer[array.length];
		count = mergeSort1(array, 0, array.length - 1, temp, count);
		long endTime = System.currentTimeMillis();
		System.out.println("比较次数：" + count[0] + "，交换次数：" + count[1] + ",运行时间" + (endTime - startTime) + "ms");
	}

	public static long[] merge(Integer[] arr, int low, int mid, int high, Integer[] temp, long[] count) {
		int i = 0;
		int j = low, k = mid + 1; // 左边序列和右边序列起始索引
		while (j <= mid && k <= high) {
			count[0]++;
			if (arr[j] < arr[k]) {
				temp[i++] = arr[j++];
				count[1]++;
			} else {
				temp[i++] = arr[k++];
				count[1]++;
			}
		}
		// 若左边序列还有剩余，则将其全部拷贝进temp[]中
		while (j <= mid) {
			temp[i++] = arr[j++];
			count[1]++;
		}

		while (k <= high) {
			temp[i++] = arr[k++];
			count[1]++;
		}

		for (int t = 0; t < i; t++) {
			arr[low + t] = temp[t];
			count[1]++;
		}
		return count;
	}

	public static long[] mergeSort1(Integer[] arr, int low, int high, Integer[] temp, long[] count) {
		if (low < high) {
			int mid = (low + high) / 2;
			count = mergeSort1(arr, low, mid, temp, count); // 对左边序列进行归并排序
			count = mergeSort1(arr, mid + 1, high, temp, count); // 对右边序列进行归并排序
			count = merge(arr, low, mid, high, temp, count); // 合并两个有序序列
			return count;
		}
		return count;
	}
}