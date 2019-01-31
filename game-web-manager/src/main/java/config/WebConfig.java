package config;

import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig
{
	public @Bean JettyEmbeddedServletContainerFactory servletContainer()
	{
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		return factory;
	}
}
