package com.custom.annotation.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.custom.annotation.define.InitMethod;
import com.custom.annotation.define.JsonElementField;
import com.custom.annotation.define.JsonSerializableType;

@Configuration 
@Component
public class ObjectToJsonConverter {
	
	 public String convertToJson(Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
	}
	 private void checkIfSerializable(Object object) throws JsonSerializationException  {
	    if (Objects.isNull(object)) {
	        throw new JsonSerializationException("The object to serialize is null");
	    }
	         
	    Class<?> clazz = object.getClass();
	    if (!clazz.isAnnotationPresent(JsonSerializableType.class)) {
	        throw new JsonSerializationException("The class "
	          + clazz.getSimpleName() 
	          + " is not annotated with JsonSerializable");
	    }
	} 
	private void initializeObject(Object object) throws Exception {
		    Class<?> clazz = object.getClass();
	    for (Method method : clazz.getDeclaredMethods()) {
	        if (method.isAnnotationPresent(InitMethod.class)) {
	            method.setAccessible(true);
	            method.invoke(object);
	        }
	    }
	}
	private String getJsonString(Object object) throws Exception {  
	    Class<?> clazz = object.getClass();
	    Map<String, String> jsonElementsMap = new HashMap<>();
	    for (Field field : clazz.getDeclaredFields()) {
	        field.setAccessible(true);
	        
	        if (field.isAnnotationPresent(JsonElementField.class)) {
	            jsonElementsMap.put(getKey(field), (String) field.get(object));
	        }
	    }       
	      
	    String jsonString = jsonElementsMap.entrySet()
	        .stream()
	        .map(entry -> "\"" + entry.getKey() + "\":\""
	          + entry.getValue() + "\"")
	        .collect(Collectors.joining(","));
	    return "{" + jsonString + "}";
	}
	
	private String getKey(Field field) {
		String value = field.getAnnotation(JsonElementField.class).key();
		return value.isEmpty()? field.getName(): value;
	
	}
}
