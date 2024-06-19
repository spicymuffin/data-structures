import java.math.BigInteger;
import java.util.Random;

public class HashFunction {

    public int table_size_int = 0;
    public BigInteger table_size;
    public BigInteger coefficient;
    public Random rand = null;

    public HashFunction(int m) {
        rand = new Random();
        table_size = BigInteger.valueOf(m);
        table_size_int = m;
        coefficient = BigInteger.valueOf(rand.nextInt(1, m));
    }

    // i tested a million hashing functions
    // but for some reason this gave the smallest standard deviation
    // when calculating number of keys per bucket
    // while not being that bad in performance

    // return 0 to m
    public int hash(BigInteger k) {
        return k.multiply(coefficient).mod(table_size).intValue();
    }
}
