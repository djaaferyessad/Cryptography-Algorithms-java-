import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class hash {
            public static void main(String[] args) throws NoSuchAlgorithmException {
                Scanner obj = new Scanner(System.in);
                System.out.println("Enter ur message : ");
               String orignaltext1 =  obj.nextLine();
               System.out.println("Enter the second text to compare it with : ");
               String text2 = obj.nextLine();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                MessageDigest digest2 = MessageDigest.getInstance("SHA-256");
                byte[] encodehash = digest.digest(orignaltext1.getBytes(StandardCharsets.UTF_8));
                byte[] hashtext2 = digest.digest(text2.getBytes(StandardCharsets.UTF_8));
               // String s1 = String.format("%8s", Integer.toBinaryString(encodehash & 0xFF)).replace(' ', '0');
               System.out.println("---------------------------------------------------------------------------------------------------------------");
                System.out.println("# Hashed text 1 : "+bytesToHex(encodehash));
                System.out.println("# Hashed text 2 : "+bytesToHex(hashtext2));
                String binaryFormattext1 = HextoBinary(bytesToHex(encodehash));
                String binaryFormattext2 = HextoBinary(bytesToHex(hashtext2));
                System.out.println("# The binary format of text 1 : " + binaryFormattext1);
                 System.out.println("# The binary format of text 2 : " + binaryFormattext2);
                 double numchangedbit = 0 ;
                 double lengthchiphertext = binaryFormattext1.length();
                for(int i=0 ; i<lengthchiphertext ; i++)
                {
                    if(binaryFormattext1.charAt(i) != binaryFormattext2.charAt(i))
                    {
                        numchangedbit++;
                    }
                }
                System.out.println("-->"+numchangedbit);
                System.out.println("-->"+binaryFormattext1.length());
                double Avalanchenumber ;
                Avalanchenumber = numchangedbit / binaryFormattext1.length() * 100 ;

                System.out.println("# Number of Avalanche Effect is : " + Avalanchenumber + "\n");
           
            


            }
            private static String bytesToHex(byte[] hash) {
                StringBuilder hexString = new StringBuilder(2 * hash.length);
                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if(hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return hexString.toString();
            }          
            private static String HextoBinary(String hexdecimal)
            {
                String binarystring = "";
                for(int i=0 ; i<hexdecimal.length() ; i++)
                {

                    switch (hexdecimal.charAt(i)) {
                        case '0':
                            binarystring += "0000";
                            break;
                        case '1':
                            binarystring += "0001";
                            break;
                        case '2':
                            binarystring += "0010";
                            break;
                        case '3':
                            binarystring += "0011";
                            break;
                        case '4':
                            binarystring += "0100";
                            break;
                        case '5':
                            binarystring += "0101";
                            break;
                        case '6':
                            binarystring += "0110";
                            break;
                        case '7':
                            binarystring += "0111";
                            break;
                        case '8':
                            binarystring += "1000";
                            break;
                        case '9':
                            binarystring += "1001";
                            break;
                        case 'a':
                        case 'A':
                            binarystring += "1010";
                            break;
                        case 'b':
                        case 'B':
                            binarystring += "1011";
                            break;
                        case 'c':
                        case 'C':
                            binarystring += "1100";
                            break;
                        case 'd':
                        case 'D':
                            binarystring += "1101";
                            break;
                        case 'e':
                        case 'E':
                            binarystring += "1110";
                            break;
                        case 'f':
                        case 'F':
                            binarystring += "1111";
                            break;
                        default:
                            System.out.println("Invalid Hexadecimal Digit!");
                    }
                }
                return binarystring;

            }  
}
