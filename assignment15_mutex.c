#include <stdio.h>
#include <pthread.h>

#define THREADS 4
#define INCREMENTS 1000000

long long counter = 0;

// Mutex declaration
pthread_mutex_t lock;

void* increment(void* arg)
{
    for (int i = 0; i < INCREMENTS; i++)
    {
        // Lock critical section
        pthread_mutex_lock(&lock);

        counter++;

        // Unlock critical section
        pthread_mutex_unlock(&lock);
    }

    return NULL;
}

int main()
{
    pthread_t tid[THREADS];

    // Initialize mutex
    pthread_mutex_init(&lock, NULL);

    // Create threads
    for (int i = 0; i < THREADS; i++)
    {
        pthread_create(&tid[i], NULL, increment, NULL);
    }

    // Wait for all threads
    for (int i = 0; i < THREADS; i++)
    {
        pthread_join(tid[i], NULL);
    }

    printf("Final Counter Value = %lld\n", counter);

    // Destroy mutex
    pthread_mutex_destroy(&lock);

    return 0;
}
