import java.math.BigInteger;
import java.util.Random;

public class splitter {

    public static BigInteger[] splitBigInteger(BigInteger bigInt, int k) {
        // Determine the total bit length and the bit length of each part
        int totalBits = bigInt.bitLength();
        int partSize = (totalBits + k - 1) / k; // Ensuring each part is nearly equal
        BigInteger[] parts = new BigInteger[k];

        // Mask to extract parts - assumes partSize does not exceed the maximum integer
        // size
        BigInteger mask = BigInteger.ONE.shiftLeft(partSize).subtract(BigInteger.ONE);

        // Extract each part
        for (int i = 0; i < k; i++) {
            int shiftDistance = i * partSize;
            parts[k - i - 1] = bigInt.shiftRight(shiftDistance).and(mask);
        }

        return parts;
    }

    public static int table_size_int = 3989;
    public static BigInteger table_size = BigInteger.valueOf(table_size_int);
    public static int part_cnt = 3;

    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger[] coefficients;
        coefficients = new BigInteger[part_cnt];

        for (int i = 0; i < part_cnt; i++) {
            coefficients[i] = BigInteger.valueOf(rand.nextInt(0, table_size_int));
        }

        BigInteger k = new BigInteger("12312312313");
        int bit_len = k.bitLength();
        int part_len = (bit_len + part_cnt - 1) / part_cnt;

        int hash = 0;

        BigInteger mask = BigInteger.ONE.shiftLeft(part_len).subtract(BigInteger.ONE);

        System.out.println("k:           " + k.toString(2));
        System.out.println("bit_len:     " + bit_len);
        System.out.println("part_len:    " + part_len);
        System.out.println("mask:        " + mask.toString(2));

        for (int i = 0; i < part_cnt; i++) {
            int shift = i * part_len;
            if (shift > bit_len) {
                break;
            }
            System.out.println(k.shiftRight(shift).and(mask).toString(2));
            hash += (k.shiftRight(shift).and(mask).multiply(coefficients[i]).mod(table_size)).intValue();
        }
        System.out.println(hash % table_size_int);
    }
}