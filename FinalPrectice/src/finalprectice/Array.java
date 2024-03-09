
package finalprectice;
import java.util.Arrays;
import java.util.Scanner;
public class Array {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        {
           arr[i]=in.nextInt();
        }
        Arrays.sort(arr, 0, n);
        //System.out.println("second maximum: "+arr[n-2]);
        //System.out.println("orginal Array: "+ Arrays.toString(arr));
        int fs=0;
        int ls=n-1;
        int key=30;
        int mid=fs+ls/2;
        while(fs<=ls)
        {
           if(arr[mid]<key)
           {
               fs=mid+1;
           }
           else if(arr[mid]==key)
           {
               System.out.println("Number is found and index is :"+mid);
               break;
           }
           else
               ls=mid-1;
           mid=(fs+ls)/2;
        }
        if(fs>ls)
           {
               System.out.println("Number is not found");
           //break;
           }
    }
}
