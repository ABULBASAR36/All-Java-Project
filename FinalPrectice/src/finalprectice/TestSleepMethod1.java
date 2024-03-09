
package finalprectice;
//
//public class Problem01 {
//        try {
//            int a[] = new int[5];
//            a[5] = 30 / 5;
//        } catch (ArithmeticException e) {
//            System.out.println(e + " occurs");
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println(e + " occurs");
//        }
//        System.out.println("Rest of the code");
//    }
//}
//


//threding 


//public class TestSleepMethod1 extends Thread{    
// public void run(){    
//  for(int i=1;i<5;i++){   
//  // the thread will sleep for the 500 milli seconds   
//    try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}    
//    System.out.println(i);    
//  }    
// }    
// public static void main(String args[]){    
//  TestSleepMethod1 t1=new TestSleepMethod1();    
//  TestSleepMethod1 t2=new TestSleepMethod1();    
//     
//  //t1.start();  
//t1.start();
//t2.start();  
//  
// }    
//}  
import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.TimeZone;
import java.util.*;

class Test {
    public static void main(String[] args) {
        try{
           int arr[]=new int[5];
           arr[5]=30/5;
        }catch(Exception e){
            System.out.println(e);
        }
        SimpleDateFormat cdt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        cdt.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
        System.out.println("\nNow: " + cdt.format(System.currentTimeMillis()));
    }
}