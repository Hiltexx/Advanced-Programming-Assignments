#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *data;
    size_t length;
    size_t capacity;
} StringBuffer;

/* Initialize StringBuffer */
StringBuffer* sb_init(size_t initial_capacity) {

    /* Prevent zero capacity */
    if (initial_capacity == 0) {
        initial_capacity = 1;
    }

    /* Allocate struct */
    StringBuffer *sb = (StringBuffer*)malloc(sizeof(StringBuffer));

    if (sb == NULL) {
        printf("Memory allocation failed for StringBuffer.\n");
        return NULL;
    }

    /* Allocate internal buffer */
    sb->data = (char*)malloc(initial_capacity);

    if (sb->data == NULL) {
        printf("Memory allocation failed for data buffer.\n");
        free(sb);
        return NULL;
    }

    sb->length = 0;
    sb->capacity = initial_capacity;

    /* Initialize empty string */
    sb->data[0] = '\0';

    return sb;
}

/* Append string to buffer */
void sb_append(StringBuffer *sb, const char *str) {

    if (sb == NULL || str == NULL) {
        return;
    }

    size_t str_len = strlen(str);

    /* Resize buffer if needed */
    while (sb->length + str_len + 1 > sb->capacity) {

        size_t new_capacity = sb->capacity * 2;

        /* Safe realloc */
        char *temp = (char*)realloc(sb->data, new_capacity);

        if (temp == NULL) {
            printf("Reallocation failed.\n");
            return;
        }

        sb->data = temp;
        sb->capacity = new_capacity;

        printf("Buffer resized to capacity: %zu\n", sb->capacity);
    }

    /* Append new string */
    strcpy(sb->data + sb->length, str);

    sb->length += str_len;
}

/* Free all allocated memory */
void sb_free(StringBuffer *sb) {

    if (sb != NULL) {
        free(sb->data);
        free(sb);
    }
}

int main() {

    /* Create buffer with small capacity */
    StringBuffer *sb = sb_init(8);

    if (sb == NULL) {
        return 1;
    }

    printf("Initial Capacity: %zu\n\n", sb->capacity);

    /* Append first string */
    sb_append(sb, "Hello");

    printf("String: %s\n", sb->data);
    printf("Length: %zu\n", sb->length);
    printf("Capacity: %zu\n\n", sb->capacity);

    /* Append second string */
    sb_append(sb, " World!");

    printf("String: %s\n", sb->data);
    printf("Length: %zu\n", sb->length);
    printf("Capacity: %zu\n\n", sb->capacity);

    /* Append third string */
    sb_append(sb, " Dynamic String Buffer Example");

    printf("String: %s\n", sb->data);
    printf("Length: %zu\n", sb->length);
    printf("Capacity: %zu\n\n", sb->capacity);

    /* Free memory */
    sb_free(sb);

    printf("All memory freed successfully.\n");

    return 0;
}
