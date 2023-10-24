import java.util.Scanner;

public class pgcd {
    public static void main(String[] args)
    {
        Scanner myobj = new Scanner(System.in);
        System.out.println("enetr first number : ") ;
        int A = myobj.nextInt();
        System.out.println("enter the scond number : ") ;
        int b = myobj.nextInt();
        int s = A;
        A = Math.max(A,b) ;
        b = Math.min(s,b);      
        pgcd(A,b);
    }
    static void pgcd(int a , int b)
    {
        int rest = 1 , saveRest = 1;
        while(rest != 0)
        {
        saveRest = rest ;
        rest = a % b ; 
        a = b ;
        b = rest ;
        }
        System.out.print("pgcd =  ");
        System.out.println(saveRest);

    }
}