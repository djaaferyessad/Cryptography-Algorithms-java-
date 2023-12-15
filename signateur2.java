import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyPairGeneratorSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class signateur2 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        KeyPairGenerator keypairgen = KeyPairGenerator.getInstance("RSA");
        keypairgen.initialize(2048);
        KeyPair keypair = keypairgen.generateKeyPair();
        PrivateKey privatekey = keypair.getPrivate();
        PublicKey publickey = keypair.getPublic() ;
        byte[] message = Files.readAllBytes(Paths.get("signateur.txt"));
        Signature signateur = Signature.getInstance("SHA256withRSA");
        signateur.initSign(privatekey);
        signateur.update(message);
        byte[] signedmessage = signateur.sign();
        // Veriffing the ssignateur 
        signateur.initVerify(publickey);
        signateur.update(message);
        boolean test = signateur.verify(signedmessage);
        if(test)
        System.out.println("the message has been successfully Verfied and it was true");
        else 
        System.out.println("the message was verified but it was false  ");
        
        

    }
}
