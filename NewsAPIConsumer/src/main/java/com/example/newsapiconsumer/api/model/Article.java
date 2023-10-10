package com.example.newsapiconsumer.api.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
    private int totalArticles;
    private List<ArticleInfo> articles;


    public static class ArticleInfo {
        private String title;
        private String description;
        private String content;
        private String url;
        private String image;
        private String publishedAt;
        private Source source;


      @JsonCreator
      public ArticleInfo(@JsonProperty("title") String title,
                         @JsonProperty("description") String description,
                         @JsonProperty("content") String content,
                         @JsonProperty("url") String url,
                         @JsonProperty("image") String image,
                         @JsonProperty("publishedAt") String publishedAt,
                         @JsonProperty("source") Source source) {
          this.title = title;
          this.description = description;
          this.content = content;
          this.url = url;
          this.image = image;
          this.publishedAt = publishedAt;
          this.source = source;
      }
        public String getTitle(){
            return title;
        }
        public String getDescription(){
            return description;
        }
        public String getContent(){
            return content;
        }
        public String getUrl(){
            return url;
        }
        public String getImage(){
            return image;
        }
        public String getPublishedDate(){
            return publishedAt;
        }

        public static class Source {
            private String name;
            private String sourceUrl;

            @JsonCreator
            public Source(@JsonProperty("name") String name, @JsonProperty("url") String url){
                this.name = name;
                this.sourceUrl = url;
            }
            public String getName(){
                return name;
            }
            public String getSourceUrl(){
                return sourceUrl;
            }
        }
    }

    public int getTotalArticles (){
        return totalArticles;
    }
    public List<ArticleInfo> getArticles(){
        return  articles;
    }





}
