#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void Count_Sort(int *arr,int size,int position){
   
    int output[size];
    int aux_arr[10];
    int i=0;
    for(i=0;i<10;i++){
        aux_arr[i]=0;
    }
    i=0;
    int div =1;
    while(position>0){
        div*=10;
        position--;
    }
  
    for(i=0;i<size;i++){
        aux_arr[(arr[i]/(div))%10]++;
    }

    for(i=1;i<10;i++){
        aux_arr[i]+=aux_arr[i-1];
    }

    int j=0;
    for(i=size-1;i>=0;i--){
        output[aux_arr[(arr[i]/(div))%10]-1]=arr[i];
         aux_arr[(arr[i]/(div))%10]--; // we use reverse order because next element with same
                                         // digit and same place will be put after present element
    }                                       // with same digit at a given place.
     for (i = 0; i < size; i++) 
        arr[i] = output[i];


}
int findMaxDigits(int *arr,int size){
    int i=0,max = arr[0];
    for(i=1;i<size;i++){
            if(arr[i]>max)
                max= arr[i];
    }
    int count = 0;
    while(max>0){
        max/=10;
        count++;
    }
    return count;
}
int* Radix_Sort(int *arr,int size){
    int aux[10];
    int max_digits=findMaxDigits(arr,size);
   // printf("max size : %d",max_digits);
    int i;
    int output[size];
    for(i=0;i<max_digits;i++){
        Count_Sort(arr,size,i);
    }
   
}

void main(){
    printf("Enter size of array : ");
    int size;
    scanf("%d",&size);
    printf("Enter array : ");
    int i;
    int arr[size];
    srand(time(0));
    for(i=0;i<size;i++){
        arr[i]=rand()%10000;
        //scanf("%d",&arr[i]);
    }

    Radix_Sort(arr,size);
    printf("Sorted array : \n");
   for(i=0;i<size;i++){
        printf("%d ",arr[i]);
    }

}
