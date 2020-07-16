package mx.uam.tsis.ejemplobackend.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder encode;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); 
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/index").permitAll()
		.antMatchers(HttpMethod.GET,"/cartelera").permitAll()
		.antMatchers(HttpMethod.GET,"/compraBoletos").permitAll()
		.antMatchers(HttpMethod.GET,"/peliculasCatalogo").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/crearPelicula").hasRole("ADMIN")	
		.anyRequest().authenticated() // cualquier otra solicitud necesitara ser authenticada
		.and()
		.formLogin().loginPage("/login").permitAll() // definimos la uri de el controlador que sera nuestro login
		.defaultSuccessUrl("/") //definimos a donde nos redireccionara si es exitoso
		.failureUrl("/").permitAll() // en caso de loguear falso redirigimos
		.and()
		.logout().logoutUrl("/logout").permitAll() //controlador para salir de la sesion 
		.logoutSuccessUrl("/").permitAll().and().sessionManagement().maximumSessions(1);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("gabriel@gmail.com").password(encode.encode("gabriel")).roles("ADMIN");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/styles.css");
	}
	
	
	
	
}