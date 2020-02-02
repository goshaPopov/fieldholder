package ru.popov.sixthgrain.fieldholder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.popov.sixthgrain.fieldholder.dto.response.FieldResponse;
import ru.popov.sixthgrain.fieldholder.model.Field;
import ru.popov.sixthgrain.fieldholder.services.FieldService;


@Controller
public class ThymeleafController {

    private final FieldService fieldService;

    @Autowired
    public ThymeleafController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, Pageable pageable) {
        Page<Field> fieldPage = fieldService.findAll(pageable);
        model.addAttribute("fields", fieldPage.map(FieldResponse::new));
        return "index";
    }

}
