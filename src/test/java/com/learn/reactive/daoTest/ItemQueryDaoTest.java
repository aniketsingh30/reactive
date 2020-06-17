package com.learn.reactive.daoTest;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.reactive.ItemDataInitializer;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.dao.ItemQueryDao;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.repository.IItemQueryRepoitory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
//@WebFluxTest(controllers = ItemQueryDao.class)
public class ItemQueryDaoTest {
	@InjectMocks
	ItemQueryDao itemQueryDao;
	
	@MockBean
	IItemQueryRepoitory itemQueryRepoitory;
	
	
	List<SubItem> subItemSAMList1 = Arrays.asList(new SubItem("SAMTV", "SAMTV"), new SubItem("SAMTV1", "SAMTV1"),
			new SubItem("SAMTV2", "SAMTV2"));
	List<SubItemDTO> subItemSAMList = Arrays.asList(new SubItemDTO("SAMTV", "SAMTV"),
			new SubItemDTO("SAMTV1", "SAMTV1"), new SubItemDTO("SAMTV2", "SAMTV2"));


	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testgFindById() {
		ItemDTO itemDto= new ItemDTO("ID1", "Samsung TV", 399.99, subItemSAMList, new Date(), new Date());
		
		Mono<ItemDTO> itemDTOMono = Mono.just(itemDto);
		try {
			Mono<ItemDTO> itemDTOMono1 =null;
			when(itemQueryRepoitory.findById(anyString())).thenReturn(itemDTOMono);
			itemDTOMono1 =  itemQueryDao.findById("ID1");
			itemDTOMono1.subscribe(currentItem->{
				assertEquals("ID1", currentItem.getId());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testGetHello() {
		Mono<String> stringMono = itemQueryDao.getHello();
		StepVerifier.create(stringMono)
		.expectSubscription()
		.expectNext("Hello, Aniket!")
		.verifyComplete();
		
	}

	@Test
	public void testFindAll() {
		 Flux<ItemDTO> itemDTOFlux = Flux.fromIterable(ItemDataInitializer.data());
		when(itemQueryRepoitory.findAll()).thenReturn(itemDTOFlux);
		Flux<ItemDTO> itemDtoFlux= itemQueryDao.findAll();
		StepVerifier.create(itemDtoFlux)
		.expectSubscription()
		.expectNextCount(itemDtoFlux.collectList().block().size())
		.verifyComplete();
		
	}

	

	@Test
	public void testFindItemByDescription() {

		ItemDTO itemDto= new ItemDTO("ID1", "Samsung TV", 399.99, subItemSAMList, new Date(), new Date());
		
		Mono<ItemDTO> itemDTOMono = Mono.just(itemDto);
		try {
			Mono<ItemDTO> itemDTOMono1 =null;
			when(itemQueryRepoitory.findItemByDescription(anyString())).thenReturn(itemDTOMono);
			itemDTOMono1 =  itemQueryDao.findItemByDescription("Samsung TV");
			itemDTOMono1.subscribe(currentItem->{
				assertEquals("Samsung TV", currentItem.getDescription());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

}
