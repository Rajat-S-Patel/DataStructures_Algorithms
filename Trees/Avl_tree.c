#include<stdlib.h>
#include<stdio.h>
struct Avl
{
    int data,height;
    struct Avl *left,*right;

};
typedef struct Avl *node;
int height(node n){
    if(n==NULL)
        return 0;
    return n->height;
}
int max(int a,int b){
    return a>b?a:b;
}
int getBalance(node n){
    if(n==NULL){
        return 0;
    }
    return (height(n->left)-height(n->right));
}
node createNode(int data){
    node temp=(node)malloc(sizeof(struct Avl));
    temp->data=data;
    temp->height=1;
    temp->left=NULL;
    temp->right=NULL;
    return temp;
}
node rightRotate(node root){
    node t1 = root->left;
    node t2= t1->right;
    t1->right=root;
    t1->right->left=t2;
    root->height = max(height(root->left),height(root->right))+1;
    t1->height=max(height(t1->left),height(t1->right))+1;
    return t1;
}
node leftRotate(node root){
    node t1 = root->right;
    node t2 = t1->left;
    t1->left=root;
    t1->left->right=t2;

    root->height = max(height(root->left),height(root->right))+1;
    t1->height=max(height(t1->left),height(t1->right))+1;
    return t1;

}
node Insert(node root,int data){
    if(root==NULL){
        root = createNode(data);
        return root;
    }
    else{
        if(root->data<data){
            root->right=Insert(root->right,data);
        }
        if(root->data>data){
            root->left=Insert(root->left,data);
        }
        

    
    root->height=1+max(height(root->left),height(root->right));
    int balance = getBalance(root);
    if(balance>1&&data<root->left->data){
        printf("right rotate : %d",root->data);
        return rightRotate(root);
    }
    if(balance>1&&data>root->left->data){
        root->left=leftRotate(root->left);
        return rightRotate(root);
    }
    if(balance<-1&&data>root->right->data){
        printf("left rotate : %d",root->data);
        return leftRotate(root);
    }
    if(balance<-1&&data<root->right->data){
        root->right=rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

}

void PreOrder(node root){
    if(root!=NULL){
        printf("%d ",root->data);
        PreOrder(root->left);
        PreOrder(root->right);
    }
}
node inOrderSuccesser(node root){
    node current = root;
    while(current->left!=NULL){
        current= current->left;
    }
    return current;
}
node Delete(node root,int data){
    if(root==NULL){
        return root;
    }
    else{
        if(root->data<data)
            root->right=Delete(root->right,data);
        if(root->data>data)
            root->left=Delete(root->left,data);
        else if(data==root->data){
            if(root->left==NULL){
               node temp = root->right;
               free(root);
               return temp; 
            }
            else if(root->right==NULL){
                node temp = root->left;
                free(root);
                return temp;
            }
            else{
                node temp = inOrderSuccesser(root->right);
                root->data=temp->data;
                root->right=Delete(root->right,temp->data);
            }
            
        }

        root->height=1+max(height(root->left),height(root->right));
        int balance = getBalance(root);

        if(balance<-1&&getBalance(root->right)<0){
            return leftRotate(root);
        }
        if(balance<-1&&getBalance(root->right)>=0){
            root->right=rightRotate(root->right);
            return leftRotate(root);
        }
        if(balance>1&&getBalance(root->left)>0){
           return rightRotate(root);
        }
        if(balance>1&&getBalance(root->left)<=0){
            root->left= leftRotate(root->left);
            return rightRotate(root);
        }
        return root;    
    }
}
void main(){
    node root = NULL;
    printf("AVL TREE OPERATIONS ---------------------------------------------------------");
    start:
    printf("\n1. Insert\n2. Preorder traversal\n3. Delete");
    int choice;
    scanf("%d",&choice);
    int data;
    switch(choice){
        case 1:
            printf("Enter data : ");
            scanf("%d",&data);
            root = Insert(root,data);
            break;
        case 2:
            PreOrder(root);
            break;
        case 3:
            printf("Enter data to be deleted : ");
            scanf("%d",&data);
            root = Delete(root,data);
            break;

    }
    goto start;
}