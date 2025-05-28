package br.com.aula.api;

import org.springframework.data.repository.CrudRepository;

public interface DisciplRepository extends CrudRepository<Discipl, Integer> {
    // This interface will automatically provide CRUD operations for the Discipl entity.
    // Additional query methods can be defined here if needed.
    

}
