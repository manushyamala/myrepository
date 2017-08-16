package com.emaratech.client.usermanagement.common;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManagementUtils {
	
	private UserManagementUtils() {}
	
	/**
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string){
		return null==string || string.isEmpty();
	}
	
	/**
	 * @param collection
	 * @return
	 */
	public static boolean isNullOrEmpty(Collection<?> collection){
		return null==collection || collection.isEmpty();
	}
	
	/**
	 * @param collection
	 * @return
	 */
	public static boolean isEmptyAndNotNull(Collection<?> collection){
		return (null!=collection && collection.isEmpty());
	}

	/**
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmptyAndNotNull(Collection<?> collection){
		return (null!=collection && !collection.isEmpty());
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static boolean isNotNullOrNotEmpty(String string){
		return (null != string && !string.isEmpty());
	}
	
	/**
	 * @param iterable
	 * @return
	 */
	public static boolean isNotNull(Iterable<?> iterable){
		return null != iterable;
	}
	
	/**
	 * @param map
	 * @return
	 */
	public static boolean isNotEmptyAndNotNull(Map<?,?> map){
		return (null != map && !map.isEmpty());
	}
	
	/**
	 * @param iterable
	 * @return
	 */
	public static <T> boolean isNotNull(T t){
		return null != t;
	}
	
	
	public static  <T> T generatePOJOFromJSON(Class<T> classType,String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return (T)mapper.readValue(jsonString, classType);
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw e;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}				
	}
	
	public static MultiValueMap<String,String> generateMultivalueMapFromLocale(Locale locale){
		MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<String,String>();
		if(null != locale){
			multiValueMap.set(UserManagementConstants.LOCALE_LANGUAGE, locale.getLanguage());
		}
		return multiValueMap;
	}
	
	
}
