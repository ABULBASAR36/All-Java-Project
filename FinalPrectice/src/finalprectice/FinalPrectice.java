
package finalprectice;

class BankAccount{
    int acc_no;
    String name;
    float amount;
    void insert(int a,String n,float amt){
        acc_no=a;
        name=n;
        amount=amt;
    }
    void deposit(float balance)
    {
        amount+=balance;
        System.out.println(balance+"deposite ");
    }
    void withdraw(float balance)
    {
        if(amount<balance)
          System.out.println("Insufficient Balance");  
        else
        {
            amount-=balance;
            System.out.println(balance+"Withdraw");
        } 
    }
    void checBalance()
    {
        System.out.println("Balance is: "+ amount);
    }
    void display()
    {
        System.out.println(acc_no+" "+name+" "+amount);
    }
}
//class SavingAccount extends BankAccount{
//    SavingAccount(int acc_no,float amount)
//    {
//        super(acc_no ,amount);
//    }
//}
 class TestAccount{  
     
   public static void main(String[] args) {
       BankAccount a1=new BankAccount(); 
       a1.insert(832345,"Ankit",1000);  
a1.display();  
a1.checBalance();  
a1.deposit(40000);  
a1.checBalance();  
a1.withdraw(15000);  
a1.checBalance();  
    }
}

////withdraw method  
//void withdraw(float amt){  
//if(amount<amt){  
//System.out.println("Insufficient Balance");  
//}else{  
//amount=amount-amt;  
//System.out.println(amt+" withdrawn");  
//}  
//}  
////method to check the balance of the account  
//void checkBalance(){System.out.println("Balance is: "+amount);}  
////method to display the values of an object  
//void display(){System.out.println(acc_no+" "+name+" "+amount);}  
//}  
////Creating a test class to deposit and withdraw amount  
//class TestAccount{  
//public static void main(String[] args){  
//Account a1=new Account();  
//a1.insert(832345,"Ankit",1000);  
//a1.display();  
//a1.checkBalance();  
//a1.deposit(40000);  
//a1.checkBalance();  
//a1.withdraw(15000);  
//a1.checkBalance();  
//}}   