#include<stdio.h>
#include<stdlib.h>

struct Node{
    int data;
    struct Node *next,*prev;
};
typedef struct Node *node;
node newNode(int data){
    node temp = (node)malloc(sizeof(struct Node));
    temp->data=data;
    temp->next=NULL;
    temp->prev=NULL;
    return temp;
}
node enqueue_f(node front,int data){
    node temp = newNode(data);
    if(front==NULL){
        front = temp;
        front->next=front;
        front->prev=front;
        return front;
    }
    else{
        node rear = front->prev;
        front->prev=temp;
        temp->next=front;
        temp->prev=rear;
        rear->next=temp;
        front=temp;
        return front;
    }
}
node enqueue_b(node front,int data){
    node temp=newNode(data);
    node rear=front->prev;
    if(rear==NULL){
        front = temp;
        front->next=front;
        front->prev=front;
        return front;
    }
    else{
        rear->next=temp;
        temp->prev=rear;
        rear=rear->next;
        rear->next=front;
        front->prev=rear;
        return front;
    }
}
int dequeue_f(node *front){
    
    if(*front==NULL){
        printf("Empty Queue");
        return -1;
    }
    else{
       
        node rear=(*front)->prev;
        if(rear->next==rear){
            int i=rear->data;
            (*front)=NULL;
            return i;
        }
        node temp=(*front);
        int data=temp->data; 
        (*front)=(*front)->next;
        rear->next=(*front);
        (*front)->prev=rear;
         free(temp);
        return data;
    }
}
int dequeue_b(node *front){
    if(*front==NULL){
        printf("Empty Queue");
        return -1;
    }
    else{
        node rear=(*front)->prev;
        if(rear->next==rear){
            int i=rear->data;
            (*front)=NULL;
            return i;
        }
        node temp=rear;
        rear=rear->prev;
        rear->next=(*front);
        (*front)->prev=rear;
        int data=temp->data;
        free(temp);
        return data;
    }
}
void display(node front){
    if(front==NULL){
        printf("Empty Queue");
        return;
    }
    node temp=front;
    do 
    {
        printf("%d ",temp->data);
        temp=temp->next;
    } while (temp!=front);
    
}
void main(){
    printf("DoubleEnded Queue using Doubly Linkedlist");
    node front = NULL;
    start:
    printf("\n1. Enqueue_front\n2. Enqueue_back\n3. Dequeue_front\n4. Dequeue_back\n5. Display\n");
    int choice, data;
    scanf("%d", &choice);
    switch (choice)
    {
    case 1:
        printf("Enter data : ");
        scanf("%d", &data);
        front=enqueue_f(front, data);
        break;
    case 2:
        printf("Enter data : ");
        scanf("%d", &data);
        front=enqueue_b(front, data);
        break;
    case 3:
        printf("dequeued : %d", dequeue_f(&front));
        break;
    case 4:
        printf("dequeued : %d", dequeue_b(&front));
        break;
    case 5:
        display(front);
        break;
    }
    goto start;
}