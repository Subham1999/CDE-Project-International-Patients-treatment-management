package com.cts.portal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
class ServletInizializerTest {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;
	
	@Test
	void test() {
		ServletInitializer servletInitializer = new ServletInitializer();
		when(springApplicationBuilder.sources(PortalMicroserviceApplication.class)).thenReturn(springApplicationBuilder);
		
		verify(springApplicationBuilder.sources(PortalMicroserviceApplication.class));
		servletInitializer.configure(springApplicationBuilder);
	}

}
