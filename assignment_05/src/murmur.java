import java.math.BigInteger;
import java.nio.ByteBuffer;

public class murmur {
    private static final int SEED = 0; // Adjust seed as necessary

    public static int murmur3(byte[] data, int seed) {
        int c1 = 0xcc9e2d51; // magic number 1
        int c2 = 0x1b873593; // magic number 2
        int r1 = 15;
        int r2 = 13;
        int m = 5;
        int n = 0xe6546b64;

        int hash = seed;
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.rewind();

        while (buffer.remaining() >= 4) {
            int k = buffer.getInt();

            k *= c1;
            k = Integer.rotateLeft(k, r1);
            k *= c2;

            hash ^= k;
            hash = Integer.rotateLeft(hash, r2);
            hash = hash * m + n;
        }

        int remaining = 0;
        switch (buffer.remaining()) {
            case 3:
                remaining ^= (buffer.get() & 0xff) << 16;
            case 2:
                remaining ^= (buffer.get() & 0xff) << 8;
            case 1:
                remaining ^= (buffer.get() & 0xff);

                remaining *= c1;
                remaining = Integer.rotateLeft(remaining, r1);
                remaining *= c2;
                hash ^= remaining;
        }

        hash ^= data.length;
        hash ^= (hash >>> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >>> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >>> 16);

        return hash;
    }

    public static int hashBigInteger(BigInteger bigInteger) {
        byte[] data = bigInteger.toByteArray();
        return murmur3(data, SEED);
    }

    public static void main(String[] args) {
        long starttime, elapsedtime;
        BigInteger bigInt = new BigInteger("123456789012312312314567890"); // Example BigInteger
        starttime = System.nanoTime();
        int result = hashBigInteger(bigInt);
        elapsedtime = System.nanoTime() - starttime;
        System.out.println("exec_time:  " + Double.toString((double) elapsedtime / 1000000000));
        System.out.println("Hash of BigInteger: " + result);
    }
}