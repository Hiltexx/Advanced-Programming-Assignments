#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define BUFFER_SIZE 5
#define ITEMS 10

int buffer[BUFFER_SIZE];

int in = 0;
int out = 0;

// Semaphores
sem_t empty;
sem_t full;

// Mutex
pthread_mutex_t mutex;

void* producer(void* arg)
{
    for (int i = 1; i <= ITEMS; i++)
    {
        // Wait if buffer is full
        sem_wait(&empty);

        // Lock critical section
        pthread_mutex_lock(&mutex);

        buffer[in] = i;

        printf("Producer produced item %d at index %d\n", i, in);

        in = (in + 1) % BUFFER_SIZE;

        // Unlock critical section
        pthread_mutex_unlock(&mutex);

        // Signal that buffer has filled slot
        sem_post(&full);

        sleep(1);
    }

    return NULL;
}

void* consumer(void* arg)
{
    for (int i = 1; i <= ITEMS; i++)
    {
        // Wait if buffer is empty
        sem_wait(&full);

        // Lock critical section
        pthread_mutex_lock(&mutex);

        int item = buffer[out];

        printf("Consumer consumed item %d from index %d\n", item, out);

        out = (out + 1) % BUFFER_SIZE;

        // Unlock critical section
        pthread_mutex_unlock(&mutex);

        // Signal empty slot available
        sem_post(&empty);

        sleep(2);
    }

    return NULL;
}

int main()
{
    pthread_t prod, cons;

    // Initialize semaphores
    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full, 0, 0);

    // Initialize mutex
    pthread_mutex_init(&mutex, NULL);

    // Create threads
    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    // Wait for threads
    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    // Destroy synchronization objects
    sem_destroy(&empty);
    sem_destroy(&full);
    pthread_mutex_destroy(&mutex);

    return 0;
}
