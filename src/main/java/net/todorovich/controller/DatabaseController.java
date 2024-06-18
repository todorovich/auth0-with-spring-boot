package net.todorovich.controller;

import net.todorovich.components.DatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController @RequestMapping("/databases")
public class DatabaseController
{
	 DatabaseClient databaseClient;

	@Autowired
	public DatabaseController(DatabaseClient databaseClient)
	{
		this.databaseClient = databaseClient;
	}

	@PostMapping(path="/", produces="application/json")
	public Set<String> post()
	{
		return databaseClient.getDatabaseNames();
	}
}
