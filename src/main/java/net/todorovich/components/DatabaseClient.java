package net.todorovich.components;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoIterable;
import com.mongodb.lang.NonNull;
import net.todorovich.server.ServerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Component
public class DatabaseClient
{
    final ServerApplication server;
    final MongoClient mongoClient;

    @Autowired
    public DatabaseClient(
       ServerApplication server, MongoClient mongoClient
    )
    {
        this.server = server;
        this.mongoClient =mongoClient;
    }

    public static class User
    {
        public static net.todorovich.model.User get(@NonNull String id)
        {
            return net.todorovich.model.User.builder()
                    .id(id)
                    .build();
        }
    }

    public Set<String> getDatabaseNames()
    {
        MongoIterable<String> strings = mongoClient.listDatabaseNames();

        TreeSet<String>  set = new TreeSet<>();
        try (var cursor = strings.cursor())
        {
            while (cursor.hasNext())
            {
                set.add(cursor.next());
            }
        }

        return set;
    }
}
