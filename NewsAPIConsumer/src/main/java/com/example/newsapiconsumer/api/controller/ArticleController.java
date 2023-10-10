package com.example.newsapiconsumer.api.controller;
import com.example.newsapiconsumer.api.model.Article.ArticleInfo;
import com.example.newsapiconsumer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArticleController {


    private ArticleService articleService;

    @Autowired
    public ArticleController ( ArticleService articleService){
        this.articleService = articleService;

    }

    //Obtencion de lista de todos los articulos
    @GetMapping("/articles")
    public List<ArticleInfo> getArticles(){
        return articleService.getArticles();
    }

    /*Obtencion de articulo por titulo: Si la solicitud contiene la palabra dentro del titulo o el titulo completo,
      retorna el articulo
     */
    @GetMapping("/article")
    public ArticleInfo getArticle(@RequestParam String title){
        Optional article = articleService.getArticle(title);
        if (article.isPresent()){
            return (ArticleInfo) article.get();
        }
        return null;
    }
    //Obtencion de lista de los n ultimos articulos
    @GetMapping("/articles/last")
    public List<ArticleInfo> getLastArticles(@RequestParam (name= "count") int count){
        return articleService.getLastArticles(count);
    }

    //Obtencion de lista de los n primeros articulos
    @GetMapping("/articles/first")
    public List<ArticleInfo> getFirstArticles(@RequestParam( name = "count") int count){
        return articleService.getFirstArticles(count);
    }

    //Obtencion de lista de articulos por palabra clave
    @GetMapping("/articles/search")
    public List<ArticleInfo> searchArticlesByKeyWord(@RequestParam (name = "keyword") String keyword){
        return articleService.searchArticlesByKeyWord(keyword);

    }
}
