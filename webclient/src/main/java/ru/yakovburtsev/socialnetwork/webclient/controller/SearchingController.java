package ru.yakovburtsev.socialnetwork.webclient.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import java.util.List;

import static ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser.id;

@Controller
@RequestMapping(value = SearchingController.REST_URL)
public class SearchingController extends AbstractUserController {
    static final String REST_URL = "/users";

    @GetMapping
    public String getUsersView(ModelMap modelMap) {
        return "users";
    }

    @GetMapping(value = "/find")
    public String search(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "surname", required = false) String surname,
                         ModelMap modelMap) {
        List<UserInfo> infoList = super.findByNameAndSurname(name, surname);
        infoList.removeIf(userInfo -> userInfo.getId().equals(id()));
        modelMap.addAttribute("result", infoList);
        return "users";
    }
}
