package quiz.basic;

import java.security.MessageDigest;
import java.util.Scanner;

public class basic06 {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            String inputData = sc.nextLine();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(inputData.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : md.digest()) {
                builder.append(String.format("%02x", b));
            }
            System.out.println(builder.toString());
        } catch (Exception ex) {
        }

    }
}
