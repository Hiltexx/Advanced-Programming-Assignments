#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void constantTime() {

    clock_t startTime, endTime;
    double totalTime;
    size_t memory = sizeof(int);

    int a = 10, b = 20;
    int sum;

    startTime = clock();
    sum = a + b;   // simple operation
    endTime = clock();

    totalTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    printf("Constant Time (O(1)):\n");
    printf("Execution Time = %f seconds\n", totalTime);
    printf("Space Used     = %zu bytes\n\n", memory);
}

void linearSearch(int *arr, int n) {

    clock_t startTime, endTime;
    double totalTime;
    size_t memory = n * sizeof(int);

    int key = -1;   // value not present in array

    startTime = clock();

    for(int i = 0; i < n; i++) {
        if(arr[i] == key) {
            break;
        }
    }

    endTime = clock();

    totalTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    printf("Linear Search (O(n)):\n");
    printf("Execution Time = %f seconds\n", totalTime);
    printf("Space Used     = %zu bytes\n\n", memory);
}

void bubbleSort(int *arr, int n) {

    clock_t startTime, endTime;
    double totalTime;
    size_t memory = n * sizeof(int);

    startTime = clock();

    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - i - 1; j++) {

            if(arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }

        }
    }

    endTime = clock();

    totalTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;

    printf("Bubble Sort (O(n^2)):\n");
    printf("Execution Time = %f seconds\n", totalTime);
    printf("Space Used     = %zu bytes\n\n", memory);
}

int main() {

    int n;

    printf("Enter number of elements (n): ");
    scanf("%d", &n);

    int *arr = malloc(n * sizeof(int));

    for(int i = 0; i < n; i++) {
        arr[i] = i + 1;
    }

    printf("\n--- Time and Space Complexity Analysis ---\n\n");

    constantTime();
    linearSearch(arr, n);
    bubbleSort(arr, n);

    free(arr);

    return 0;
}