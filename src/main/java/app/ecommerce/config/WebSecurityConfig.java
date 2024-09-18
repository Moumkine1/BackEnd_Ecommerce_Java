package app.ecommerce.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import app.ecommerce.filter.JwtFilter;
import app.ecommerce.point.AuthEntryPointJwt;
import app.ecommerce.service.CustomAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.List; 


@Configuration
@EnableWebSecurity
public class WebSecurityConfig    {

	@Autowired
	private  UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtFilter jwtFilter;
	
	  @Autowired
	  private   AuthEntryPointJwt unauthorizedHandler;
	  
	    @Autowired
	    private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	 @Bean
	    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    	 http
	    	    .csrf(csrf -> csrf.disable())/*.cors(AbstractHttpConfigurer::disable)
	    	    .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))*/
						/*
						 .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						 .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())*/
						 //.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	     .authorizeHttpRequests(auth -> {
			try {
				auth.requestMatchers("/connexion","/products","/subscribe","/products/{id}","/panier").permitAll()
						 .requestMatchers("/client","/admin").authenticated()
						 .and()
				         .exceptionHandling()
				             .authenticationEntryPoint(authenticationEntryPoint);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    		 );
	      
	    	 
	    
	   http.authenticationProvider(authenticationProvider());  

	   
	   http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	  

	    return http.build();
	  }

	  
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	      
	      authProvider.setPostAuthenticationChecks((userDetails) -> {
	          System.out.println("Post authentication checks passed for: " + userDetails.getUsername());
	      });
	      
	      authProvider.setPreAuthenticationChecks((userDetails) -> {
	          System.out.println("Pre authentication checks passed for: " + userDetails.getUsername());
	      });
	   
	      return authProvider;
	  }
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	    
	  }
	  
	 
	  

	  
	 
	
	}

