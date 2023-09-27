package dream.security.config;

import dream.security.jwt.filter.JwtAuthenticationProcessingFilter;
import dream.security.jwt.service.JwtService;
import dream.security.oauth2.handler.SocialLoginFailureHandler;
import dream.security.oauth2.handler.SocialLoginSuccessHandler;
import dream.security.oauth2.service.SocialLoginService;
import dream.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SocialLoginService socialLoginService;
    @Autowired
    private SocialLoginSuccessHandler socialLoginSuccessHandler;
    @Autowired
    private SocialLoginFailureHandler socialLoginFailureHandler;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .cors(c -> {
                            CorsConfigurationSource source = request -> {
                                // Cors 허용 패턴
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(
                                        List.of("*")
                                );
                                config.setAllowedMethods(
                                        List.of("*")
                                );
                                config.setAllowedHeaders(
                                        List.of("*")
                                );
                                return config;
                            };
                            c.configurationSource(source);
                        }
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/***").permitAll()
                .antMatchers("/ws-stomp/**", "/login/**", "/api/oauth2/**").permitAll()
                .antMatchers("/api/s3/**").permitAll()
                .antMatchers("/api/mongo/**").permitAll()
                .antMatchers("/api/user/signup/extra-info").hasRole("GUEST")
                .antMatchers("/api/**").hasRole("USER")
                .and()


                .oauth2Login()
//                .loginProcessingUrl("/api/login/oauth2/code/kakao")
                .successHandler(socialLoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
//                .failureHandler(socialLoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
                .userInfoEndpoint().userService(socialLoginService); // customUserService 설정

//        http.addFilterBefore(new JwtAuthenticationProcessingFilter(jwtService, userRepository),  UsernamePasswordAuthenticationFilter.class);


    }


    @Override //swagger 예외 처리
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }
}
