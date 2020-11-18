package br.garou.com.br.alforno.infrastruture.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/images/**", "/css/**", "/js/**", "/public", "/sbpay").permitAll()
			.antMatchers("/client/**").hasRole(Role.CLIENT.toString())
			.antMatchers("/restaurant/**").hasRole(Role.RESTAURANT.toString())
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login-error")
				//.successHandler(null)
				.permitAll()
			.and()
				.logout().logoutUrl("/logout")
				.permitAll();
				}
	

}
