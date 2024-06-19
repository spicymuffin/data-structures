import java.math.BigInteger;
import java.util.Random;

public class HashFunction {

    public int radix = 0;
    public int part_cnt = 0;
    public int table_size_int = 0;
    public BigInteger table_size;
    public BigInteger[] coefficients;
    public Random rand = null;

    public int closest_power_of_two = 0;

    public int fast_pow(int number, int power) {
        if (power == 0) {
            return 1;
        } else if (power % 2 == 1) {
            return fast_pow(number, power - 1) * number;
        } else {
            return fast_pow(number * number, power / 2);
        }
    }

    public HashFunction(int m) {
        rand = new Random();

        radix = rand.nextInt(1, m);
        table_size = BigInteger.valueOf(m);
        table_size_int = m;
        part_cnt = 2;
        coefficients = new BigInteger[part_cnt];

        for (int i = 0; i < part_cnt; i++) {
            coefficients[i] = BigInteger.valueOf(rand.nextInt(0, m));
        }
    }

    public void generate_coefficients() {
        coefficients = new BigInteger[part_cnt];

        for (int i = 0; i < part_cnt; i++) {
            coefficients[i] = BigInteger.valueOf(rand.nextInt(0, table_size_int));
        }
    }

    // return 0 to m
    public int hash0(BigInteger k) {
        int bit_len = k.bitLength();
        int part_len = (bit_len + part_cnt - 1) / part_cnt;

        int hash = 0;

        BigInteger mask = BigInteger.ONE.shiftLeft(part_len).subtract(BigInteger.ONE);

        for (int i = 0; i < part_cnt; i++) {
            int shift = i * part_len;
            if (shift > bit_len) {
                break;
            }
            hash += (k.shiftRight(shift).and(mask).multiply(coefficients[i]).mod(table_size)).intValue();
        }
        // System.out.println(hash % table_size_int);
        return hash % table_size_int;
    }

    public int hash1(BigInteger k) {
        int bit_len = k.bitLength();
        int mask_len = bit_len/2
        BigInteger mask = BigInteger.ONE.shiftLeft(mask_len).subtract(BigInteger.ONE);
        return k.pow(2).shiftLeft(mask_len/2).and(mask);
    }


    public static int reverse_bits(int n, int iter) {
        int reversed = 0;
        for (int i = 0; i < iter; i++) {
            reversed <<= 1;
            reversed |= (n & 1);
            n >>= 1;
        }
        return reversed;
    }

    public int hash4(BigInteger k) {
        // System.out.println("key: " + bigInt.shiftRight(2).toString(10));
        // System.out.println("binkey: " + bigInt.toString(2));

        int part_len = closest_power_of_two;
        int bit_len = k.bitLength();
        int iter = bit_len / part_len + ((bit_len % part_len == 0) ? 0 : 1);
        // System.out.println("iter: " + iter);
        // System.out.println("bit_len: " + bit_len);

        BigInteger mask = BigInteger.ONE.shiftLeft(part_len).subtract(BigInteger.ONE);
        // System.out.println(mask.toString(2));

        int shift = 0;
        int hash = 0;
        int part = 0;

        for (int i = 0; i < iter; i++) {
            shift = i * part_len;
            part = k.shiftRight(shift).and(mask).intValue();
            // System.out.println(BigInteger.valueOf(part).toString(2));
            if (i % 2 == 0) {
                // part = reverse_bits(part, part_len);
            }
            // System.out.println(BigInteger.valueOf(part).toString(2));
            hash += part % table_size_int;
        }

        return hash % table_size_int;
    }
}
