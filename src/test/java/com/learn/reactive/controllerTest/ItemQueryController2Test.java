package com.learn.reactive.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.learn.reactive.controller.ItemQueryController2;
import com.learn.reactive.service.IItemQueryService;
import com.learn.reactive.service.ItemQueryService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ItemQueryController2.class)
@Import(ItemQueryService.class)
public class ItemQueryController2Test {
	@Autowired
	WebTestClient webTestClient;
	@MockBean
	IItemQueryService itemQueryService;

	@Test
	public void tesHello() {

		Mono<String> stringMono = Mono.just("Hello, Aniket!");
		try {
			when(itemQueryService.getHello()).thenReturn(stringMono);

			webTestClient.get().uri("/hello").exchange().expectStatus().isOk().expectBody().consumeWith(m -> {
				// String s=new String(m.getResponseBodyContent(), StandardCharsets.UTF_8);
				System.out.println(new String(m.getResponseBodyContent(), StandardCharsets.UTF_8));
				assertEquals("Hello, Aniket!", new String(m.getResponseBodyContent(), StandardCharsets.UTF_8));
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
