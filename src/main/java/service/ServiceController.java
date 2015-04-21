package service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import bean.*;

@RestController
public class ServiceController {

    private static final String template = "Hello, %s!";
    private static final String externalURL = "http://taotao.byethost12.com/test.json";
    
    @RequestMapping(value = "/city", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		return "HELLO REST API";
	}
    
	@RequestMapping(value = "/city/{cityName}", method = RequestMethod.GET)
	public String displayATMList(@PathVariable String cityName, ModelMap model) {
		
		// get resource
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    ResponseWrapper[] ATMList = restTemplate.getForObject(externalURL, ResponseWrapper[].class);
	    
	    // Store city as key and list of address for the city as value in the HashMap
	    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	    for (ResponseWrapper singleObj : ATMList) {
	    	if (singleObj.getType().equals("ING")) {
	    		ATMLocationWrapper address = singleObj.getAddress();
	    		//System.out.println(address.getCity());
	    		if(map.get(address.getCity()) == null) {
	    			List<String> list = new ArrayList<String>();
	    			list.add(address.toString());
	    			map.put(address.getCity(), list);
	    		} else {
	    			List<String> newList = map.get(address.getCity());
	    			newList.add(address.toString());
	    			map.put(address.getCity(), newList);
	    		}
	    		
	    	}
	    }
	    
	    if (map.containsKey(cityName)) {	    	
	    	return StringUtils.join(map.get(cityName), "<br/>");
	    } else {
	    	return "City Not Found";
	    }
		
	}
	
	
}