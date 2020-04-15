package io.bhupedra.spring5recipeapp.repositories;

import io.bhupedra.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
