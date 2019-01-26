package org.launchcode.project_workaround.models.data;

import org.launchcode.project_workaround.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {
        }