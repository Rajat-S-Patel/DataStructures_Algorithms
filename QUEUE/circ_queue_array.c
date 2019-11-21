#include<stdio.h>
#include<stdlib.h>

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
    return ((q->rear+1)%q->capacity)==((q->front)%q->capacity);
}
int isEmpty(queue q){
    return (q->front==-1&&q->rear==-1)||
    (((q->rear+1)%q->capacity==q->capacity)&&q->front!=0);
}
void enqueue(queue q,int data){
    if(q->front==-1&&q->rear==-1){
        q->front=0;
        q->rear=0;
         printf("capacity : %d",q->capacity);
    }
   
    else if((q->front==0&&(q->rear==(q->capacity)-1))||q->rear+1==q->front){
        printf("Queue is full.");
        return;
    }
    else if(q->rear==(q->capacity-1)&&q->front!=0){
        q->rear=0;
    }
    else{
        q->rear++;
    }
    q->arr[q->rear]=data;
}
void display(queue q){
    if(q->rear>q->front){
        int i=0;
        for(i=q->front;i<=q->rear;i++){
            printf("%d ",q->arr[i]);
        }
    }
    else{
        int i;
        for(i=q->front;i<q->capacity;i++){
            printf("%d ",q->arr[i]);
        }
        for(i=0;i<=q->rear;i++){
            printf("%d ",q->arr[i]);
        }
    }
}
int dequeue(queue q){
    if(q->front==-1){
        printf("Empty queue");
        return -1;
    }
    printf("%d : ",q->front);
    int data=q->arr[q->front];
    if(q->front==q->rear){
        q->rear=q->front=-1;
    }
    else if(q->front==q->capacity-1){
        q->front=0;
    }
    else{
        q->front++;
    }
    return data;
}
void main(){
    printf("Enter capacity of Circular Queue: ");
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
