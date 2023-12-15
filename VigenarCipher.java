import java.util.*;
public class VigenarCipher {
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
        System.out.println("Enter the key word : ");
        String key = scanner.nextLine();
        System.out.println("Enter the message : ");
        String message = scanner.nextLine();
        System.out.println(encrypted(message, key, alphabet,choise));
        
    }
    static String encrypted(String message , String key , String alpha, int type ) 
    {
        message = message.toLowerCase();
        alpha = alpha.toLowerCase();
        String NewMessage = "";
        int lengthOfKey = key.length();
        int s = 0;
        for(int i=0 ; i<message.length();i++)
        {
            if(message.charAt(i) == ' ')
            {
            NewMessage += " ";
            continue;
            }
            if(s >= lengthOfKey )
            {
                s = s % lengthOfKey;
            } 
            int shiftkeychar = alpha.indexOf(key.charAt(s));
            if(type == 1)
            {
                NewMessage += encrypted(message.charAt(i), shiftkeychar, alpha);
            }
            else 
            {
                 NewMessage += decrypted(message.charAt(i), shiftkeychar, alpha);
            }

            s++;
        }


        return NewMessage ;
    }
    static char encrypted(char message , int shiftkey , String alphabet)
    {
            

            int charpostion = alphabet.indexOf(message);
            int shiftkeychar = (charpostion + shiftkey) % 26 ;
            return alphabet.charAt(shiftkeychar); 
                        
    }
    static char decrypted(char message , int shiftkey , String alphabet)
    {

            int charpostion = alphabet.indexOf(message);
            int shiftkeychar = ((charpostion - shiftkey) + 26 )% 26 ;
            return alphabet.charAt(shiftkeychar); 
                       
    }
    
    
}
