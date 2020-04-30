package com.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * define an entity
 */
@Entity
public class Blog {

    /**
     * @Id annotation makes id variable as Primary key
     */
    @Id
    private int blogId;
    private String blogTitle;
    private String authorName;
    private String blogContent;

    /**
     * default constructor
     */
    public Blog() {
    }

    /**
     * parameterized constructor
     */
    public Blog(int blogId, String blogTitle, String authorName, String blogContent) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.authorName = authorName;
        this.blogContent = blogContent;
    }

    /**
     * getters and setters
     */
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", blogContent='" + blogContent + '\'' +
                '}';
    }
}
