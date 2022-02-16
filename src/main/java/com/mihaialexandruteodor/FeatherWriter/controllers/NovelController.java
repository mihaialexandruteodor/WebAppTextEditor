package com.mihaialexandruteodor.FeatherWriter.controllers;

import com.mihaialexandruteodor.FeatherWriter.model.FWCharacter;
import com.mihaialexandruteodor.FeatherWriter.model.Novel;
import com.mihaialexandruteodor.FeatherWriter.services.FWCharacterService;
import com.mihaialexandruteodor.FeatherWriter.services.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NovelController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private FWCharacterService characterService;

    @Autowired
    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping("/projectsPage")
    public ModelAndView projectsPage() {
        return loadProjectsPageData();
    }

    public ModelAndView loadProjectsPageData() {
        return findPaginated(1, "title", "asc");
    }

    @GetMapping("/newProject")
    public ModelAndView newNovel(Model model) {
        ModelAndView mv = new ModelAndView("project_editor_page");
        return setUpProjPage(mv);
    }

    ModelAndView setUpProjPage(ModelAndView mv)
    {
        Novel novel = new Novel();
        mv.addObject("novel",novel);
        List<FWCharacter> characterList = characterService.getAllFWCharacters();
        mv.addObject("characterList", characterList);
        return mv;
    }

    @RequestMapping(value = "/addCharacterToProject/{characterID}/{novelID}")
    public String addTeamToProject(@Valid @PathVariable (value = "characterID") int characterID, @Valid @PathVariable("novelID") int novelID){
        FWCharacter charObj = characterService.getFWCharacterById(characterID);
        Novel novelObj = novelService.getNovelById(novelID);
        novelService.addTeamToProject(novelObj,charObj);
        return "redirect:/showFormForUpdateProj/"+novelID;
    }

    @GetMapping("/projectsPage/page/{pageNo}")
    public ModelAndView findPaginated(@Valid @PathVariable(value = "pageNo") int pageNo,
                                      @Valid @RequestParam("sortField") String sortField,
                                      @Valid @RequestParam("sortDir") String sortDir) {

        ModelAndView model = new ModelAndView("projects_page");
        int pageSize = 12;

        Page<Novel> page = novelService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Novel> novelList = page.getContent();

        model.addObject("currentPage", pageNo);
        model.addObject("totalPages", page.getTotalPages());
        model.addObject("totalItems", page.getTotalElements());

        model.addObject("sortField", sortField);
        model.addObject("sortDir", sortDir);
        model.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addObject("novelList", novelList);

        return model;
    }
}
