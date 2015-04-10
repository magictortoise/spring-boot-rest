package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import service.Application;
import bean.ResponseWrapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SpringRestApplicationTests {

	@Test
	public void test() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.getForEntity("http://taotao.byethost12.com/test.json", String.class);
		System.out.println(response);
	}
	
	@Test
	public void convert_json_response_to_java_obj() {

	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    ResponseWrapper[] forNow = restTemplate.getForObject("http://taotao.byethost12.com/test.json", ResponseWrapper[].class);
	   
	    System.out.println("Test Address: " +  forNow[0].getAddress().toString());
	}
	

}
