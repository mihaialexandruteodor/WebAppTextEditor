package com.mihaialexandruteodor.FeatherWriter.controllers;

import com.mihaialexandruteodor.FeatherWriter.model.FWCharacter;
import com.mihaialexandruteodor.FeatherWriter.services.FWCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FWCharacterController {

    @Autowired
    private FWCharacterService fwCharacterService;

    @Autowired
    public FWCharacterController(FWCharacterService fwCharacterService) {
        this.fwCharacterService = fwCharacterService;
    }

    /*public String loadProjectPageData(ModelAndView model) {
        return this.findPaginated(1, "projName", "asc", model);
    }*/

    @GetMapping("/newCharacter")
    public String viewCharacterPage(Model model) {

        //ModelAndView mv = new ModelAndView("character_creation");
        //List<Project> projectList = projectService.getAllProjects();
        //mv.addObject(projectList);
        //loadProjectPageData(mv);
        FWCharacter fwCharacter = new FWCharacter();
        model.addAttribute("fwcharacter",fwCharacter);
        return "character_creation";
    }

    @GetMapping("/fw/page/{pageNo}")
    public String findPaginated(@Valid @PathVariable(value = "pageNo") int pageNo,
                                @Valid @RequestParam("sortField") String sortField,
                                @Valid @RequestParam("sortDir") String sortDir,
                                ModelAndView model) {
        int pageSize = 5;

        Page<FWCharacter> page = fwCharacterService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<FWCharacter> listChapters = page.getContent();

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("listProjects", listChapters);

        return "index";
    }

}
