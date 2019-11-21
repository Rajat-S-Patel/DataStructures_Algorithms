#include <stdlib.h>
#include <stdio.h>

struct Node
{
    int data;
    struct Node *next;
};
typedef struct Node *node;
node newNode(int data)
{
    node temp = (node)malloc(sizeof(struct Node));
    temp->data = data;
    temp->next = NULL;
    return temp;
}
int isEmpty(node head)
{
    return head == NULL;
}
void enqueue(node *rear, int data)
{
    node temp = newNode(data);
    if (isEmpty(*rear))
    {
        *rear = temp;
        (*rear)->next = (*rear);
    }
    else
    {
        node head = (*rear)->next;
        (*rear)->next = temp;
        (*rear) = (*rear)->next;
        (*rear)->next = head;
    }
}
void display(node head)
{
    if (isEmpty(head))
    {
        printf("Empty Queue");
        return;
    }
    node temp = head;
    do
    {
        printf("%d ", temp->data);
        temp = temp->next;
    } while ((temp != head));
}
int dequeue(node *head,node *rear)
{
    if(isEmpty(*head)){
        printf("Empty queue");
        return -1;
    }
    else{
        if((*rear)->next==(*rear)){
            //printf("test....");
            int data=(*head)->data;
            (*head)=(*rear)=NULL;
            return data;

        }
        else{
        int data=(*head)->data;
        (*head)=(*head)->next;
        (*rear)->next=(*head);
        return data;
        }
    }
}
void main()
{
    node rear = NULL;
    node head = NULL;
    printf("Circular Queue using Linked List : ");
start:
    printf("\n1. Enqueue\n2. Dequeue\n3. Traverse\n");
    int choice;
    scanf("%d", &choice);
    int data;
    switch (choice)
    {
    case 1:
        printf("Enter data : ");
        scanf("%d", &data);
        enqueue(&rear, data);
        if (head == NULL)
        {
            head = rear;
        }
        break;
    case 2:
        printf("Dequeued : %d", dequeue(&head,&rear));
        break;
    case 3:
        display(head);
        break;
    }
    goto start;
}