package net.todorovich.controller;

import net.todorovich.components.DatabaseClient;
import net.todorovich.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/users")
public class UserController
{
    @PostMapping(path="/{id}", produces="application/json")
    public User post(@PathVariable("id") String id)
    {
        return DatabaseClient.User.get(id);
    }
}
