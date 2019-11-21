#include<stdlib.h>
#include<stdio.h>

struct Node{
    int data;
    struct Node *next;
};
typedef struct Node *node;
node newNode(int data){
    node temp = (node)malloc(sizeof(struct Node));
    temp->data=data;
    temp->next=NULL;
    return temp;
}
int isEmpty(node head){
    return head==NULL;
}
node enqueue(node rear,int data){
   node temp = newNode(data);
   if(isEmpty(rear)){
      rear=temp;
   }
   else{
       rear->next=temp;
       rear=rear->next;
   }
   return rear;
}
int dequeue(struct Node **head){
    if(!isEmpty(*head)){
       int data = (*head)->data;       
        (*head)=(*head)->next;
        return data;
    }
    else{
        printf("Empty queue");
        return -1;
    }
}
void display(node head){
    node temp = head;
    if(isEmpty(head)){
        printf("Empty queue");
    }
    while(temp!=NULL){
        printf("%d ",temp->data);
        temp=temp->next;
    }
}

void main(){
    node rear = NULL;
    node head= NULL;
    printf("Queue using Linked List : ");
    start:
    printf("\n1. Enqueue\n2. Dequeue\n3. Traverse\n");
    int choice;
    scanf("%d",&choice);
    int data;
    switch(choice){
        case 1:
            printf("Enter data : ");
            scanf("%d",&data);
            rear=enqueue(rear,data);
            if(head==NULL)
                head=rear;
            break;
        case 2:
            printf("Dequeued : %d",dequeue(&head));
            break;
        case 3:
            display(head);
            break;
    }
    goto start;
}