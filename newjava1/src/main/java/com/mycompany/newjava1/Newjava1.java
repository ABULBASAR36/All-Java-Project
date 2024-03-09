
package com.mycompany.newjava1;

import java.util.Scanner;

public class Newjava1 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int a;
        a=in.nextInt();
        int sum=0;
        for(int i=1;i<=a;i++)
        {
            for(int j=1;j<=a;j++)
            {
                for(int k=1;k<=a;k++)
                {
                    if(k!=i&&k!=j&&i!=j)
                    {
                        sum++;
                        System.out.println(i+""+ j +"" +k);
                    }
                }
            }
        }
        System.out.println("sum:"+sum);
        //Scanner in = new Scanner(System.in);
    }
}
