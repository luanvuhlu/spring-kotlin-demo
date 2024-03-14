package com.example.springkotlindemo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .addFilterBefore(customBasicAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
            .logout()
            .permitAll()
    }

    @Bean
    fun customBasicAuthenticationFilter(authenticationManager: AuthenticationManager): OncePerRequestFilter {
        return object : BasicAuthenticationFilter(authenticationManager) {
            override fun getAuthenticationManager(): AuthenticationManager {
                return authenticationManagerBean()
            }

            override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
                if (SecurityContextHolder.getContext().authentication == null) {
                    if (request.getHeader("Authorization") == null) {
                        response.setHeader("WWW-Authenticate", "Basic")
                        response.status = HttpServletResponse.SC_UNAUTHORIZED
                        return
                    } else {
                        val authRequest = BasicAuthenticationConverter().convert(request)
                        val authResult = this.authenticationManager.authenticate(authRequest)
                        if (authResult != null) {
                            chain.doFilter(request, response)
                        }
                    }
                } else {
                    chain.doFilter(request, response)
                }
            }
        }
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}