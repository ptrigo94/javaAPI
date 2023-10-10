package com.example.newsapiconsumer.service;

import com.example.newsapiconsumer.api.model.Article;
import com.example.newsapiconsumer.api.utils.NewsApiConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private List<Article.ArticleInfo> articleList = new ArrayList<>();
    @Cacheable
    public List<Article.ArticleInfo> getArticles (){
        List < Article.ArticleInfo> allArticles= new ArrayList<>();
        String jsonString = NewsApiConsumer.consumeAPI();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Article article = objectMapper.readValue(jsonString,Article.class);
            System.out.println(article);
            int totalArticles = article.getTotalArticles();

            List < Article.ArticleInfo> articles = article.getArticles();
            allArticles.addAll(articles);


        }catch (Exception e){
            e.printStackTrace();
        }
        return allArticles;

    }
    public ArticleService(){
    }
    @Cacheable
    public Optional<Article.ArticleInfo> getArticle(String title) {

        List < Article.ArticleInfo> allArticles = getArticles();
        Optional optional = Optional.empty();
        for(Article.ArticleInfo article : allArticles){
            if(article.getTitle().toLowerCase().contains(title.toLowerCase())){
                optional = Optional.of(article);
                return optional;
            }
        }
        return optional;
    }

    @Cacheable
    public List<Article.ArticleInfo> getLastArticles(int count) {
        List<Article.ArticleInfo> allArticles = getArticles();

        if (count > 0) {
            count = Math.min(count, allArticles.size());

            int startIndex = allArticles.size() - count;
            int endIndex = allArticles.size();
            return allArticles.subList(startIndex, endIndex);
        } else {
            return Collections.emptyList();
        }
    }
    @Cacheable
    public List <Article.ArticleInfo> getFirstArticles (int count) {

        List<Article.ArticleInfo> allArticles = getArticles();
        if (count > 0 && count <= allArticles.size()) {
            return allArticles.subList(0, count);
        } else {
            return Collections.emptyList(); //
        }
    }
    @Cacheable
    public List<Article.ArticleInfo> searchArticlesByKeyWord(String keyword) {

        List<Article.ArticleInfo> allArticles = getArticles();
        List<Article.ArticleInfo> filteredArticles = new ArrayList<>();
        for (Article.ArticleInfo article : allArticles) {
            if (article.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    article.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                    article.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                filteredArticles.add(article);
            }
        }

        return filteredArticles;
    }
}
