public class QuickSortIterativo {

    public static void quickSort(int[] arr) {
        int[] stack = new int[arr.length];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            int p = partition(arr, low, high);

            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;
            }
            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Método para probar el algoritmo
    public static void main(String[] args) {
        int[] arr = {34, 7, 23, 32, 5, 62};
        quickSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
