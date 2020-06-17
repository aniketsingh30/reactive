package com.learn.reactive.wrapperTest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.reactive.bean.Item;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.dao.IItemQueryDao;
import com.learn.reactive.dao.ItemQueryDao;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.service.ItemQueryService;
import com.learn.reactive.service.OrderResponseService;
import com.learn.reactive.wrapper.ItemQueryWrapper;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ItemQueryWrapper.class)
@Import(ItemQueryDao.class)
public class ItemQueryWrapperTest {
	@MockBean
	IItemQueryDao itemQueryDao;
	@InjectMocks
	ItemQueryWrapper itemQueryWrapper;
	
	@MockBean
	ObjectMapper mapper;
	
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


	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void tesHello() {
		ItemDTO itemDto= new ItemDTO("ID1", "Samsung TV", 399.99, subItemSAMList, new Date(), new Date());
		Item item= new Item("ID1", "Samsung TV", 399.99, subItemSAMList1, new Date(), new Date());
		
		Mono<ItemDTO> itemDTOMono = Mono.just(itemDto);
		try {
			Mono<Item> itemMono =null;
			when(itemQueryDao.findById(anyString())).thenReturn(itemDTOMono);
			when(mapper.convertValue(itemDto,Item.class)).thenReturn(item);
			itemMono =  itemQueryWrapper.getItemById("ID1");
			itemMono.subscribe(currentItem->{
				assertEquals("ID1", currentItem.getId());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
