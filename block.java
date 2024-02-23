import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class block {
private static int index;
private static String timestamp;
private String data;
private static String previousHash;
private String Hash;

block(int index,String data,String previousHash)
{
    block.index = index ;
    this.data = data ;
    block.previousHash = previousHash;
    this.Hash = calculateHash();
}
public static void main(String[] args)
 {
SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
Date date = new Date();
timestamp = format.format(date).toString();

Blocks.addBlock("100");
Blocks.addBlock("200");

 }
 
public static block CreateBlock(int i , String d , String ph)
{
    block genesiseBlock = new block(i,d,ph);
    return genesiseBlock; 
}




public String calculateHash() {
String text=
String.valueOf(index+previousHash+String.valueOf(timestamp)+String.valueOf(data));
MessageDigest digest = null;
try {
// we will get the instance of SHA256
digest = MessageDigest.getInstance("SHA-256");
} catch (NoSuchAlgorithmException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
//by using digest() function, we will get hash as one dimensional byte array
final byte bytes[]=digest.digest(text.getBytes());
//we wanna use hexadecimal values not bytes in our program
// will convert byte into hexadecimal
final StringBuilder hexString=new StringBuilder();
for(final byte b: bytes) {
String hex=Integer.toHexString(0xff &b);
if(hex.length()==1) {
hexString.append("0");
}
hexString.append(hex);
}
return hexString.toString();

}

String getHash()
{
    return Hash ;
}
String getPervhash()
{
    return previousHash;
}



 


public static class Blocks {
private static List<block> blockChain = new ArrayList<>();
static int j=0 ;
public Blocks()
{
    block genesisBlock = block.CreateBlock(j, "hello ", "PerviousHash");
    blockChain.add(genesisBlock); 
}

public block retiveBlock()
{
    block returnedData = block.Blocks.blockChain.get(blockChain.size() - 1);
    return returnedData;
} 
public static boolean checkBlock(block newBlock, block prevBlock) {
    if (!newBlock.getHash().equals(newBlock.calculateHash())) {
        System.out.println("Hash mismatch detected in the new block.");
        return false;
    }
    if (!(newBlock.getPervhash().equals(prevBlock.getHash()))) {
        System.out.println("Previous hash mismatch detected.");
        return false;
    }
    return true;
}
public static void addBlock(String data) {
    if (blockChain.isEmpty()) {
        block genesisBlock = block.CreateBlock(j, data, "0");
        blockChain.add(genesisBlock);
        System.out.println("Genesis block created successfully.");
    } else {
        block prevBlock = blockChain.get(blockChain.size() - 1);
        block newBlock = block.CreateBlock(j, data, prevBlock.getHash());
        if (checkBlock(newBlock, prevBlock)) {
            blockChain.add(newBlock);
            System.out.println("Block added successfully.");
        } else {
            System.out.println("Block creation failed. Hash mismatch detected.");
        }
    }
    j++;
}

}


    
}
