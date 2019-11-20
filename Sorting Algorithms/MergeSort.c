#include <stdio.h>
#include <stdlib.h>
#include<time.h>

void Merge(int *arr,int low,int mid,int high){
    int n1 = (mid-low)+1;
    int n2 = high-mid;
    int i=0,j=0,k=low;
    int L[n1],R[n2];

    for(i=0;i<n1;i++){
        L[i]=arr[low+i];
    }
    for(i=0;i<n2;i++){
        R[i]= arr[mid+1+i];
    }
    i=0;
    j=0;
    k=low;
    while(i<n1&&j<n2){
            if(L[i]<=R[j]){
                arr[k]=L[i];
                i++;
            }
            else{
                arr[k]=R[j];
                j++;
            }
            k++;
    }
    while(i<n1){
        arr[k]=L[i];
        i++;
        k++;
    }
    while(j<n2){
        arr[k]=R[j];
        j++;
        k++;
    }

}
void MergeSort(int *arr,int low,int high){
    if(low<high){
        int mid =(low+high)/2;
       // printf("Mid : %d",mid);
        MergeSort(arr,low,mid);
        MergeSort(arr,mid+1,high);
        Merge(arr,low,mid,high); 
    }
}

int main(){
    printf("Enter Array size : ");
    int size;
    scanf("%d",&size);
    int arr[size];
    
    printf("Enter elements in array : ");
    int i;
    srand(time(0));
    for(i=0;i<size;i++){
      arr[i]=rand();
       // scanf("%d",&arr[i]);
    }
    MergeSort(arr,0,size-1);
    printf("\nSorted");
    for(i=0;i<size;i++){
        printf("%d ",arr[i]);
    }
}
