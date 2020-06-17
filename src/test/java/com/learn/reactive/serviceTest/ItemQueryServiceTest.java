package com.learn.reactive.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.reactive.bean.Item;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.service.ItemQueryService;
import com.learn.reactive.service.OrderResponseService;
import com.learn.reactive.wrapper.IItemQueryWrapper;
import com.learn.reactive.wrapper.ItemQueryWrapper;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ItemQueryService.class)
@Import(ItemQueryWrapper.class)
public class ItemQueryServiceTest {
	@MockBean
	IItemQueryWrapper itemQueryWrapper;
	@InjectMocks
	ItemQueryService itemQueryService;
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

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void tesHello() {
		Mono<Item> itemMono = Mono.just(new Item("ID1", "Samsung TV", 399.99, subItemSAMList1, new Date(), new Date()));
		try {
			Mono<ResponseEntity<OrderResponseService>> responseMono =null;
			when(itemQueryWrapper.getItemById(anyString())).thenReturn(itemMono);
			responseMono =  itemQueryService.getItemById("ID1");
			responseMono.subscribe(response->{
				OrderResponseService orderResponseService  = response.getBody();
				assertEquals("200", orderResponseService.getStatusCode());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
