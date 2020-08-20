package com.maiia.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiia.test.jsondb.Post;
import io.jsondb.JsonDBTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostsController {
    private final Logger logger = LoggerFactory.getLogger(PostsController.class);

    public JsonDBTemplate jsonDBTemplate;

    @CrossOrigin(origins={"http://localhost:4200", "http://localhost:3000"})
    @RequestMapping("/posts")
    public List<Post> posts() {
        initJsonDB();
        if (jsonDBTemplate.findAll(Post.class).isEmpty()) {
            insertPosts("https://jsonplaceholder.typicode.com/posts");
        }
        Comparator<Post> comparator = new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return (post1.getTitle().compareTo(post2.getTitle()));
            }
        };
        List<Post> sortedPosts = jsonDBTemplate.findAll(Post.class, comparator);
        List<Post> fiftySortedPosts = sortedPosts.stream().limit(50).collect(Collectors.toList());
        return fiftySortedPosts;
    }

    public String insertPosts(String httpUrl){
        Post[] posts = {};
        try{
            URL url = new URL(httpUrl);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            ObjectMapper mapper = new ObjectMapper();
            posts = mapper.readValue(reader, Post[].class);
            for (Post post : posts){
                jsonDBTemplate.insert(post);
            }
        }catch(Exception ex){
            logger.error("Error occured while reading url " + httpUrl, ex);
        }
        return posts.toString();
    }

    public void initJsonDB() {
        String dbFilesLocation = ".";
        String baseScanPackage = "com.maiia.test.jsondb";

        jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
        if (!jsonDBTemplate.collectionExists(Post.class)) {
            jsonDBTemplate.createCollection(Post.class);
        };
    }
}


