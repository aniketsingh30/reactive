package com.learn.reactive.controllerTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.learn.reactive.bean.Item;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.constant.ItemConstants;
import com.learn.reactive.controller.ItemQueryController;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.repository.IItemCommandRepository;
import com.learn.reactive.service.IItemQueryService;
import com.learn.reactive.service.ItemQueryService;
import com.learn.reactive.service.OrderResponseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ItemQueryController.class)
@Import(ItemQueryService.class)
public class ItemQueryControllerTest {
	@Autowired
	WebTestClient webTestClient;
	@MockBean
	IItemQueryService itemQueryService;
	List<SubItem> subItemSAMList1 = Arrays.asList(new SubItem("SAMTV", "SAMTV"), new SubItem("SAMTV1", "SAMTV1"),
			new SubItem("SAMTV2", "SAMTV2"));
	List<SubItemDTO> subItemSAMList = Arrays.asList(new SubItemDTO("SAMTV", "SAMTV"),
			new SubItemDTO("SAMTV1", "SAMTV1"), new SubItemDTO("SAMTV2", "SAMTV2"));

	List<SubItemDTO> subItemLGList = Arrays.asList(new SubItemDTO("LGTV", "LGTV"), new SubItemDTO("LGTV1", "LGTV1"),
			new SubItemDTO("LGTV2", "LGTV2"));
	List<SubItemDTO> subItemAPPWATCHList = Arrays.asList(new SubItemDTO("APPWATCH", "APPWATCH"),
			new SubItemDTO("APPWATCH1", "APPWATCH1"), new SubItemDTO("APPWATCH2", "APPWATCH"));

	List<SubItemDTO> subItemBeatsList = Arrays.asList(new SubItemDTO("Beats", "Beats"),
			new SubItemDTO("Beats1", "Beats1"), new SubItemDTO("Beats2", "Beats2"));

	public List<ItemDTO> data() {

		return Arrays.asList(new ItemDTO("ID1", "Samsung TV", 399.99, subItemSAMList, new Date(), new Date()),
				new ItemDTO("ID2", "LG TV", 329.99, subItemLGList, new Date(), new Date()),
				new ItemDTO("ID3", "Apple Watch", 349.99, subItemAPPWATCHList, new Date(), new Date()),
				new ItemDTO("ID4", "Beats HeadPhones", 149.99, subItemBeatsList, new Date(), new Date()));

	}

	@Test
	public void tesHello() {

		//REQUEST PARAM URI
		/*webTestClient.get()
		.uri(uriBuilder -> uriBuilder
				.path(ItemConstants.ITEM_REQUEST_MAPPING.concat(ItemConstants.FINDBYID))
				.queryParam("id", "ID1").build())*/
		Item item = new Item("ID1", "Samsung TV", 399.99, subItemSAMList1, new Date(), new Date());
		try {
			OrderResponseService orderResponseService = new OrderResponseService();
			orderResponseService.setResponse(item);
			orderResponseService.setStatusCode("200");
			Mono<ResponseEntity<OrderResponseService>> responseMono = Mono
					.just(ResponseEntity.ok().body(orderResponseService));
			when(itemQueryService.getItemById(anyString())).thenReturn(responseMono);
			webTestClient.get()
			.uri(ItemConstants.ITEM_REQUEST_MAPPING.concat(ItemConstants.FINDBYID),"ID1")
			.exchange().expectStatus().isOk().expectBody().jsonPath("response.id").isNotEmpty()
            .jsonPath("response.id").isEqualTo(item.getId());
           // .jsonPath("data.description").isEqualTo(form.getDescription())
            //.jsonPath("data.status").isEqualTo("A");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
