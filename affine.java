import java.util.Scanner;

public class affine {
    public static void main(String[] args)
    {
        Scanner myobj = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        String alphabet = "";
        
        // Populate the array with the English alphabet letters
        for (int i = 0; i < 26; i++) {
            alphabet += String.valueOf((char)('A' + i));
        }
        int choise = 0;
        do {
        System.out.println("Choose 1-for Encryption or 2-for Decryption : ");
        choise = myobj.nextInt();
        } while(choise != 1 && choise != 2);
        System.out.println("Enter the Message : ");
        String message = scanner.nextLine();
        int shift = 0 ;
        do{
        System.out.println("Enter the Shift key : ");
        shift = myobj.nextInt();
        }while(shift >= 26 && shift <= 0);
        int multiple = 0 ;
         do{
        System.out.println("Enter the multiple key : ");
        multiple = myobj.nextInt();
        }while((pgcd(multiple , 26) != 1 ) || (shift >= 26 && shift <= 0));
        if(choise == 1)
        System.out.println(Encryption(message,shift,multiple,alphabet));
        if(choise == 2)
        System.out.println(decryption(message, shift, multiple, alphabet));



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
    static String Encryption(String message , int shiftkey , int multiplekey , String alpha)
    {
        String newmessage = "";
        message = message.toLowerCase();
        alpha = alpha.toLowerCase();
        for(int i=0 ; i<message.length() ; i++)
        {
        int charpostion = alpha.indexOf(message.charAt(i));
        int charencryptedposition = (( alpha.indexOf(message.charAt(i)))* multiplekey  + shiftkey )  % 26 ;
        newmessage += alpha.charAt(charencryptedposition);
        }
        return newmessage ;

    }
    static String decryption(String message , int shiftkey , int multiplekey , String alpha)
    {
        String newmessage = "";
        message = message.toLowerCase();
        alpha = alpha.toLowerCase();
        for(int i=0 ; i<message.length() ; i++)
        {
        int charpostion = alpha.indexOf(message.charAt(i));
        int charencryptedposition = ((( alpha.indexOf(message.charAt(i))) - shiftkey + 26 ) * inverse(multiplekey, 26))  % 26 ;
        newmessage += alpha.charAt(charencryptedposition);
        }
        return newmessage ;
    }

}
