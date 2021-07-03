package PasswordManager;

import PasswordManager.utils.TokensUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PasswordManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApplication.class, args);
		String token = new TokensUtil().createToken();

		Boolean valid = new TokensUtil().validateTokenExpiration(token);

	}
}
