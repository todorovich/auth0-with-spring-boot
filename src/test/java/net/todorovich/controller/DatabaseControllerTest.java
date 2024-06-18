package net.todorovich.controller;

import lombok.extern.slf4j.Slf4j;

import net.todorovich.server.ServerApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.test.context.ActiveProfiles;

import java.text.MessageFormat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= ServerApplication.class)
class DatabaseControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("Autowire DatabaseController")
	void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

	@Test @DisplayName("List Database Names")
	void listDatabaseName()
	{
		var response = restTemplate.postForEntity(
			MessageFormat.format("http://www.todorovich.local:{0}/databases/", String.valueOf(port)),
			null,
			String.class
		);

		log.atInfo().log("Available Databases" + response.getBody());
	}
}