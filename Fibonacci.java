public class Fibonacci {
    // classic recursive fibonacci algorithm
    // not efficient.
    public static int fib1(int n) {
        return n <= 2 ? 1 : fib1(n-1) + fib1(n-2);
    }


    // iterative fibonacci
    // time: O(n), space: O(n)
    public static int fib2(int n) {
        int[] f = new int[n];
        f[0] = f[1] = 1;
        for (int i = 2; i < n; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f[n-1];
    }


    // another iterative method
    // time: O(n), space: O(1)
    public static int fib3(int n) {
        int a = 1, b = 1;
        for (int i = 3; i <= n ; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }


    // Method using recursive powering
    // [1 1] ^ n     {F(n+1) F(n)  ]
    // [1 0]      =  [F(n)   f(n-1)]
    // Where F(n) is n-th Fibonacci number
    // need 2x2 matrix multiplication helper method
    // time: O(n), space: O(1)
    public static void matmult(int[][] m1, int[][] m2) {
        int x = m1[0][0]*m2[0][0] + m1[0][1]*m2[1][0];
        int y = m1[0][0]*m2[0][1] + m1[0][1]*m2[1][1];
        int z = m1[1][0]*m2[0][0] + m1[1][1]*m2[1][0];
        int w = m1[1][0]*m2[0][1] + m1[1][1]*m2[1][1];

        m1[0][0] = x;
        m1[0][1] = y;
        m1[1][0] = z;
        m1[1][1] = w;
    }

    public static int fib4(int n) {
        int[][] M = {{1,0}, {0,1}};
        for (int i = 1; i < n; i++) {
            matmult(M, new int[][] {{1,1},{1,0}});
        }
        return M[0][0];
    }


    // Using recursive powering and fast exponentiation
    // ex) 3^8 = 3^2^2^2
    // time: O(log n), space: O(1)
    public static int fib5(int n) {
        int[][] M = {{1,0},{0,1}};
        matpow(n-1, M);
        return M[0][0];
    }

    // recursive helper method for fast exponentiation
    public static void matpow(int n, int[][] M) {
        if (n > 1) {
            matpow(n/2, M);
            matmult(M, M);
        }

        if (n%2 == 1)
            matmult(M, new int[][] {{1,1},{1,0}});
    }

    public static void main(String[] args) {
        System.out.println("Testing Fibonacci methods");
        System.out.println("Testing fib1");
        System.out.println("fib(5): " + fib1(5));
        System.out.println("Testing fib2");
        System.out.println("fib(6): " + fib2(6));
        System.out.println("Testing fib3");
        System.out.println("fib(7): " + fib3(7));
        System.out.println("Testing fib4");
        System.out.println("fib(8): " + fib4(8));
        System.out.println("Testing fib5");
        System.out.println("fib(9): " + fib5(9));
    }
}
