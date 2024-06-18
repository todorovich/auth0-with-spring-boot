package net.todorovich.components;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoIterable;
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

    public class User
    {
        public net.todorovich.model.User get()
        {
            return net.todorovich.model.User.builder()
                    .id("micho")
                    .build();
        }
    }

    public final User user = new User();

    public Set<String> getDatabaseNames()
    {
        MongoIterable<String> strings = mongoClient.listDatabaseNames();

        var cursor = strings.cursor();

        var set = new TreeSet<String>();
        while (cursor.hasNext())
        {
            set.add(cursor.next());
        }

        return set;
    }
}
