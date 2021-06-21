
public class SortingClass {

	static int[] numbers1k = new int[1000];
	static int[] numbers10k = new int[10000];
	static int[] numbers100k = new int[100000];

	public static void Increasingnumbers() {
		for (int i = 0; i < numbers100k.length; i++) {
			if (i < numbers1k.length)
				numbers1k[i] = i;
			if (i < numbers10k.length)
				numbers10k[i] = i;
			numbers100k[i] = i;
		}
	}

	public static void Decreasingnumbers() {
		for (int i = 0; i < numbers100k.length; i++) {
			if (i < numbers1k.length)
				numbers1k[i] = numbers1k.length - i;
			if (i < numbers10k.length)
				numbers10k[i] = numbers10k.length - i;
			numbers100k[i] = numbers100k.length - i;
		}
	}

	public static void Randomnumbers() {
		for (int i = 0; i < numbers100k.length; i++) {
			int numbers = (int) (Math.random() * Integer.MAX_VALUE);
			if (i < numbers1k.length)
				numbers1k[i] = numbers;
			if (i < numbers10k.length)
				numbers10k[i] = numbers;
			numbers100k[i] = numbers;
		}
	}

	public static void Equalnumbers() {
		int number = (int) (Math.random() * Integer.MAX_VALUE);
		for (int i = 0; i < numbers100k.length; i++) {
			if (i < numbers1k.length)
				numbers1k[i] = number;
			if (i < numbers10k.length)
				numbers10k[i] = number;
			numbers100k[i] = number;
		}
	}

	// DualPivotQuickSort
	public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
	}

	public static void DPQuicksort(int[] arr, int low, int high) {
			if (high > low) {
				if (arr[low] > arr[high]) {
					swap(arr, low, high);
				}
				int j = low + 1;
				int g = high - 1;
				int k = low + 1;
				int p = arr[low];
				int q = arr[high];
				while (k <= g) {
					if (arr[k] < p) {
						swap(arr, k, j);
						++j;
					} else if (arr[k] >= q) {
						while (arr[g] > q && k < g) {
							--g;
						}
						swap(arr, k, g);
						--g;
						if (arr[k] < p) {
							swap(arr, k, j);
							++j;
						}
					}
					++k;
				}
				--j;
				++g;
				swap(arr, low, j);
				swap(arr, high, g);
				DPQuicksort(arr, low, j - 1);
				DPQuicksort(arr, j + 1, g - 1);
				DPQuicksort(arr, g + 1, high);
			}
	}

	// Shellsort
	static int shellsort(int arr[]) {
		int n = arr.length;
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i += 1) {
				int temp = arr[i];
				int j;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
					arr[j] = arr[j - gap];
				arr[j] = temp;
			}
		}
		return 0;
	}

	// Heapsort
	public static void heapsort(int arr[]) {
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; i--)
			maxheapify(arr, n, i);
		for (int i = n - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			maxheapify(arr, i, 0);
		}
	}

	public static void maxheapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (l < n && arr[l] > arr[largest])
			largest = l;
		if (r < n && arr[r] > arr[largest])
			largest = r;
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			maxheapify(arr, n, largest);
		}
	}

	public static double runtimeHeapsort(int[] arr) {
		long startTime = System.currentTimeMillis();
		heapsort(arr);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	public static double runtimeDPQuicksort(int[] arr) {
		long startTime = System.currentTimeMillis();
		DPQuicksort(arr, 0, arr.length - 1);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	public static double runtimeShellsort(int[] arr) {
		long startTime = System.currentTimeMillis();
		shellsort(arr);
		long estimatedTime = System.currentTimeMillis() - startTime;
		return estimatedTime;
	}

	public static void Testfunction() {

		Equalnumbers();
		System.out.println("\n\n\n\n---------------- EQUAL NUMBERS --------------------\n");
		System.out.println(String.format("%20s %11s %10s", "1000", "10000", "100000"));
		System.out.print("\nHeapsort: ");
		System.out.print(String.format("%12s", runtimeHeapsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nDPQuicksort: ");
		System.out.print(String.format("%9s", runtimeDPQuicksort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nShellsort: ");
		System.out.print(String.format("%11s", runtimeShellsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers100k.clone()) + " ms\n"));

		Randomnumbers();
		System.out.println("\n\n\n\n---------------- RANDOM NUMBERS --------------------\n");
		System.out.println(String.format("%20s %11s %9s", "1000", "10000", "100000"));
		System.out.print("\nHeapsort: ");
		System.out.print(String.format("%12s", runtimeHeapsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nDPQuicksort: ");
		System.out.print(String.format("%9s", runtimeDPQuicksort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nShellsort: ");
		System.out.print(String.format("%11s", runtimeShellsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers100k.clone()) + " ms\n"));

		Increasingnumbers();
		System.out.println("\n\n---------------- INCREASING NUMBERS --------------------\n");
		System.out.println(String.format("%20s %11s %9s", "1000", "10000", "100000"));
		System.out.print("\nHeapsort: ");

		System.out.print(String.format("%12s", runtimeHeapsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nDPQuicksort: ");
		System.out.print(String.format("%9s", runtimeDPQuicksort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nShellsort: ");
		System.out.print(String.format("%11s", runtimeShellsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers100k.clone()) + " ms\n"));

		Decreasingnumbers();
		System.out.println("\n\n\n\n---------------- DECREASING NUMBERS --------------------\n");
		System.out.println(String.format("%20s %10s %10s", "1000", "10000", "100000"));
		System.out.print("\nHeapsort: ");
		System.out.print(String.format("%12s", runtimeHeapsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeHeapsort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nDPQuicksort: ");
		System.out.print(String.format("%9s", runtimeDPQuicksort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeDPQuicksort(numbers100k.clone()) + " ms\n"));

		System.out.print("\nShellsort: ");
		System.out.print(String.format("%11s", runtimeShellsort(numbers1k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers10k.clone()) + " ms"));
		System.out.print(String.format("%11s", runtimeShellsort(numbers100k.clone()) + " ms\n"));

		System.out.println("\n");
	}

	public static void main(String[] args) {
		Testfunction();
		//If there is a Stackoverflowerror,
		//you should do Run Configurations-->Arguments---> -Xss4m
	}

}
