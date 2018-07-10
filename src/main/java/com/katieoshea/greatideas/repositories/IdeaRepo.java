package com.katieoshea.greatideas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.katieoshea.greatideas.models.Idea;

@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long> {
    Idea findByContent(String content);

}
