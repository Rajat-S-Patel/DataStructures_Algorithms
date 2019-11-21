#include<stdlib.h>
#include<stdio.h>

struct Queue{
    int *arr;
    int front,rear;
    unsigned capacity;
};
typedef struct Queue *queue;
queue createQ(unsigned capacity){
    queue temp = (queue)malloc(sizeof(struct Queue));
    temp->arr=(int*)malloc(sizeof(int)*capacity);
    temp->front=temp->rear=-1;
    temp->capacity=capacity;
    return temp;
}
int isFull(queue q){
    return (q->rear==q->capacity-1&&q->front==0);
}
int isEmpty(queue q){
    return (q->rear==-1)||(q->front>q->rear);
}
void enqueue(queue q,int data){
   if(isFull(q)&&(q->front<=q->rear)){
        printf("Queue is full.");
        return;
    }
    else{
        if(q->front>q->rear){
            q->front=q->rear=-1;
    }
        if(q->front==-1){
            q->front=0;
        }
        q->arr[++(q->rear)]=data;
    }
}
int dequeue(queue q){
    if(isEmpty(q)){
        printf("Queue is empty.");
        return -1;
    }
    else{
        int data = q->arr[q->front++];
        return data;
    }
}
void display(queue q){
    if(isEmpty(q)){
        printf("Empty queue");
    }
    else{
        int i=q->front;
        
        while(i<=q->rear){
            printf("%d ",q->arr[i]);
            i++;
        }
    }
}
void main(){
    printf("Enter capacity : ");
    int capacity;
    scanf("%d",&capacity);
    queue q=createQ(capacity);
    start:
    printf("\n1. Enqueue\n2. Dequeue\n3. Traverse");
    int choice,data;
    scanf("%d",&choice);
    switch(choice){
        case 1:
            printf("Enter data : ");
            scanf("%d",&data);
            enqueue(q,data);
            break;
        case 2:
            printf("dequeued : %d",dequeue(q));
            break;
        case 3:
            display(q);
            break;
    }
    goto start;

}
