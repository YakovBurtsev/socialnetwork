package ru.yakovburtsev.socialnetwork.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = SearchingController.REST_URL)
public class SearchingController extends AbstractUserController {
    static final String REST_URL = "/users";

    @GetMapping
    public String getUsersView(ModelMap modelMap) {
        return "users";
    }

//    @GetMapping(value = "/find")
//    public String search(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "surname", required = false) String surname,
//            ModelMap modelMap
//    ) {
//        List<UserInfo> infoList = super.findByNameAndSurname(name, surname);
//        Iterator<UserInfo> iterator = infoList.iterator();
//        while(iterator.hasNext()) {
//            UserInfo userInfo = iterator.next();
//            if (userInfo.getId().longValue() == AuthorizedUser.id().longValue()) {
//                iterator.remove();
//            }
//        }
//        modelMap.addAttribute("result", infoList);
//        return "users";
//    }
}
