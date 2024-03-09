
package finalprectice;


// class Member {
//    String name,addrase,p_n;
//    int age,address,salary;
//    void printsalary()
//    {
//        System.out.println("Salary: "+salary);
//    }
//}
// class Employee extends Member{
//    String spe;
//    Employee(String name,String address,String p_n,int age,int salary){
//        this.name=name;
//        this.addrase=address;
//        this.age=age;
//        this.p_n=p_n;
//        this.salary=salary;
//        this.spe=spe;
//
//    }
//    void display()
//    {
//        System.out.println(name+" "+address+" "+age+" "+p_n+" "+salary+" "+spe);  
//    }
//}
//class Manager extends Member{
//    String dep;
//    Manager(String name,String address,String p_n,int age,int salary){
//        this.name=name;
//        this.addrase=address;
//        this.age=age;
//        this.p_n=p_n;
//        this.salary=salary;
//        this.dep=dep;
//
//    }
//    void display()
//    {
//        System.out.println(name+" "+address+" "+age+" "+p_n+" "+salary+" "+dep);  
//    }
//}
//public class Inharitance{
//    public static void main(String[] args) {
//        Employee emp=new Employee("Anik","hsj","019192898",23,6000);
//        emp.spe="hsh";
//        emp.display();
//        
//        Manager mn=new Manager("Anika","dds","0191554598",21,5000);
//        mn.dep="math";
//        mn.display();
//    }
//}
class reg{
    int len=10;
    int wd=20;
   
    void getarea(){
        System.out.println("area "+len*wd);
    }
    void perameter(){
        System.out.println("peremeter  "+2*(len+wd));
    }
}
class sq extends reg{
    void sr(int s){
       
           System.out.println("ahb :"+s*s);
    }
}
public class Inharitance {
    public static void main(String[] args) {
        reg r=new reg();
        r.getarea();
        r.perameter();
        sq q=new sq();
        q.sr(10);
        
        //q.perameter();
    }
}