import java.util.Scanner;
import java.util.*;
import java.io.*;
public class CaesarCipher {
    public static void main(String[] args)
    {
        Scanner myobj = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
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
        int shift = 0 ;
        do{
        System.out.println("Enter the Shift key : ");
        shift = myobj.nextInt();
        }while(shift >= 26 && shift <= 0);
        System.out.println("Enter the Message : ");
        String message = scan.nextLine();
        if(choise == 1)
        System.out.println(encrypted(message, shift, alphabet));
        else
        System.out.println(decrypted(message, shift, alphabet));

        myobj.close();
        scan.close();
        
    }
    static String encrypted(String message , int shiftkey , String alphabet)
    {
        message = message.toLowerCase();
        alphabet = alphabet.toLowerCase();
        String NewMessage = "" ;
        for(int i=0;i<message.length();i++)
        {
            if(message.charAt(i) == ' ')
            {
            NewMessage += ' ';
            }
            else{
            int charpostion = alphabet.indexOf(message.charAt(i));
            int shiftkeychar = (charpostion + shiftkey) % 26 ;
            NewMessage += alphabet.charAt(shiftkeychar);
            }
        }
        return NewMessage ;
    }
     static String decrypted(String message , int shiftkey , String alphabet)
    {
        message = message.toLowerCase();
        alphabet = alphabet.toLowerCase();
        String NewMessage = "" ;
        
        for(int i=0;i<message.length();i++)
        {
            if(message.charAt(i) == ' ')
            {
            NewMessage += ' ';
            }
            int charpostion = alphabet.indexOf(message.charAt(i));
            int shiftkeychar = (charpostion - shiftkey + 26) % 26 ;
            NewMessage += alphabet.charAt(shiftkeychar);
        }
        return NewMessage ;
    }


}
