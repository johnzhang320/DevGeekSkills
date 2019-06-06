package com.loan.agent.mvc.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
@Configuration
public class TilesConfigure {
	@Bean
	public TilesConfigurer tilesConfigurer() {

		TilesConfigurer tiles = new TilesConfigurer();
		// tiles-spring.xml , tiles-config_2.0.dtd
		tiles.setDefinitions("classpath:tiles-spring.xml" );
		return tiles;

	}
}
