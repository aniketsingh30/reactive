package com.learn.reactive.serviceTest;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.reactive.bean.Item;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.dao.ItemCommandDao;
import com.learn.reactive.dao.ItemQueryDao;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.service.ItemCommandService;
import com.learn.reactive.wrapper.ItemCommandWrapper;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@Import(ItemCommandWrapper.class)
public class ItemCommandServiceTest {

	@InjectMocks
	ItemCommandService itemCommandService;

	@MockBean
	ItemCommandWrapper itemCommandWrapper;
	@MockBean
	ObjectMapper mapper;

	List<SubItem> subItemSAMList1 = Arrays.asList(new SubItem("SAMTV", "SAMTV"), new SubItem("SAMTV1", "SAMTV1"),
			new SubItem("SAMTV2", "SAMTV2"));
	List<SubItemDTO> subItemSAMList = Arrays.asList(new SubItemDTO("SAMTV", "SAMTV"),
			new SubItemDTO("SAMTV1", "SAMTV1"), new SubItemDTO("SAMTV2", "SAMTV2"));

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() {
		Item item = new Item("ID5", "Samsung TV", 399.99, subItemSAMList1, new Date(), new Date());

		try {
			when(itemCommandWrapper.save(any(Item.class))).thenReturn(Mono.just(item));

			Mono<ResponseEntity<Item>> itemMono = itemCommandService.save(item);
			StepVerifier.create(itemMono).expectSubscription()
					.expectNextMatches(response -> response.getBody().getPrice().equals(399.99)).verifyComplete();
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() throws Exception {
		ItemDTO itemDto = new ItemDTO("ID4", "Samsung TV", 499.99, subItemSAMList, new Date(), new Date());
		Item item = new Item("ID4", "Samsung TV", 499.99, subItemSAMList1, new Date(), new Date());
		try {
			when(itemCommandWrapper.update(any(Item.class), anyString())).thenReturn(Mono.just(item));
			when(mapper.convertValue(itemDto, Item.class)).thenReturn(item);
			Mono<ResponseEntity<Item>> itemMono = itemCommandService.update(item, item.getId());

			StepVerifier.create(itemMono).expectSubscription()
					.expectNextMatches(response -> response.getBody().getPrice().equals(499.99)).verifyComplete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteById() throws Exception {
		try {
			when(itemCommandWrapper.deleteById(anyString())).thenReturn(Mono.empty());

			Mono<Void> emptyMono = itemCommandService.deleteById("ID5");
			StepVerifier.create(emptyMono).verifyComplete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
