package com.stackroute.repository;

import com.stackroute.domain.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer> {
}


