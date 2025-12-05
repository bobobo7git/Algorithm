import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();

        int n = 1;
        long sum = 0;
        while (sum < s) {
            sum += n++;
        }
        if (sum != s) n--;
        System.out.println(n > 1 ? n-1 : 1);
    }
}
