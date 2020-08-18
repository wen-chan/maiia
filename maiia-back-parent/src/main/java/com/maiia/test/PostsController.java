package com.maiia.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiia.test.jsondb.Post;
import io.jsondb.JsonDBTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class PostsController {
    private final Logger logger = LoggerFactory.getLogger(PostsController.class);


    @RequestMapping("/posts")
    public String posts() {
        return readUrl("https://jsonplaceholder.typicode.com/posts");
    }

    public String readUrl(String httpUrl){
        JsonDBTemplate jsonDBTemplate = initJsonDB();
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

    public JsonDBTemplate initJsonDB() {
        String dbFilesLocation = ".";
        String baseScanPackage = "com.maiia.test.jsondb";

        JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
        if (!jsonDBTemplate.collectionExists(Post.class)) {
            jsonDBTemplate.createCollection(Post.class);
        }
        return jsonDBTemplate;
    }
}


