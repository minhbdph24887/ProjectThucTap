package com.example.thuctap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("minhbdph24887").password(getPasswordEncoder().encode("Minh9032003")).roles("ADMIN", "USER", "GUEST")
                .and()
                .withUser("hiennttph25063").password(getPasswordEncoder().encode("123")).roles("USER", "GUEST")
                .and()
                .withUser("user01").password(getPasswordEncoder().encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/javascript/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/product", "/product/detail/**").permitAll()
                .antMatchers("/cart/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/product")
                .failureUrl("/login/error")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        String redirectURL = "/product"; // Mặc định chuyển hướng đến /product nếu đăng nhập thành công
                        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                            redirectURL = "/admin"; // Nếu người dùng có vai trò ADMIN thì chuyển hướng đến /admin
                        }
                        response.sendRedirect(redirectURL);
                    }
                })
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .permitAll();

        http.rememberMe()
                .rememberMeParameter("remember");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/product")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // Lưu thông báo vào localStorage hoặc sessionStorage
                        String script = "<script>localStorage.setItem('message', 'Đăng xuất thành công');</script>";
                        response.getWriter().append(script);
                        response.sendRedirect("/product");
                    }
                });
    }
}
