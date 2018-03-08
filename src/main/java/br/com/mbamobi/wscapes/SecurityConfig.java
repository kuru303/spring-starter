package br.com.mbamobi.wscapes;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	private final String usersQuery = "SELECT DS_USERNAME, DS_SENHA, DECODE(ST_ATIVO,'S','true','false') ATIVO FROM TB_USUARIO WHERE DS_USERNAME = ?";
	
	private final String rolesQuery = "SELECT DS_USERNAME, 'ROLE_ADMIN' FROM TB_USUARIO WHERE DS_USERNAME = ?";
	
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
//		 auth.inMemoryAuthentication()
//		        .withUser("user").password("secret1").roles("USER")
//		        .and()
//		        .withUser("admin1").password("df6e1620-f7ec-459f-89c9-senhapadrao").roles("ADMIN");
		
		auth.jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder());
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .cors().and()
		  .httpBasic().and().authorizeRequests()
		  // .antMatchers("/students/**").hasRole("USER")
		  .antMatchers("/**").hasRole("ADMIN")
		  .and()
		  .csrf().disable().headers().frameOptions().disable();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	public static void main(String[] args) {
		String senha = "123";
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(senha, salt);

		System.out.println(hashed_password);
	}

}
