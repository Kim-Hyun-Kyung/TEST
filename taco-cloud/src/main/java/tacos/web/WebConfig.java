package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//WebMvcConfigurer = spring MVC를 구성하는 메서드를 정의하고 있다. 인터페이스지만 기본적인 구현을 제공함. 필요한 메소드만 선택하여 오버라이딩하면 됨.
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		//홈 컨트롤러를 대신한다.
	}
}
