package com.codeid.eshopper.controller;

import com.codeid.eshopper.entities.Shipper;
import com.codeid.eshopper.service.CategoryService;
import com.codeid.eshopper.service.ShipperService;
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
@RequestMapping("/shipper")
public class ShipperController {

    private final ShipperService shipperService;
    private final CategoryService categoryService;


    public ShipperController(ShipperService shipperService, CategoryService categoryService) {
        this.shipperService = shipperService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String findAllShipper(Model model) {
        model.addAttribute("shippers", shipperService.findAllShipper());
        model.addAttribute("category", categoryService.findAllCategory());

        return "/modules/shippers/shipper.html";
    }

    @GetMapping("/add")
    public String addShipper(Model model) {
        model.addAttribute("shipper", new Shipper());
        model.addAttribute("action", "Add Region");

        return "/modules/shippers/addEdit.html";
    }

    @PostMapping("/add")
    public String postShipper(@Valid Shipper shipper, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "/modules/shippers/addEdit.html";
        }

        shipperService.addShipper(shipper);
        redirectAttrs.addFlashAttribute("message", "Shipper " + shipper.getCompanyName() + " created!");

        return "redirect:/shipper/";
    }

    @GetMapping("edit/{id}")
    public String editShipperById(@PathVariable("id") Long shipperId, Model model) {
        Optional<Shipper> shipper = shipperService.findShipperById(shipperId);

        model.addAttribute("action", "Edit Shipper");
        model.addAttribute("shipper", shipper.get());

        return "modules/shippers/addEdit.html";

    }


    @GetMapping("delete/{id}")
    public String deleteShipper(@PathVariable(name = "id") Long shipperId, RedirectAttributes redirectAttrs) {
        shipperService.deleteShipperById(shipperId);
        redirectAttrs.addFlashAttribute("message", "ShipperId " + shipperId + " has been delete!");

        return "redirect:/shipper/";
    }
}
