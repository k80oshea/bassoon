package com.katieoshea.greatideas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katieoshea.greatideas.models.Idea;
import com.katieoshea.greatideas.repositories.IdeaRepo;

@Service
public class IdeaServ {
    private IdeaRepo iRepo;
    public IdeaServ(IdeaRepo iRepo) {
        this.iRepo = iRepo;
    }

    public void create(Idea idea) {
        iRepo.save(idea);
    }
    public Idea findById(Long id) {
        return iRepo.findOne(id);
    }
    public List<Idea> allIdeas() {
    	return (List<Idea>) iRepo.findAll();
    }
    public void update(Idea idea) {
        iRepo.save(idea);
    }
    public void delete(Long id) {
        iRepo.delete(id);
    }

}
