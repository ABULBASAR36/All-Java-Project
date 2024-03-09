
package finalprectice;
import java.util.Scanner;
public class Binary_decemal {

    private static Object Intiger;
    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        String s1=cin.nextLine();
        String s2=cin.nextLine();
        String sr=cin.nextLine();
        int a,b,sum;
        a=Integer.parseInt(s1,2);
        b=Integer.parseInt(s2, 2);
        sum=a+b;
        String s3=Integer.toOctalString(sum);
        int i=Integer.parseInt(sr);
        System.out.println(i);
        System.out.println(s3);
        
    }
}
