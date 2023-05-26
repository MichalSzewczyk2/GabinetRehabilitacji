package com.clinic.security;

import com.clinic.helpers.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    http.csrf().disable()
      .authorizeHttpRequests((authorize) ->
        authorize.requestMatchers("/styles/**",
            "/images/**",
            "/js/**",
            "/signIn",
            "/main")
          .permitAll()
          .anyRequest().authenticated())
      .formLogin(form -> form
        .loginPage("/logIn")
        .loginProcessingUrl("/logIn")
        .defaultSuccessUrl("/main")
        .permitAll())
      .logout(logout -> logout
        .logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
          .permitAll());

    return http.build();

//    http.authorizeRequests()
//      .anyRequest()
//      .authenticated()
//      .and()
//      .httpBasic();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    return configuration.getAuthenticationManager();
  }

//  @Autowired
//  private MyUserDetailsService userDetailsService;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////    http.csrf()
////      .disable()
////      .authorizeRequests()
////      .requestMatchers("/admin/**")
////      .hasRole("admin")
////      .requestMatchers("/main*")
////      .anonymous()
////      .requestMatchers("/login*")
////      .permitAll()
////      .anyRequest()
////      .authenticated()
////      .and()
////      .formLogin()
////      .loginPage("/logIn")
////      .loginProcessingUrl("/logIn")
////      .defaultSuccessUrl("/main", true)
////      .failureUrl("/login.html?error=true")
////      //.failureHandler(authenticationFailureHandler())
////      .and()
////      .logout()
////      .logoutUrl("/logOut")
////      .deleteCookies("JSESSIONID");
////      //.logoutSuccessHandler(logoutSuccessHandler());
//    http.authenticationProvider(authenticationProvider())
//      .formLogin()
//      .loginPage("/logIn")
//      .loginProcessingUrl("/logIn")
//      .defaultSuccessUrl("/main",true)
//      .failureUrl("/login.html?error=true");
//    return http.build();
//  }
//
//  @Bean
//  public AuthenticationProvider authenticationProvider() {
//    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//    authenticationProvider.setUserDetailsService(userDetailsService);
//    return authenticationProvider;
//  }
}
