package com.company.project.configurer.security;

import com.company.project.configurer.filter.AuthenticationTokenFilter;
import com.company.project.configurer.security.handler.EntryPointUnauthorizedHandler;
import com.company.project.configurer.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @version V1.0.0
 * @Description Spring Security 的配置类
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 0:02
 */
@Configuration      // 声明为配置类
@EnableWebSecurity      // 启用 Spring Security web 安全的功能
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    /**
     * 注册 401 处理器
     */
    private final EntryPointUnauthorizedHandler unauthorizedHandler;

    /**
     * 注册 403 处理器
     */
    private final MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public ApplicationSecurity(EntryPointUnauthorizedHandler unauthorizedHandler, MyAccessDeniedHandler accessDeniedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return AuthenticationTokenFilter
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
                // 需携带有效 token
//                .antMatchers("/auth").authenticated()
                // 需拥有 admin 这个权限
//                .antMatchers("/admin").hasAuthority("admin")
                // 需拥有 ADMIN 这个身份
//                .antMatchers("/ADMIN").hasRole("ADMIN")
                // 允许所有请求通过
//                .anyRequest().permitAll()
                .and()
                // 配置被拦截时的处理
                .exceptionHandling()
                // 添加 token 无效或者没有携带 token 时的处理
                .authenticationEntryPoint(this.unauthorizedHandler)
                //添加无权限时的处理
                .accessDeniedHandler(this.accessDeniedHandler)
                .and()
                .csrf()
                // 禁用 Spring Security 自带的跨域处理
                .disable()
                // 定制我们自己的 session 策略
                .sessionManagement()
                // 调整为让 Spring Security 不创建和使用 session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        /*
         *  本次 json web token 权限控制的核心配置部分
         *  在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
         *  通过添加过滤器将 token 解析，将用户所有的权限写入本次会话
         */
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
