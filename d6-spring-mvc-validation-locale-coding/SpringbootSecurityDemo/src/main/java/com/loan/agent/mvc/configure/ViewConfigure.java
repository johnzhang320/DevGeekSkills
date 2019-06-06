package com.loan.agent.mvc.configure;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class ViewConfigure extends WebMvcConfigurationSupport {    //WebMvcConfigurationSupport extends WebMvcConfigurer
	
	// Very important here is that we can register view controllers that create a direct mapping between the URL and the view name using 
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/");  
		// registry.addViewController("/admin").setViewName("forward:/admin/index.html");   
	}
	
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/js/form/","/ui/static/js/libs/","/ui/static/js/utils/","/ui/static/js/ckeditor/","/ui/static/script");
        registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/css/","/ui/static/css/common");
    }

	// create InternalResourceViewResolver to register to ViewResolverRegistry which contains List<ViewResolver> viewResolvers
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
		
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
     }

}
