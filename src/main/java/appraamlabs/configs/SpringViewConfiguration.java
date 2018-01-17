package appraamlabs.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@PropertySource("classpath:messages.properties")
public class SpringViewConfiguration extends WebMvcConfigurerAdapter{
	 
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/static";
	private static final String VIEW_RESOLVER_SUFFIX = ".html";
	
/*	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	        "classpath:/META-INF/resources/", "classpath:/resources/static/",
	        "classpath:/resources/", "classpath:/static/" };*/

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
/*	    .setCachePeriod(3600)
	      .resourceChain(true)
	      .addResolver(new PathResourceResolver())*/;
	}
	
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

/*	 @Bean
	 public MultipartConfigElement multipartConfigElement(){
		 return new MultipartConfigElement("");
	 }
	 
	 @Bean
	 public MultipartResolver multipartResolver(){
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		 multipartResolver.setMaxUploadSize(5000000);
		 return multipartResolver;
	 }*/
	
		
	 @Bean
	 public ViewResolver getViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        
	        resolver.setViewClass(JstlView.class);
	        resolver.setPrefix(VIEW_RESOLVER_PREFIX);
	        resolver.setSuffix(VIEW_RESOLVER_SUFFIX);
	        return resolver;
	   }
	 
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
	     registry.addViewController("/").setViewName("forward:/resources/static/map.html");
	 }

}
