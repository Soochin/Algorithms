import java.sql.SQLOutput;

public class KaratsubaMult {
    public static long karatsubaMult(long x, long y) {
        if (x < 10 || y < 10)
            return x * y;

        double n = Math.round(getNumDigit(x));

        long a = (long) (x / Math.pow(10, Math.round(n/2)));
        long b = (long) (x % Math.pow(10, Math.round(n/2)));
        long c = (long) (y / Math.pow(10, Math.round(n/2)));
        long d = (long) (y % Math.pow(10, Math.round(n/2)));

        long first = karatsubaMult(a, c);
        long second = karatsubaMult(b, d);
        long third = karatsubaMult(a + b, c + d);

        return (long) (first * Math.pow(10, Math.round(n)) +
                      (third - first - second) * Math.pow(10, Math.round(n/2)) +
                       second);
    }

    private static double getNumDigit(long n) {
        return Long.toString(n).length();
    }

    public static void main(String[] args) {
        System.out.println("1234*5678 = " + karatsubaMult(1234, 5678));
    }
}
