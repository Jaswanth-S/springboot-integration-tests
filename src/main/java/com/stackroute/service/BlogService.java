package com.stackroute.service;

import com.stackroute.domain.Blog;
import java.util.List;

public interface BlogService {
    /**
     * AbstractMethod to save a blog
     */
    public Blog saveBlog(Blog blog);

    /**
     * AbstractMethod to get all blogs
     */
    public List<Blog> getAllBlogs();

    /**
     * AbstractMethod to get blog by id
     */
    public Blog getBlogById(int blogId);

    /**
     * AbstractMethod to delete blog by id
     */
    public Blog deleteBlogById(int blogId);

    /**
     * AbstractMethod to update a blog
     */
    public Blog updateBlog(Blog blog);
}
