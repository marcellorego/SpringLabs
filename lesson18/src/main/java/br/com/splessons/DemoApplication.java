package br.com.splessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

// exclude = org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class
@SpringBootApplication
public class DemoApplication {

//	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Autowired
//	private CustomerRepository customerRepository;

//	private Customer createCustomer(final String name) {
//		Customer c = new Customer();
//		c.setName(name);
//		return c;
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		//  add something to the primary database
//		this.customerRepository.save(createCustomer("VISUAL"));
//
//		logger.debug("data from customer: {}", this.customerRepository.findAll());
//
//		TenantContext.setTenant("visualnuts");
//
//		//  add something to the primary database
//		this.customerRepository.save(createCustomer("TEST"));
//
//		logger.debug("data from customer: {}", this.customerRepository.findAll());
//	}

	@Bean
	public FilterRegistrationBean tenantFilter() {

		FilterRegistrationBean registrationBean
				= new FilterRegistrationBean();

		registrationBean.setFilter(new TenantFilter());
		registrationBean.addUrlPatterns("/person/*");

		return registrationBean;
	}

}
