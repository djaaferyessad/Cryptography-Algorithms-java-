import java.util.Scanner;
public class ChinesTheoremReminder {
    public static void main(String[] args)
    {
        Scanner myobj = new Scanner(System.in);
        System.out.println("Enter the congress number :");
        int Con = myobj.nextInt();
        int[] Tab1 = new int[Con];
        int[] Tab2 = new int[Con];
        int[] Tab3 = new int[Con];
        int[] Tab4 = new int[Con];
        int M = 1 ;
        for(int i=0;i < Con ; i++)
        {
            System.out.println("enter the a "+(i+1)+" : ");
            Tab1[i]=myobj.nextInt();
            System.out.println("enter the m "+(i+1)+" : ");
            Tab2[i]=myobj.nextInt();
            M*=Tab2[i];
            
        }
        // testing if m1,m2,m3....are coprime between them
        boolean test = true ;
        for(int i=0;i<Con;i++)
        {
            for(int j=0;j<Con;j++)
            {
                if(j == i)
                continue ;
                if(pgcd(Tab2[i], Tab2[j]) != 1)
                test = false ;
            }
        }
        if(test)
        {
        System.out.println("Big M = "+ M);

        int x = 0 ;
        for(int i=0;i < Con ; i++)
        {
            Tab3[i] = M/Tab2[i];
            Tab4[i] = inverse(Tab3[i], Tab2[i]);
            x += (Tab1[i]*Tab3[i]*Tab4[i]);
        }
        x = x % M ;
     System.out.println("X is : " + x);
    }
    else
    {
        System.out.println("the m that u entered are not coprime ");
    }


    }
    public static int inverse(int number ,int modenumber)
    {
        for(int i=0;i<modenumber;i++)
        {
            if( (number*i) % modenumber == 1)
            {
                return i ;
            }
        }
        return 0;
    }
    static int pgcd(int a , int b)
    {
        int rest = 1 , saveRest = 1;
        while(rest != 0)
        {
        saveRest = rest ;
        rest = a % b ; 
        a = b ;
        b = rest ;
        }
        return saveRest ;

    }
}
