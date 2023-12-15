import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.crypto.dsig.DigestMethod;

public class Digital_Signature_Using_Cipher_MessageDigest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator keypairgen = KeyPairGenerator.getInstance("RSA");
        keypairgen.initialize(2048);
        KeyPair keypair = keypairgen.genKeyPair();
        PrivateKey privatekey = keypair.getPrivate();
        PublicKey publickey = keypair.getPublic();
        byte[] messagebytes = Files.readAllBytes(Paths.get("signateur.txt"));
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodehash = digest.digest(messagebytes);
        System.out.println("--->" + bytesToHex(encodehash));
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, privatekey);
        byte[] encrytedhash = c.doFinal(encodehash);
        // Verfing 
        c.init(Cipher.DECRYPT_MODE, publickey);
        byte[] decryptedmessage = c.doFinal(encrytedhash);
        boolean test = Arrays.equals(encodehash, decryptedmessage);
        if(test)
        System.out.println("the signarteur ha been tested and it was true");
        else 
        System.out.println("the signaeur has been tested and it was false "); 
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
}
