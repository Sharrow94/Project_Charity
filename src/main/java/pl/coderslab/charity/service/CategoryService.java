package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Category;

import java.util.List;

public interface CategoryService {

    void add(Category category);
    void delete(Long id);
    List<Category> getAll();
    Category getById(Long id);

}
