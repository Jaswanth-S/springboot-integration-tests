package com.stackroute.commander.test.repository;

import com.stackroute.domain.Blog;
import com.stackroute.repository.BlogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BlogRepositoryTest {

    /**
     * Used AutoWire by property to inject BlogRepository here
     */
    @Autowired
    private BlogRepository blogRepository;
    private Blog blog;

    /**
     * Execute this before each test case
     */
    @BeforeEach
    public void setUp() {
        blog = new Blog(1, "Blog 1", "Alice", "Sample Blog ");
    }


    /**
     * Execute this after every test case
     */
    @AfterEach
    public void tearDown() {
        blogRepository.deleteAll();
        blog = null;
    }

    @Test
    public void givenBlogThenShouldSaveThatBlog() {
        blogRepository.save(blog);
        Blog fetchedBlog = blogRepository.findById(blog.getBlogId()).get();
        assertEquals(1, fetchedBlog.getBlogId());
    }

    @Test
    public void givenBlogThenShouldSaveThatBlogFailure() {
        Blog blog = new Blog(1, "Blog 1", "John", "Sample Blog ");

        assertNotSame(blog, blogRepository.save(blog));
    }

    @Test
    public void givenBlogsThenShouldReturnListOfAllBlogs() {
        Blog blog = new Blog(2, "Blog 2", "John", "Sample2");
        Blog blog1 = new Blog(3, "Blog 2", "Ravi", "Sample3");
        blogRepository.save(blog);
        blogRepository.save(blog1);

        List<Blog> blogList = (List<Blog>) blogRepository.findAll();
        assertEquals("Blog 2", blogList.get(1).getBlogTitle());
    }

    @Test
    public void givenMethodCallToGetAllBlogsThenShouldReturnListOfAllBlogsFailure() {
        Blog blog1 = new Blog(4, "Blog 4", "Devin", "Sample Blog 4");
        Blog blog2 = new Blog(5, "Blog 5", "Marco", "Sample Blog 5");
        blogRepository.save(blog1);
        blogRepository.save(blog2);
        blogRepository.save(blog);

        List<Blog> list = new ArrayList<>();
        list.add(blog1);
        list.add(blog2);

        List<Blog> blogList = (List<Blog>) blogRepository.findAll();
        //assert
        assertNotSame(list, blogList);
    }


    @Test
    public void givenBlogIdThenShouldReturnBlogOfThatId() {
        Blog blog = new Blog(3, "Blog 3", "Ravi", "SampleBlog");
        Blog blog1 = blogRepository.save(blog);
        Optional<Blog> optional = blogRepository.findById(blog1.getBlogId());
        assertEquals(blog1.getBlogId(), optional.get().getBlogId());
        assertEquals(blog1.getBlogTitle(), optional.get().getBlogTitle());
        assertEquals(blog1.getAuthorName(), optional.get().getAuthorName());
        assertEquals(blog1.getBlogContent(), optional.get().getBlogContent());
    }

    @Test
    public void givenBlogIdThenShouldReturnBlogOfThatIdFailure() {
        Blog blog1 = new Blog(1, "Blog 2", "Alice", "Sample Blog");
        blogRepository.save(blog1);
        Blog actualResult = blogRepository.findById(blog.getBlogId()).get();
        assertNotSame(blog, actualResult);
    }

    @Test
    public void givenBlogIdTODeleteThenShouldDeleteTheBlog() {
        Blog blog = new Blog(4, "Demo4", "Imneet", "Sample4");
        blogRepository.save(blog);
        blogRepository.deleteById(blog.getBlogId());
        Optional optional = blogRepository.findById(blog.getBlogId());
        assertEquals(Optional.empty(), optional);
    }

}