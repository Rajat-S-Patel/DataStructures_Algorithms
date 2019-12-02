#include <stdlib.h>
#include <stdio.h>

struct PrioQ
{
    int *arr;
    int size;
    int pointer;
};
typedef struct PrioQ *prioQ;
prioQ newNode(prioQ q, unsigned size)
{
    q = (prioQ)malloc(sizeof(struct PrioQ));
    q->arr = (int *)malloc(sizeof(int) * size);
    q->size = size;
    q->pointer = 0;
    return q;
}

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

void MAX_HEAPIFY(int *arr, int size, int i)
{
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (arr[largest] < arr[left] && left < size)
    {
        largest = left;
    }
    if (arr[largest] < arr[right] && right < size)
    {
        largest = right;
    }

    if (i != largest)
    {
        swap(&arr[i], &arr[largest]);
        MAX_HEAPIFY(arr, size, largest);
    }
}
void build_maxheap(int *arr, int size)
{
    int i;
    for (i = size / 2; i >= 0; i--)
    {
        MAX_HEAPIFY(arr, size, i);
    }
}
void enqueue(prioQ q, int data)
{
    if (q->pointer >= q->size)
    {
        printf("queue is full");
        return;
    }
    q->arr[q->pointer] = data;
    ++q->pointer;

    build_maxheap(q->arr, q->pointer);
}
int dequeue(prioQ q)
{
    if (q->size == 0)
    {
        printf("Empty queue");
        return -1;
    }
    else
    {
        int temp = q->arr[0];
        q->arr[0] = q->arr[q->pointer - 1];
        q->pointer--;
        MAX_HEAPIFY(q->arr, q->pointer, 0);
        return temp;
    }
}
void display(prioQ q)
{
    int i = 0;
    if (q->pointer == 0)
    {
        printf("Queue is empty");
        return;
    }
    for (i = 0; i < q->pointer; i++)
    {
        printf("%d ", q->arr[i]);
    }
}
void main()
{
    prioQ q = NULL;
    printf("Enter size : ");
    int size;
    scanf("%d", &size);
    q = newNode(q, size);

    printf("Priority Queue using Heap---------------------------------------------");
start:
    printf("\n1. Enqueue\n2. Dequeue\n3. Display\n");
    int choice, data;
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
        printf("Enter data to insert : ");
        scanf("%d", &data);
        enqueue(q, data);
        break;
    case 2:
        printf("Dequeued : %d ", dequeue(q));
        break;
    case 3:
        display(q);
        break;

    default:
        break;
    }
    goto start;
}