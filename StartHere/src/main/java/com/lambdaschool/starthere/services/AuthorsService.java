package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Authors;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AuthorsService
{
    List<Authors> findAll(Pageable pageable);

    Authors findById(long id);

    Authors save (Authors authors);

}
