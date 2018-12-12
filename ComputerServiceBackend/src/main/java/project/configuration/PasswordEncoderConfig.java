package project.configuration;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	private static final Logger LOG = Logger.getLogger(PasswordEncoderConfig.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				LOG.info(String.format("encoded \"%s\"", rawPassword));
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				LOG.info(String.format("compare: raw:\"%s\" and \"%s\"", rawPassword, encodedPassword));
				return rawPassword.equals(encodedPassword);
			}
		};

//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence rawPassword) {
//				String encoded = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
//				LOG.info(String.format("encoded \"%s\": \"%s\"", rawPassword, encoded));
//				return encoded;
//			}
//
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				LOG.info(String.format("compare: raw:\"%s\" and \"%s\"", rawPassword, encodedPassword));
//				return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
//			}
//		};
	}
}