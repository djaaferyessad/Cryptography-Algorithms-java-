import java.util.*;
public class hill {
    public static void main(String[] args)
    {
        // note u can watch nesoacadimy hill cipher to unserstand more 
        Scanner myobj = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        String alphabets = "";
        String EncryptMessage = "";
        String DescryptedMessage = "";
        int blocksize = 0 ;
        for(int i=0 ; i<26 ; i++)
        {
            alphabets += String.valueOf((char)('A'+i));
        }
        int sqrt = 0 ;
        String key = "";
        // he will keep him on while unless he entered square matrix 
        do{
        System.out.println("Enter the key (square matrix ) in Decription accept only (2*2 or 3*3 matrix) : ");
        key = scan.nextLine();
        sqrt = (int)Math.sqrt(key.length()) ;

        } while(Math.sqrt(key.length()) - sqrt != 0 );
        System.out.println("Enter the message : ");
        String message = scan.nextLine();
        blocksize = sqrt ;
        // removing spaces 
        message = message.replace(" ", "");
        int choise = 0 ;
        do{
        System.out.println("choose 1-Encryption and 2-Decryption ");
        choise = myobj.nextInt();
        }while(choise != 1 && choise != 2);
        
        // chiffring by blocks it depends on the matrix if 2*2 it chiffers 2 blocks if 3*3 3 blocks and so on
        for(int i=0 ; i<message.length() ; i+=blocksize)
        {
            // finding the end of the block for example it start from 0 to 2 
            int endindex = Math.min(i + blocksize ,message.length());
            String block = message.substring(i, endindex);
            //if it doesn't fit the block size we add z (padding)
            while(block.length() != blocksize)
            {
                message += 'z';
                endindex = Math.min(i + blocksize ,message.length());
                block = message.substring(i, endindex);
            }
            
            // we add each encrypted block to String 
            if(choise == 1)
            EncryptMessage += encryption(block, key, alphabets);
            else 
            DescryptedMessage += decryption(block, key, alphabets);
        }
        if(choise == 1)
        System.out.println(EncryptMessage); 
        else
         System.out.println(DescryptedMessage); 
    }
        
    
    public static String encryption(String message ,String key ,String alphabets)
    {
        alphabets = alphabets.toLowerCase();
        message = message.toLowerCase() ;
        int size = (int)Math.sqrt(key.length());
        int[][] keymatrix = new int[size][size];
        int[] messagematrix = new int[size];
        int[] encyptmatrix = new int[size];
        String encryptedmessage = "";
        int s = 0 ;
        
        // filling the key matrix && the message table 
        for(int i=0 ; i<size ; i++)
        {
            messagematrix[i] = alphabets.indexOf(message.charAt(i));
            for(int j=0 ; j<size ; j++)
            {
                keymatrix[i][j] = alphabets.indexOf(key.charAt(s));
                s++;
            }
        }
        if(pgcd(Det(keymatrix, size),26) != 1)
        {
            return "Determinux n'est coprime avec 26 ";
        }
        else {
        // multiplaying the matrix of the key and message : P * k = C and give his coresponding letter 
         for(int i=0 ; i<size ; i++)
        {
            for(int j=0 ; j<size ; j++)
            {
                encyptmatrix[i] += keymatrix[i][j]*messagematrix[j];
                encyptmatrix[i] = encyptmatrix[i] % 26 ;
            }
            encryptedmessage += alphabets.charAt(encyptmatrix[i]);
        }
        return encryptedmessage ;
         }
    }
    public static String decryption(String message , String key , String alphabets)
    {
        alphabets = alphabets.toLowerCase();
        message = message.toLowerCase() ;
        int size = (int)Math.sqrt(key.length());
        int[][] keymatrix = new int[size][size];
        int[] messagematrix = new int[size];
        int[] decyptmatrix = new int[size];
        int[][] inverseK = new int[size][size];
        String decryptedmessage = "";
        int s = 0 ;
        // filling the key matrix && the message table 
        for(int i=0 ; i<size ; i++)
        {
            messagematrix[i] = alphabets.indexOf(message.charAt(i));
            for(int j=0 ; j<size ; j++)
            {
                keymatrix[i][j] = alphabets.indexOf(key.charAt(s));
                s++;
            }
        }
        if(pgcd(Det(keymatrix, size),26) != 1)
        {
            return "Determinux n'est coprime avec 26 ";
        }
        else {
        int det = Det(keymatrix, size);
        int inverseDet = inverse(det, 26);
        int[][] adjmatrix = Adj(keymatrix , size);
        // findind the inverse key matrix 
        for(int i=0 ; i<size ; i++)
        {
            for(int j=0 ; j<size ; j++)
            {
                inverseK[i][j] = inverseDet * adjmatrix[i][j] % 26;
                if(inverseK[i][j] < 0)
                inverseK[i][j] = inverseK[i][j] + 26 ;

            }
        }
// giving each number his corresponding letter ex : 0 --> a and do : C*kˆ-1 
         for(int i=0 ; i<size ; i++)
        {
            for(int j=0 ; j<size ; j++)
            {
                decyptmatrix[i] += inverseK[i][j]*messagematrix[j];
                decyptmatrix[i] = decyptmatrix[i] % 26 ;
            }
            decryptedmessage += alphabets.charAt(decyptmatrix[i]);
        }
        return decryptedmessage ;
    }

    }
    public static int Det(int[][] keymatrix , int size)
    {
        int det = 0 ;
        // the same way as we calculated on paper det of 2*2 
        if(size == 2)
        {
            
            det = (keymatrix[0][0] * keymatrix[1][1]) - (keymatrix[1][0] * keymatrix[0][1]);
        }
        // the same way as we calculated on paper det of 3*3 
        else
        {
            det = (keymatrix[0][0]*(keymatrix[1][1]*keymatrix[2][2]-keymatrix[2][1]*keymatrix[1][2]) - 
                  keymatrix[0][1]*(keymatrix[1][0]*keymatrix[2][2]-keymatrix[2][0]*keymatrix[1][2]) +
                  keymatrix[0][2]*(keymatrix[1][0]*keymatrix[2][1]-keymatrix[2][0]*keymatrix[1][1]) ) % 26 ;
        }
        // here fro ex : when the number is (-) and we do mode 26 he gives as (-) so we add 26 to turn it positive key
        if(det < 0)
        return det+26;
        else
        return det;
    }
    public static int[][] Adj(int[][] keymatrix , int size)
    {
        int[][] adjmatrix = new int[5][5];
      
        // we calculating adjusnt matrix using the same way we use in paper
        int s = 0 , p = 0;
        if(size == 2)
        {
            s = keymatrix[0][0];
            keymatrix[0][0] = keymatrix[1][1];
            keymatrix[1][1] = s ;
            keymatrix[0][1] = keymatrix[0][1] * -1 ;
            keymatrix[1][0] = keymatrix[1][0] * -1 ;
        }
        else 
        {
            // creating matrix 5*5 
            for(int i=0 ; i<size ; i++)
            {
                for(int j=0 ; j<size ; j++)
                {
                    adjmatrix[i][j] = keymatrix[i][j];
                }
            }
            // adding two first colums to the ed=nd of the new matrix we create 
            for(int i=0 ; i<=2 ; i++)         
            {
                s = 0;
                for(int j=3 ; j<5 ; j++,s++)
                {
                    adjmatrix[i][j] = keymatrix[i][s];
                }
            }
            // adding two first lines to the end of the matriz that we created 
            s = 0;
            for(int i=3 ; i<5 ; i++)         
            {
                
                for(int j=0 ; j<5 ; j++)
                {
                    adjmatrix[i][j] = adjmatrix[s][j];
                }
                s++;
            }
            // calculate the adjustment 
            s=0 ;
            for(int i=1 ; i<=3 ; i++)
            {
                p=0;
                for(int j=1 ; j<=3 ; j++)
                {
                    keymatrix[s][p] = (adjmatrix[j][i] * adjmatrix[j+1][i+1] - adjmatrix[j+1][i] * adjmatrix[j][i+1]) % 26 ;
                    p++;
                }
                s++;
            }
            
        }
        return keymatrix;
    }
    public static int inverse(int number ,int modenumber)
    {
        for(int i=1;i<modenumber;i++)
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
