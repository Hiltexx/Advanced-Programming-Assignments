#include <stdio.h>
#include <stdlib.h>
void constantTime() {
size_t memory = sizeof(int) * 3; // a, b, sum
printf("Constant Operation:\n");
printf("Space Complexity = O(1)\n");
printf("Space Used = %zu bytes\n\n", memory);
}
void linearSearch(int *arr, int n) {
size_t memory = (n * sizeof(int)) + sizeof(int); // array + key
printf("Linear Search:\n");
printf("Space Complexity = O(n)\n");
printf("Space Used = %zu bytes\n\n", memory);
}
void bubbleSort(int *arr, int n) {

size_t memory = (n * sizeof(int)) + sizeof(int); // array + temp
variable
printf("Bubble Sort:\n");
printf("Space Complexity = O(n)\n");
printf("Space Used = %zu bytes\n\n", memory);
}
int main() {
int n;
printf("Enter number of elements (n): ");
scanf("%d", &n);
int *arr = malloc(n * sizeof(int));
printf("\n--- Space Complexity Analysis ---\n\n");
constantTime();
linearSearch(arr, n);
bubbleSort(arr, n);
free(arr);
return 0;
}