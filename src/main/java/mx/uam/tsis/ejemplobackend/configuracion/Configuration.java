package mx.uam.tsis.ejemplobackend.configuracion;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@org.springframework.context.annotation.Configuration
@EnableSwagger2
public class Configuration {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
	return new ApiInfo("Api rest cine", "Api CRUD del curso de TSIS", "API TSIS", "Terms of service",
		new Contact("Gabriel López", "https://ticsolucionesweb.000webhostapp.com/",
			"gabriellopeztic@gmail.com"),
		"License of API", "API license URL", Collections.emptyList());
    }

}
