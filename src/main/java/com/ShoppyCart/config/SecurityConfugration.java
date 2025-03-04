package com.ShoppyCart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ShoppyCart.SecurityService.JwtFilter;
import com.ShoppyCart.SecurityService.MyUserDetailsService;

import jakarta.servlet.Filter;


@Configuration
public class SecurityConfugration {
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {			
		return http
				
				.csrf(customizer-> customizer.disable())
				.authorizeHttpRequests(request-> request
												.requestMatchers(
														"/v1/home/**"
//														"/v1/prod/saveProduct",
//														"/v1/login",
//														"/v1/getAllProducts",
//														"/v1/getKafkaLogs/javaguides", 
//															"/v1/saveEmp",
//															"/v1/ven/saveVendor"
															).authenticated()
												.anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session-> 
							session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//				.addFilter(new UsernamePasswordAuthenticationFilter())
				
				.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
				.cors(cors -> {
	                cors.configurationSource(corsConfigurationSource());
	            })
				.build();
			
	}
	
	
		
		
		
		@Bean 
		public  AuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
			provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//			provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
			provider.setUserDetailsService(myUserDetailsService);
			return provider;
		}
		
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {				
			return authenticationConfiguration.getAuthenticationManager();
			
		}
		
		 @Bean
		    public CorsConfigurationSource corsConfigurationSource() {
		        CorsConfiguration configuration = new CorsConfiguration();
//		        configuration.addAllowedOrigin("https://qqlfc8b1-5500.inc1.devtunnels.ms/"); 
		        configuration.addAllowedOrigin("http://127.0.0.1:5500/"); 
		        
		        configuration.addAllowedMethod("*"); 
		        configuration.addAllowedHeader("*"); 
		        configuration.setAllowCredentials(true);
		        UrlBasedCorsConfigurationSource source = new 
		         UrlBasedCorsConfigurationSource();
		        source.registerCorsConfiguration("/**", configuration);
		        return source;
		    }
}
