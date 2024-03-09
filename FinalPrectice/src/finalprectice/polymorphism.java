
package finalprectice;

class Shap{
    void show(){
        System.out.println("this shop show");
    }
    void getinfo(){
        System.out.println("this is shap get");
    }
}
class circle extends Shap{
    void show(){
        System.out.println("this circle show");
    }
    void getinfo(){
        System.out.println("this is circle get");
    }
}
class rectangle extends Shap{
    void show(){
        System.out.println("this reg show");
    }
    void getinfo(){
        System.out.println("this is reg get");
    }
}
public class polymorphism {
    public static void main(String[] args) {
        circle c=new circle();
        c.show();
        c.getinfo();
        Shap s=new Shap();
        s.show();
        s.getinfo();
        rectangle r=new rectangle();
        r.show();
        r.getinfo();
    }
}
