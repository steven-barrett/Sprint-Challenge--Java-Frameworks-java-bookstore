package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorsRepository extends PagingAndSortingRepository<Authors, Long>
{
}
