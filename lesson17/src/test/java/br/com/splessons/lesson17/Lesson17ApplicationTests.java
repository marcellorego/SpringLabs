package br.com.splessons.lesson17;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Lesson17Application.class)
public class Lesson17ApplicationTests {

	@Autowired
	private JsonProperties jsonProperties;

	@Test
	public void contextLoads() {
	}

	@Test
	public void whenPropertiesLoadedViaJsonPropertySource_thenLoadFlatValues() {
		Assert.assertEquals("mailer@mail.com", jsonProperties.getHost());
		Assert.assertEquals(9090, jsonProperties.getPort());
		Assert.assertTrue(jsonProperties.isResend());
		Assert.assertThat(
				jsonProperties.getTopics(),
				Matchers.is(Arrays.asList("spring", "boot")));
		Assert.assertEquals("sender", jsonProperties.getSender().get("name"));
		Assert.assertEquals("street", jsonProperties.getSender().get("address"));
	}

}
