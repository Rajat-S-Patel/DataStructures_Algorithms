#include <stdio.h>
#include <stdlib.h>

struct Queue
{
    int *arr;
    int front, rear;
    unsigned capacity;
};
typedef struct Queue *queue;
queue createQ(unsigned capacity)
{
    queue temp = (queue)malloc(sizeof(struct Queue));
    temp->arr = (int *)malloc(sizeof(int) * capacity);
    temp->front = -1;
    temp->rear = -1;
    temp->capacity = capacity;
    return temp;
}
int isFull(queue q)
{
    return (q->front == 0 && q->rear == q->capacity - 1) || (q->front == q->rear + 1);
}
int isEmpty(queue q)
{
    return q->front == -1;
}
void enqueue_f(queue q, int data)
{
    if (isFull(q))
    {
        printf("Queue is Full");
        return;
    }
    if (q->front == -1)
    {
        q->front = 0;
        q->rear = 0;
    }
    else if (q->front == 0)
    {
        q->front = q->capacity - 1;
    }
    else
    {
        q->front--;
    }
    q->arr[q->front] = data;
}
void enqueue_b(queue q, int data)
{
    if (isFull(q))
    {
        printf("Queue is full");
        return;
    }
    if (q->front == -1)
    {
        q->front = q->rear = 0;
    }
    if (q->rear == q->capacity - 1)
    {
        q->rear = 0;
    }
    else
    {
        q->rear++;
    }
    q->arr[q->rear] = data;
}
int dequeue_f(queue q)
{
    if (isEmpty(q))
    {
        printf("Queue is empty");
        return -1;
    }
    int data = q->arr[q->front];
    if (q->front == q->rear)
    {
        q->front = q->rear = -1;
    }
    else if (q->front == q->capacity - 1)
    {
        q->front = 0;
    }
    else
    {
        q->front++;
    }
    return data;
}
int dequeue_b(queue q)
{
    if (isEmpty(q))
    {
        printf("Queue is Empty");
        return -1;
    }
    int data = q->arr[q->rear];
    if (q->front == q->rear)
    {
        q->front = q->rear = -1;
    }
    else if (q->rear == 0)
    {
        q->rear = q->capacity - 1;
    }
    else
    {
        q->rear--;
    }
    return data;
}
void display(queue q)
{  // printf("\nq->front %d",q->front);
   // printf("\nq->rear %d",q->rear);
    if (q->rear >= q->front)
    {
        int i = 0;
        for (i = q->front; i <= q->rear; i++)
        {
            printf("%d ", q->arr[i]);
        }
    }
    else
    {
        int i;
        for (i = q->front; i < q->capacity; i++)
        {
            printf("%d ", q->arr[i]);
        }
        for (i = 0; i <= q->rear; i++)
        {
            printf("%d ", q->arr[i]);
        }
    }
}
void main()
{
    printf("Enter capacity of Circular Queue: ");
    int capacity;
    scanf("%d", &capacity);
    queue q = createQ(capacity);
start:
    printf("\n1. Enqueue_front\n2. Enqueue_back\n3. Dequeue_front\n4. Dequeue_back\n5. Display\n");
    int choice, data;
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
        printf("Enter data : ");
        scanf("%d", &data);
        enqueue_f(q, data);
        break;
    case 2:
        printf("Enter data : ");
        scanf("%d", &data);
        enqueue_b(q, data);
        break;
    case 3:
        printf("dequeued : %d", dequeue_f(q));
        break;
    case 4:
        printf("dequeued : %d", dequeue_b(q));
        break;
    case 5:
        display(q);
        break;
    }
    goto start;
}