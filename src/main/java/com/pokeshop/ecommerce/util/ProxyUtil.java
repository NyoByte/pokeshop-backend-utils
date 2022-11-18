package com.pokeshop.ecommerce.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProxyUtil {
	
	private ProxyUtil() {}

    public static <T> List<T> convertDataToList(ApiResponse apiResponse, Class<T> clase) {
        ObjectMapper mapper = new ObjectMapper();
        if (apiResponse.getData() != null) {
        	return mapper.convertValue(apiResponse.getData(), mapper.getTypeFactory().constructCollectionType(List.class, clase));
        }
        return null;
    }

    public static <T> T convertDataToObject(ApiResponse apiResponse, Class<T> clase) {
        ObjectMapper mapper = new ObjectMapper();
        if (apiResponse.getData() != null) {
            return mapper.convertValue(apiResponse.getData(), clase);
        }
        return null;
    }

}