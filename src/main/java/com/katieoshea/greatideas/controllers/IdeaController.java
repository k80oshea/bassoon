package com.katieoshea.greatideas.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.greatideas.models.Idea;
import com.katieoshea.greatideas.models.User;
import com.katieoshea.greatideas.services.IdeaServ;
import com.katieoshea.greatideas.services.UserServ;

@Controller
@RequestMapping("/ideas")
public class IdeaController {
    private UserServ uServ;
    private IdeaServ iServ;
    public IdeaController(UserServ uServ, IdeaServ iServ) {
        this.uServ = uServ;
        this.iServ = iServ;
    }
	
    @RequestMapping("")
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("user", uServ.findByEmail(email));
        model.addAttribute("ideas", iServ.allIdeas());
        return "ideas";
    } 

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
    	Idea idea = iServ.findById(id);
        model.addAttribute("idea", idea);
        String email = principal.getName();
        model.addAttribute("user", uServ.findByEmail(email));
        model.addAttribute("likers", idea.getIdeaLikers());
        return "show";
    }

    @RequestMapping("/new")
    public String newIdea(Model model, Principal principal, @Valid @ModelAttribute("idea") Idea idea) {
    	return "new";
    } 
    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, Principal principal) {
    	if(result.hasErrors()) {
            return "new";
    	}
        else {
            String email = principal.getName();
            User user = uServ.findByEmail(email);
            idea.setThinker(user);
            iServ.create(idea);
            return "redirect:/ideas";
        }
    }
    
    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, Principal principal, @Valid @ModelAttribute("empty") Idea empty) {
    	Idea idea = iServ.findById(id);
        model.addAttribute("idea", idea);
        String email = principal.getName();
        User user = uServ.findByEmail(email);
    	if(idea.getThinker() != user) {
    		return "redirect:/ideas";
    	}
        return "edit";
    }
    @PostMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("empty") Idea empty, BindingResult result) {
    	Idea idea = iServ.findById(id);
    	model.addAttribute("idea", idea);
    	if(result.hasErrors()) {
            return "edit";
        }
        else {
        	empty.setThinker(idea.getThinker());
        	empty.setIdeaLikers(idea.getIdeaLikers());
            iServ.update(empty);
            return "redirect:/ideas/{id}";
        }
    }

    @RequestMapping("/{id}/like")
    public String like(@PathVariable("id") Long id, Principal principal) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Idea idea = iServ.findById(id);
    	List<Idea> likes = user.getLikedIdeas();
        likes.add(idea);
        user.setLikedIdeas(likes);
        uServ.update(user);
    	return "redirect:/ideas";
    }
    @RequestMapping("/{id}/unlike")
    public String unlike(@PathVariable("id") Long id, Principal principal) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Idea idea = iServ.findById(id);
    	List<Idea> likes = user.getLikedIdeas();
        likes.remove(idea);
        user.setLikedIdeas(likes);
        uServ.update(user);
    	return "redirect:/ideas";
    }	
    
    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	iServ.delete(id);
    	return "redirect:/ideas";
    }
    
    @RequestMapping("/low")
    public String sortLow(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("user", uServ.findByEmail(email));
        ArrayList<Idea> ideas = (ArrayList<Idea>) iServ.allIdeas();
        Idea temp;
        for(int i=0; i<ideas.size(); i++) {
            for(int x=1; x<(ideas.size()-i); x++) {
                if(ideas.get(x-1).getIdeaLikers().size() >= ideas.get(x).getIdeaLikers().size() ) {
                    temp = ideas.get(x-1);
                    ideas.set(x-1, ideas.get(x));
                    ideas.set(x, temp);
                }
            }
        }
        model.addAttribute("ideas", ideas);
    	return "ideas";
    }
    @RequestMapping("/high")
    public String sortHigh(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("user", uServ.findByEmail(email));
        ArrayList<Idea> ideas = (ArrayList<Idea>) iServ.allIdeas();
        Idea temp;
        for(int i=0; i<ideas.size(); i++) {
            for(int x=1; x<(ideas.size()-i); x++) {
                if(ideas.get(x-1).getIdeaLikers().size() < ideas.get(x).getIdeaLikers().size() ) {
                    temp = ideas.get(x-1);
                    ideas.set(x-1, ideas.get(x));
                    ideas.set(x, temp);
                }
            }
        }
        model.addAttribute("ideas", ideas);
    	return "ideas";
    }

}