package IHProject.project.Security.Configuration;

import IHProject.project.Security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/users").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/setBalance/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/sendMoneyTPU/{hashedKey}").hasRole("USER")
                .mvcMatchers(HttpMethod.POST,"/newAdmin").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/newAccount").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/balanceAccount/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/balanceCC2/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/transfer/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/newAccount/{id}/checking").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/newAccount/{id}/savings").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/newAccount/{id}/creditcard").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/AccountBalance/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/CreditCardBalance/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.POST,"/CreditCardBalance/{id}").hasRole("USER")
                .mvcMatchers(HttpMethod.POST,"/CreditCardBalance/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/setBalance/{id}/").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT,"/setBalance/{id}/").hasRole("USER")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }

}
