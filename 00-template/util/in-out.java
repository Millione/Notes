import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class Task {
        static final long MOD = (long) (1e9 + 7);
 
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            /**
             * write your main code here
             */
        }
        
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public double nextDouble()
		{
			return Double.parseDouble(next());
		}

		public long nextLong()
		{
			return Long.parseLong(next());
		}
        
        public BigInteger nextBigInteger() { 
            return new BigInteger(next()); 
        }

    	public BigDecimal nextBigDecimal() { 
        	return new BigDecimal(next()); 
        }
    }

}