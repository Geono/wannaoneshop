package market.wannaone.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // super.configure(web);
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/members/joinform").permitAll()
                .antMatchers(HttpMethod.POST, "/members/join").permitAll()
                .antMatchers("/members/welcome").permitAll()
                .antMatchers("/members/login").permitAll()
                .antMatchers("/members/**").hasRole("USER")
                .antMatchers("/item/list").permitAll()
                .antMatchers("/item/**").hasRole("USER")
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and().headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/**")// post방식으로 값을 전달할 때 csrf를 무시
                .and()
                .formLogin()
                .loginProcessingUrl("/members/login")
                .loginPage("/members/login")
                .usernameParameter("id")
                .passwordParameter("password");
    }
}
