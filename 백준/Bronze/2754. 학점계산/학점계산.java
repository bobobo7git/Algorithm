import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        String score = br.readLine();
        String ans = null;
        if (score.equals("A+")) {
            ans = "4.3";
        } else if (score.equals("A0")) {
            ans = "4.0";
        } else if (score.equals("A-")) {
            ans = "3.7";
        } else if (score.equals("B+")) {
            ans = "3.3";
        } else if (score.equals("B0")) {
            ans = "3.0";
        } else if (score.equals("B-")) {
            ans = "2.7";
        }  else if (score.equals("C+")) {
            ans = "2.3";
        } else if (score.equals("C0")) {
            ans = "2.0";
        } else if (score.equals("C-")) {
            ans = "1.7";
        }  else if (score.equals("D+")) {
            ans = "1.3";
        } else if (score.equals("D0")) {
            ans = "1.0";
        } else if (score.equals("D-")) {
            ans = "0.7";
        } else if (score.equals("F")) {
            ans = "0.0";
        }

        System.out.println(ans);
    }
}
