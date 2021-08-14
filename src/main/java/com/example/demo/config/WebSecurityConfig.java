package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizeHandler;

	@Bean
	public AuthTokenFilter authenciationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenciation) throws Exception {
		authenciation.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("kai")
				.password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("sena")
				.password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("USER");
//    auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("sena").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/login-google/**").permitAll()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/**").permitAll()
				.antMatchers("/api/**").permitAll()
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizeHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenciationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

//		// Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
//		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//		// Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn /user/**
//		http.authorizeRequests().antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
//		// Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
//		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//		// Cấu hình cho Login Form.
//		http.authorizeRequests().and().formLogin()//
//				.loginProcessingUrl("/j_spring_security_login")//
//				.loginPage("/login")//
//				.defaultSuccessUrl("/user")//
//				.failureUrl("/login?message=error")//
//				.usernameParameter("username")//
//				.passwordParameter("password")
//				// Cấu hình cho Logout Page.
//				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout");
	}

}