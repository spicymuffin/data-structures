import java.math.BigInteger;

class HTable {
	
	private HEntry[]		t;
	private int				sz;
	private HashFunction	hf;
	
	public 					HTable(int size) {
		sz = size;
		t = new HEntry[size];
		for (int i = 0; i < size; i++) t[i] = null;
		hf = new HashFunction(size);
	}
	
	public boolean			insert(BigInteger k, String p) {
		int		i, h;
		
		h = hf.hash(k);
		i = h;
		do {
			if (t[i] != null) {
				if (k.equals(t[i].key)) return false;
			} else {
				t[i] = new HEntry(k, p);
				return true;
			}
			i = (i + 1) % sz;
		} while (i != h);
		return false;
	}
	
	public String			query(BigInteger k) {
		int		i, h;
		
		h = hf.hash(k);
		i = h;
		do {
			if (t[i] == null) return null;
			if (k.equals(t[i].key)) return t[i].payload;
			i = (i + 1) % sz;
		} while (i != h);
		return null;
	}

	// a simple test driver
	public static void		main(String[] args) {
		HTable	ht;
		String	r;
		
		ht = new HTable(3989);
		do {
			if (ht.insert(new BigInteger("2103"), "p1") != true) break;
			if (ht.insert(new BigInteger("2103"), "p2") != false) break;
			if (ht.insert(new BigInteger("2104"), "p3") != true) break;
			if (ht.query(new BigInteger("2105")) != null) break;
			r = ht.query(new BigInteger("2103"));
			if (r == null) break;
			if (!r.equals("p1")) break;
			r = ht.query(new BigInteger("2104"));
			if (r == null) break;
			if (!r.equals("p3")) break;
			System.out.println("Passed.");
			return;
		} while(false);
		System.out.println("Error.");
	}	
	
}
