package com.learn.reactive.constant;

public class ItemConstants {

    public static final String ITEM_END_POINT_V1 = "/v1/items";
    public static final String ITEM_STREAM_END_POINT_V1 = "/v1/stream/items";
    public static final String ITEM_FUNCTIONAL_END_POINT_V1 = "/v1/fun/items";
    public static final String ITEM_STREAM_FUNCTIONAL_END_POINT_V1 = "/v1/fun/stream/items";
    
    public static final String ITEM_REQUEST_MAPPING= "/items";
    public static final String ITEM_CREATE= "/create";
    public static final String ITEM_UPDATE= "/update";
    public static final String ITEM_DELETE= "/delete";
	public static final String ALL = "/all";
	public static final String FINDBYID = "/findItem/{id}";
	public static final String FINDBYDESCRIPTION = "/findByDescription/{description}";
	public static final String FINDSUBITEMSBYID = "/findSubItems/{id}";
	
	public static final String SUBITEM_UPDATE= "/saveSubItem";
	public static final String SUBITEM_GET= "/getSubItem";
	
	public static final String SUCCESS= "SUCCESS";
	

}
