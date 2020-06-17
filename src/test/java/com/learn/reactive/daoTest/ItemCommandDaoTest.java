package com.learn.reactive.daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.reactive.ItemDataInitializer;
import com.learn.reactive.bean.SubItem;
import com.learn.reactive.dao.ItemCommandDao;
import com.learn.reactive.dao.ItemQueryDao;
import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.repository.IItemCommandRepository;
import com.learn.reactive.repository.IItemQueryRepoitory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class ItemCommandDaoTest {
	@InjectMocks
	ItemCommandDao itemCommandDao;

	@MockBean
	IItemCommandRepository itemCommandRepository;

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
		ItemDTO itemDto = new ItemDTO("ID5", "Samsung TV", 399.99, subItemSAMList, new Date(), new Date());
		try {
			when(itemCommandRepository.save(any(ItemDTO.class))).thenReturn(Mono.just(itemDto));

			Mono<ItemDTO> itemDtoMono = itemCommandDao.save(itemDto);
			StepVerifier.create(itemDtoMono).expectSubscription().expectNext(itemDto).verifyComplete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() throws Exception {
		ItemDTO itemDto = new ItemDTO("ID5", "Samsung TV", 499.99, subItemSAMList, new Date(), new Date());
		try {
			when(itemCommandRepository.save(any(ItemDTO.class))).thenReturn(Mono.just(itemDto));

			Mono<ItemDTO> itemDtoMono = itemCommandDao.update(itemDto);
			StepVerifier.create(itemDtoMono)
			.expectSubscription()
			.expectNextMatches(item->item.getPrice().equals(499.99))
			.verifyComplete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteById() throws Exception {
		try {
			when(itemCommandRepository.deleteById(anyString())).thenReturn(Mono.empty());

			Mono<Void> emptyMono = itemCommandDao.deleteById("ID5");
			StepVerifier.create(emptyMono)
			.verifyComplete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
