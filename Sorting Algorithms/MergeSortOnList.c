// Recursive merge sort on linked list
#include<stdio.h>
#include<stdlib.h>

struct Node{
    int data;
    struct Node *next;

};
typedef struct Node *node;
node newNode(int data){
    node temp=(node)malloc(sizeof(struct Node));
    temp->data=data;
    temp->next=NULL;
    return temp;
}
node insert(node first,int data){
    node temp=newNode(data);
    if(first==NULL){
        return temp;
    }
    else{
        node p=first;
        while(p->next!=NULL){
            p=p->next;
        }
        p->next=temp;
        return first;
    }
}

void splitList(node head,node *a,node *b){
    node fast,slow;
    slow=head;
    fast=head->next;
    while(fast->next!=NULL){
        fast=fast->next;
        if(fast->next!=NULL){
            slow=slow->next;
            fast=fast->next;
        }
    }
    (*a)=head;
    (*b)=slow->next;
    slow->next=NULL;

}
node merge(node a,node b){
    node result=NULL;
    if(a==NULL)
    return b;   //terminating condition
    else if(b==NULL)
    return a;

    if(a->data>b->data)
    {
        result=b;
        result->next=merge(a,b->next);

    }
    else{
        result=a;
        result->next=merge(a->next,b);
    }
    return result;
}
void display(node first){
    node p=first;
    while(p!=NULL){
        printf("%d ",p->data);
        p=p->next;
    }
}
void mergeSort(node *first){
   // printf("inside mergesort");
    node head=*first;
    node a,b;
    if(head==NULL||head->next==NULL){
        return;
    }
    splitList(head,&a,&b);

    mergeSort(&a);
    mergeSort(&b);

    *first=merge(a,b);

}

void main(){
    node first=NULL;
    printf("Enter data size : ");
    int size;
    scanf("%d",&size);
    printf("Enter data in sequence : ");
    int i=0;
    int data;
    srand(time(0));
    while(i<size){
      data=rand()%1000;     // takes random numbers in range 0 to 999
      //  scanf("%d",&data);
        first=insert(first,data);
        i++;
    }
   
    mergeSort(&first);
   // printf("after merge");
   printf("\nsorted array : \n");
    display(first);

}