package service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRouteBuilder extends RouteBuilder {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void configure() throws Exception {
		// Access using http://localhost:8080/camel/hello
		from("servlet:///hello").transform().constant("Hello Camel!");

		// Timer trigger run right after startup. No Servlet request required.
		from("timer://foo?fixedRate=true&period=60s").log("Camel is running.");

		from("servlet:///rest").description("start camel url redirect").process(newProcess).to("http://dummyhost");

	}

	Processor newProcess = new Processor() {
		@Override
		public void process(Exchange exchange) throws Exception {

			String name = exchange.getIn().getHeader("name", String.class);
			log.info("Get the city name: " + name);
			// redirect
			exchange.getIn().setHeader(Exchange.HTTP_URI,
					"http://localhost:8080/city");
			// exchange.getIn().setHeader(Exchange.HTTP_PATH, name);
			// Add query parameter such as "?name=xxx"
			exchange.getIn().setHeader(Exchange.HTTP_QUERY, "name=" + name);
		}

	};
}
