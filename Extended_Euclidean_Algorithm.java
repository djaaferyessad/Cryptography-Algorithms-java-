import java.util.Scanner;
public class Extended_Euclidean_Algorithm{
    public static void main(String args[]){
        Scanner myobj = new Scanner(System.in);
        System.out.println("enter number 1 : ");
        int A = myobj.nextInt();
        System.out.println("enter the second number : ");
        int B = myobj.nextInt();
        extendeCalculater(A, B);

    }
    public static void extendeCalculater(int A,int B)
    {
    int tab1[] = new int[4];
    int tab2[] = new int[4];
    tab1[1] = A ;
    tab1[2] = 1 ;
    tab1[3] = 0 ;
    tab2[1] = B ;
    tab2[2] = 0 ;
    tab2[3] = 1 ;
    int i = 1;
    while(tab1[1] != 0 && tab2[1] != 0)
    {
        if(i % 2 == 0)
        {
            tab2[0] = tab2[1] / tab1[1];
            tab2[1] = tab2[1] % tab1[1];
            tab2[2] = tab2[2] - (tab1[2] * tab2[0]);
            tab2[3] = tab2[3] - (tab1[3] * tab2[0]);
        }
        else
        {
            tab1[0] = tab1[1] / tab2[1];
            tab1[1] = tab1[1] % tab2[1];
            tab1[2] = tab1[2] - (tab2[2] * tab1[0]);
            tab1[3] = tab1[3] - (tab2[3] * tab1[0]);       
        }
        i++;
    }
    if((i-1) % 2 == 0)
    {
    System.out.println("the U is : "+ tab1[2] + "  the V is : " + tab1[3]) ;
    }
    else
    {
    System.out.println(" U is : "+ tab2[2] + "  the V is : " + tab2[3]) ;
    }


    }

    
}
