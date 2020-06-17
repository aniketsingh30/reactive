package com.learn.reactive;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.learn.reactive.document.ItemDTO;
import com.learn.reactive.document.SubItemDTO;
import com.learn.reactive.repository.IItemCommandRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Profile("!test")
public class ItemDataInitializer implements CommandLineRunner {

    @Autowired
    IItemCommandRepository itemReactiveRepository;
    

    @Override
    public void run(String... args) throws Exception {

        initalDataSetUp();
    }

   

    public static List<ItemDTO>  data() {

    	List<SubItemDTO> subItemSAMList= Arrays.asList(new SubItemDTO("SAMTV","SAMTV"),
		new SubItemDTO("SAMTV1","SAMTV1"),
		new SubItemDTO("SAMTV2","SAMTV2"));
    	
    	List<SubItemDTO> subItemLGList=    	
    	Arrays.asList(new SubItemDTO("LGTV","LGTV"),
				new SubItemDTO("LGTV1","LGTV1"),
				new SubItemDTO("LGTV2","LGTV2")
				);
    	List<SubItemDTO> subItemAPPWATCHList=    	
    	Arrays.asList(new SubItemDTO("APPWATCH","APPWATCH"),
				new SubItemDTO("APPWATCH1","APPWATCH1"),
				new SubItemDTO("APPWATCH2","APPWATCH"));
		
    	List<SubItemDTO> subItemBeatsList=    	
		Arrays.asList(new SubItemDTO("Beats","Beats"),
				new SubItemDTO("Beats1","Beats1"),
				new SubItemDTO("Beats2","Beats2")
		);
    	
        return Arrays.asList(new ItemDTO("ID1", "Samsung TV", 399.99,subItemSAMList,new Date(),new Date()),
                new ItemDTO("ID2", "LG TV", 329.99,subItemLGList,new Date(),new Date()),
                new ItemDTO("ID3", "Apple Watch", 349.99,subItemAPPWATCHList,new Date(),new Date()),
                new ItemDTO("ID4", "Beats HeadPhones", 149.99,subItemBeatsList,new Date(),new Date()));
        
    }
    


    private void initalDataSetUp() {
    	
    	itemReactiveRepository.deleteAll()
        .thenMany(Flux.fromIterable(data()))
                .flatMap(itemReactiveRepository::save)
                .thenMany(itemReactiveRepository.findAll())
                .subscribe((item -> {
                    System.out.println("Item inserted from CommandLineRunner : " + item);
                }));

    }



}
