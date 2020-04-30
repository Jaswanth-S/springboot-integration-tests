package com.stackroute.commander.test.service;

import com.stackroute.domain.Blog;
import com.stackroute.repository.BlogRepository;
import com.stackroute.service.BlogServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BlogServiceCacheTest {
    @Mock
    private BlogRepository blogRepository;
    @Autowired
    @InjectMocks
    private BlogServiceImpl blogService;
    private Blog blog1;
    private Blog blog2;
    private Blog blog3;
    private List<Blog> blogList;

    @BeforeEach
    public void setUp() {
        blogList = new ArrayList<>();
        blog1 = new Blog(1, "Blog 1", "John", "Sample Blog 1 for Testing");
        blog2 = new Blog(2, "Blog 2", "Meghna", "Sample Blog 2 for Testing");
        blog3 = new Blog(3, "Blog 3", "Alice", "Sample Blog 3 for Testing");
        blogList.add(blog1);
        blogList.add(blog2);
    }

    @AfterEach
    public void tearDown() {
        blog1 = blog2 = blog3 = null;
        blogList = null;
    }

    @Test
    void givenBlogToSaveThenShouldReturnSavedBlog() {
        when(blogRepository.save(any())).thenReturn(blog1);
        assertEquals(blog1, blogService.saveBlog(blog1));
        verify(blogRepository, times(1)).save(any());
    }

    @Test
    void givenCallToGetAllBlogsThenShouldReturnListOfAllBlogs() {
        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        verify(blogRepository, times(1)).findAll();
    }

    @Test
    void givenBlogIdThenShouldReturnBlogWithThatId() {
        when(blogRepository.findById(anyInt())).thenReturn(Optional.of(blog1));
        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);
        blogService.getBlogById(blog1.getBlogId());
        blogService.getBlogById(blog1.getBlogId());
        verify(blogRepository, times(1)).findById(blog1.getBlogId());

    }

    @Test
    void givenBlogToSaveThenShouldEvictCache() {
        when(blogRepository.save(any())).thenReturn(blog1);
        when(blogRepository.findById(anyInt())).thenReturn(Optional.of(blog1));
        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);
        blogService.getBlogById(blog1.getBlogId());
        blogService.getBlogById(blog1.getBlogId());
        verify(blogRepository, times(1)).findById(blog1.getBlogId());
        blogService.saveBlog(blog3);
        verify(blogRepository, times(1)).findById(blog1.getBlogId());
    }

    @Test
    void givenBlogToDeleteThenShouldEvictCache() {
        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);
        blogService.deleteBlogById(1);
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        verify(blogRepository, times(1)).findAll();
    }

    @Test
    void givenBlogToUpdateThenShouldEvictCache() {
        blogService.saveBlog(blog1);
        blogService.saveBlog(blog2);
        blogService.updateBlog(blog1);
        blog1.setBlogContent("Sample Blog");
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        blogService.getAllBlogs();
        assertEquals("Sample Blog", blog1.getBlogContent());
        verify(blogRepository, times(1)).findAll();
    }


}