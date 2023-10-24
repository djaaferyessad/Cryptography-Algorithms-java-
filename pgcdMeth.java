import java.util.Scanner ;
public class pgcdMeth {
    public static void main(String[] args)
    {
        Scanner myobj = new Scanner(System.in);
        System.out.println("enter first number : ") ;
        int a = myobj.nextInt();
       System.out.println("enter Second number : ") ;
       int b = myobj.nextInt();
       int s = a;
        a = Math.max(a,b) ;
        b = Math.min(s,b);  

       System.out.print("Pgcd = ") ;
       System.out.println(pgcd(a, b)) ;
    }
    static int pgcd(int a , int b)
    {
        if(a%b == 0)
        return b;
        return pgcd(b,a%b);
    }
}
