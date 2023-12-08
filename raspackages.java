import java.util.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.*;
public class raspackages {
public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Scanner obj = new Scanner(System.in);
    KeyPairGenerator keygenrator = KeyPairGenerator.getInstance("RSA");
    keygenrator.initialize(2048);
    KeyPair pair = keygenrator.genKeyPair();
    PrivateKey privatekey = pair.getPrivate();
    PublicKey publickey = pair.getPublic();
    Cipher c = Cipher.getInstance("RSA");
    c.init(Cipher.ENCRYPT_MODE, publickey);
    System.out.println("Enter ur message  : ");
    String message = obj.nextLine();
    byte[] encryptedbytes = c.doFinal(message.getBytes());
    String encryptedmessage = new String(encryptedbytes);
    String encodemessage = Base64.getEncoder().encodeToString(encryptedbytes);
    System.out.println("The message after encryption is : "+encodemessage);
    c.init(Cipher.DECRYPT_MODE, privatekey);
    byte[] decryptedmessage = c.doFinal(encryptedbytes);
    String originalmessage = new String(decryptedmessage);
    System.out.println("The orignal message after decryption : " + originalmessage);
      
}
    
}
