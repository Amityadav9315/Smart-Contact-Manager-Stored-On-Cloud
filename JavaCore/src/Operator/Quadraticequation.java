package operators;
import java.util.*;
public class Quadraticequation {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a,b,c;
            double r1,r2;
            System.out.println("Entre the number");
            a=sc.nextInt();
            b=sc.nextInt();
            c=sc.nextInt();
            r1=(-b+Math.sqrt(b*b-4*a*c))/(2*a);
            r2=(-b-Math.sqrt(b*b-4*a*c))/(2*a);
            System.out.println(r1+" "+r2);
        }


    }
}