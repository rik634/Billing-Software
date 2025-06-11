package com.project.billingSoftware.service;

import com.project.billingSoftware.io.CategoryRequest;
import com.project.billingSoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request, MultipartFile file);
    List<CategoryResponse> read();
    void delete(String categoryId);

}
