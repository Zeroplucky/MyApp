package proxy;

/**
 * Created by Administrator on 2018/11/26.
 */

public class RealSubject implements Subject {

    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
