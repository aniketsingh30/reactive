package com.learn.reactive.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.repository.IItemCommandRepository;

import reactor.core.publisher.Mono;

@Service
public class ItemCommandDao implements IItemCommandDao {

	@Autowired
	IItemCommandRepository itemCommandRepository;

	@Override
	public Mono<ItemDTO> save(ItemDTO item) throws Exception {
		return itemCommandRepository.save(item);
	}

	@Override
	public Mono<ItemDTO> update(ItemDTO item) throws Exception {
		return itemCommandRepository.findById(item.getId()).flatMap(currentItem -> {
			currentItem.setDescription(item.getDescription());
			currentItem.setPrice(item.getPrice());
			currentItem.setManufactureDate(item.getManufactureDate());
			currentItem.setExpiryDate(item.getExpiryDate());
			return itemCommandRepository.save(currentItem);
		});

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return itemCommandRepository.deleteById(id);
	}

}
