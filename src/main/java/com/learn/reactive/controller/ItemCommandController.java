package com.learn.reactive.controller;

import static com.learn.reactive.constant.ItemConstants.ITEM_CREATE;
import static com.learn.reactive.constant.ItemConstants.ITEM_DELETE;
import static com.learn.reactive.constant.ItemConstants.ITEM_REQUEST_MAPPING;
import static com.learn.reactive.constant.ItemConstants.ITEM_UPDATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactive.bean.Item;
import com.learn.reactive.service.IItemCommandService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ITEM_REQUEST_MAPPING)
public class ItemCommandController {

	@Autowired
	private IItemCommandService itemCommandService;

	@PostMapping(ITEM_CREATE)
	public Mono<ResponseEntity<Item>> create(@RequestBody Item item) throws Exception {
		return itemCommandService.save(item);

	}

	@PutMapping(ITEM_UPDATE)
	public Mono<ResponseEntity<Item>> update(@RequestBody Item item,@RequestParam String id)  {
		Mono<ResponseEntity<Item>> i=null;
		try {
		i= itemCommandService.update(item,id);
		} catch (Exception  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}

	@DeleteMapping(ITEM_DELETE)
	public Mono<Void> deleteById(@PathVariable String id) throws Exception {
		return itemCommandService.deleteById(id);

	}
}
