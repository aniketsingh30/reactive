package com.example.juint.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.juint.bean.Item;
import com.example.juint.dao.IItemCommandDao;
import com.example.juint.dao.IItemQueryDao;
import com.example.juint.document.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ItemServiceCommandWrapper implements IItemServiceCommandWrapper {

	@Autowired
	IItemCommandDao itemCommandDao;

	@Autowired
	IItemQueryDao itemQueryDao;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public Mono<Item> save(Item item) throws Exception {
		ItemDTO itemDto = objectMapper.convertValue(item, ItemDTO.class);
		return itemCommandDao.save(itemDto).map(currentItemDto -> {
			Item currentItem = objectMapper.convertValue(currentItemDto, Item.class);
			return currentItem;
		});
	}

	@Override
	public Mono<Item> update(Item item, String id) throws Exception, RuntimeException {
		return itemQueryDao.findById(id).flatMap(currentItemDTO -> {
			currentItemDTO.setDescription(item.getDescription());
			currentItemDTO.setExpiryDate(item.getExpiryDate());
			currentItemDTO.setManufactureDate(item.getManufactureDate());
			currentItemDTO.setPrice(item.getPrice());
			try {
				return itemCommandDao.save(currentItemDTO).map(currentItemDTO1 -> {
					return objectMapper.convertValue(currentItemDTO1, Item.class);
				});
			} catch (Exception e) {
				log.error("Error while saving an Item>>", e.getMessage());
				throw new RuntimeException(e);
			}
		});

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return itemCommandDao.deleteById(id);
	}

}
