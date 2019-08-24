/**
 *
 */
package com.wizer.test.config.security;

import com.wizer.test.config.security.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author uniqueM
 *         <p>
 *         This source file was written by Adindu Stevens as uniqueM. On 2019-02-09 20:15:52 It may be altered or replicated by anyone capable, but the risks that
 *         might be accrued by such actions shall be borne by the same. uniqueM may however, be contacted through chukwudereadindu@gmail.com if there arises a
 *         need for a additional functionally that may not have been included in the original project. If the module package does not include a user manual,
 *         uniqueM may also be contacted to provide one or other technical clarification.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub

        web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**", "/webjars/**", "/vendor");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/signup")
                .permitAll()
                .antMatchers("/", "/user", "/users", "/test")
                .authenticated()
                .antMatchers("/createcheque", "/assign", "/cheques")
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/process-logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsServiceBean());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceBean());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {

        return new JdbcUserDetails();
    }

    @Bean
    public RestAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public RestAuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    public RestLogoutSuccessHandler logoutSuccessHandler(){
        return new RestLogoutSuccessHandler();
    }

    @Bean
    JsonAuthenticationFilter authenticationFilter() throws Exception {
        logger.debug("Authentication filter is processing loggin request    ");
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        filter.setFilterProcessesUrl("/process-login");
        filter.setPostOnly(true);
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");

        return filter;
    }
}
