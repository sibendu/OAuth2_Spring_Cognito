package sd.oauth;

import java.util.Collections;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends ResourceServerConfigurerAdapter {

  private final ResourceServerProperties resource;

  public OAuth2ResourceServerSecurityConfiguration(ResourceServerProperties resource) {
    this.resource = resource;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors();

    http.csrf().disable();

    http.authorizeRequests()
        .antMatchers("/api/public/**")
        .permitAll()
        .antMatchers("/actuator/health")
        .permitAll()
        .antMatchers("/api-docs**")
        .permitAll()
        .antMatchers("/api-docs/**")
        .permitAll()
        .antMatchers("/swagger-ui**", "/swagger-ui-bundle**")
        .permitAll()
        .antMatchers("/swagger-ui/**")
        .permitAll()
        .anyRequest()
        .authenticated(); //.and().cors();
    
    /*
    http
	.antMatchers("/resourceA/**", "/resourceB/**")
	.authorizeRequests()
		.antMatchers("/resourceA/**").hasAuthority("#oauth2.hasScope('resourceA:read')")
		.antMatchers("/resourceB/**").hasAuthority("#oauth2.hasScope('resourceB:read')")
		.anyRequest().authenticated();
	*/	
  }

  @Bean
  public TokenStore jwkTokenStore() {
    return new JwkTokenStore(
        Collections.singletonList(resource.getJwk().getKeySetUri()),
        new CognitoAccessTokenConverter(),
        null);
  }
}
