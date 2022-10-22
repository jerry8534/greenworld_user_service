package com.luckytree.greenworld_user_service.config

import com.luckytree.greenworld_user_service.util.JwtAccessDeniedHandler
import com.luckytree.greenworld_user_service.util.JwtAuthenticationEntryPoint
import com.luckytree.greenworld_user_service.util.JwtSecurityConfig
import com.luckytree.greenworld_user_service.util.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val corsFilter: CorsFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers(
                "/h2-console/**", "/favicon.ico", "/error"
            )
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf().disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/api/hello").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/signup").permitAll()
            .anyRequest().authenticated()

            .and()
            .apply(JwtSecurityConfig(tokenProvider))
        return httpSecurity.build()
    }
}