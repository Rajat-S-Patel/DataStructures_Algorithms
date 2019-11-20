#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int getMax(int *arr,int size){
    int i,max= arr[0];
    for(i=1;i<size;i++){
        if(arr[i]>max){
            max = arr[i];
        }
    }
    return max;
}
void Count_Sort(int *arr,int size){
    int max = getMax(arr,size);
    int aux_arr[max+1];
    int i=0;
    for(i=0;i<=max;i++){
        aux_arr[i]=0;
    }
    for(i=0;i<size;i++){
        aux_arr[arr[i]]++;
    }
    int j=0;
    for(i=0;i<max+1;i++){
        if(aux_arr[i]>0){
            while(aux_arr[i]>0&&j<size){
                arr[j]=i;
                j++;
                aux_arr[i]--;
            }
        }
    }


}
void main(){
    printf("Enter size of array : ");
    int size;
    scanf("%d",&size);
    printf("Enter numbers in array : ");
    int arr[size];
    int i;
    srand(time(0));

    for(i=0;i<size;i++){
        //arr[i]=rand()%10;
       scanf("%d",&arr[i]);
    }

    Count_Sort(arr,size);
    printf("Sorted array : \n");
    for(i=0;i<size;i++){
        printf("%d ",arr[i]);
    }
}
