//package com.bloggingApp.bloggingApp.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//  @Bean
//  public UserDetailsManager userDetailsManager(DataSource dataSource) {
//    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//    // Use of custom tables with custom fields.
//
//    // define query to retrieve a user by username
//    jdbcUserDetailsManager.setUsersByUsernameQuery(
//            "select userid, password from users where userid=?"
//    );
//
//    return jdbcUserDetailsManager;
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(configurer ->
//                    configurer
//                            .anyRequest().authenticated()
//            )
//            .formLogin(form ->
//                    form
//                            .loginPage("/")
//                            .loginProcessingUrl("/processFormLogin")
//                            .usernameParameter("email")
//                            .permitAll()
//            )
//            .logout(
//                    logout -> logout.permitAll()
//            )
//            .exceptionHandling(configurer ->
//                    configurer.accessDeniedPage("/"));
//
//    return http.build();
//  }
//}
