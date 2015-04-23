package service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger log = LoggerFactory.getLogger(this.getClass());
    //private static final String template = "Hello, %s!";
    private static final String externalURL = "http://taotao.byethost12.com/test.json";
    
    @RequestMapping(value = "/city", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		return "HELLO REST API";
	}
    
	//@RequestMapping(value = "/city/{cityName}", method = RequestMethod.GET)
	@RequestMapping(value = "/city", params={"name"}, method = RequestMethod.GET)
	//public String displayATMList(@PathVariable String cityName) {
	public String displayATMList(@RequestParam(value = "name") String name) {
		
		log.info("start the rest service");
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
	    
	    if (map.containsKey(name)) {	    	
	    	return StringUtils.join(map.get(name), "<br/>");
	    } else {
	    	return "City Not Found";
	    }
		
	}
	
	
}