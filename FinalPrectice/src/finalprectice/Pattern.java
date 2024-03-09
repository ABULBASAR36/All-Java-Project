
package finalprectice;
import java.util.Scanner;
public class Pattern {
    public static void main(String[] args) {
         Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=n-i;j++)
            {
                System.out.print(" ");
            }
            for(int k=0;k<=i;k++)
            {
                System.out.print((char)('A'+k));
            }
            for(int l=i-1;l>=0;l--)
            {
                System.out.print((char)('A'+l));
            }
            System.out.println("");
        }
        for(int i=n-1;i>=0;i--)
        {
            for(int j=0;j<=n-i;j++)
            {
                System.out.print(" ");
            }
            for(int k=0;k<=i;k++)
            {
                System.out.print((char)('A'+k));
            }
            for(int l=i-1;l>=0;l--)
            {
                System.out.print((char)('A'+l));
            }
            System.out.println("");
        }
    }
}
