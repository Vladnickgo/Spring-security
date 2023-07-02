import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 100; i++) {
            String encode = bCryptPasswordEncoder.encode("1234");
            System.out.println("'"+encode+"'");
        }
    }
}
