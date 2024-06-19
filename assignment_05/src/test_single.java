import java.math.BigInteger;

public class test_single {

    public static void main(String[] args) {
        HTable ht;

        ht = new HTable(3989);
        System.out.println(Integer.toString(ht.hf.hash(new BigInteger("123123"))));
        System.out.println(Integer.toString(ht.hf.hash(new BigInteger("123123"))));
    }
}