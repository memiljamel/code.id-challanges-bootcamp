package com.codeid.eshopper.controller;

import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.service.CategoryService;
import com.codeid.eshopper.service.DepartmentService;
import com.codeid.eshopper.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;
    private final DepartmentService departmentService;
    private final CategoryService categoryService;


    public RegionController(RegionService regionService, DepartmentService departmentService, CategoryService categoryService) {
        this.regionService = regionService;
        this.departmentService = departmentService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String findAllRegion(Model model) {
        model.addAttribute("regions", regionService.findAllCategory());
        model.addAttribute("departments", departmentService.findAllDepartment());
        model.addAttribute("category", categoryService.findAllCategory());

        return "/modules/regions/region.html";
    }

    @GetMapping("/add")
    public String addRegion(Model model) {
        model.addAttribute("region", new Region());
        model.addAttribute("action", "Add Region");

        return "/modules/regions/addEdit.html";
    }

    @PostMapping("/add")
    public String postRegion(@Valid Region region, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "/modules/regions/addEdit.html";
        }

        regionService.addRegion(region);
        redirectAttrs.addFlashAttribute("message", "Region " + region.getRegionName() + " created!");

        return "redirect:/regions/";
    }

    @GetMapping("edit/{id}")
    public String editRegionById(@PathVariable("id") long regionId, Model model) {
        Optional<Region> region = regionService.findRegionById(regionId);

        model.addAttribute("action", "Edit Region");
        model.addAttribute("region", region.get());

        return "modules/regions/addEdit.html";
    }


    @GetMapping("delete/{id}")
    public String deleteRegion(@PathVariable(name = "id") Long regionId, RedirectAttributes redirectAttrs) {
        regionService.deleteRegionById(regionId);
        redirectAttrs.addFlashAttribute("message", "RegionId " + regionId + " has been delete!");

        return "redirect:/regions/";
    }
}
