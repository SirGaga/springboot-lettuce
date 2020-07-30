package com.zhangjie.lettuce.service;

import com.zhangjie.lettuce.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ListCacheServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ListCacheServiceImpl.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 初始化文章
     */
    public void initArticle(){
        String key = "article:top5";
        logger.info("从mysql中查询5个文章");
        List<Article> articles = new ArrayList<>();
        for (int i=1;i<6;i++){
            Article article = new Article();
            article.setId(Integer.toString(i));
            article.setAuthor("author"+i);
            article.setClickNum(new Random().nextInt(1000)+1);
            article.setContent("content"+i);
            article.setTitle("title"+i);
            DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            article.setCreateDate(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault())));
            articles.add(article);
        }
        logger.info("数据存入redis数据库中");
        redisTemplate.opsForList().rightPushAll(key,articles);

    }

    /**
     * 获取点击量排名前5的文章
     * @return 返回排名前5的文章
     */
    public List<Article> getTop5(){
        logger.info("从redis中获取前5个文章数据");
        String key = "article:top5";
        List<Object> range = redisTemplate.opsForList().range(key, 0, 4);

        return range.stream().map(object -> {
            List<Article> list = new ArrayList<>();
            if(object instanceof Article)
                list.add((Article) object);
            else
                list.addAll((List<Article>) object);
            return list;
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 添加文章
     */
    public void addArticle(){
        String key="article:top5";
        logger.info("从mysql中读取数据，并添加到文章中去");
        Article article = new Article();
        article.setId("6");
        article.setAuthor("author6");
        article.setClickNum(562);
        article.setContent("content6");
        article.setTitle("title6");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        article.setCreateDate(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault())));
        redisTemplate.opsForList().rightPush(key,article);
    }


    public static void main(String[] args) {
        System.out.println(new Random().nextInt(1000));
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault())));
    }

}
