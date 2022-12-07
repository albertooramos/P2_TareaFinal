package alberto;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthServiceApp {

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

}
