package com.mc.controller;

import com.mc.dto.DreamResult;
import com.mc.entity.WebBook;
import com.mc.service.WebBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by machao on  2018/4/22 1:08
 */
@Controller
public class WebBookController {
    @Autowired
    private WebBookService webBookService;
    @RequestMapping(value = "/webBook",method = RequestMethod.GET)
    public String webBook(Model model){
        model.addAttribute("webBookList", webBookService.getWebBookList());
        return "webBook";
    }
    @RequestMapping(value = "/webBook",method = RequestMethod.POST)
    public DreamResult<WebBook> saveWebBook(WebBook webBook){
        DreamResult<WebBook> result = webBookService.saveWebBook(webBook);
        return result;
    }
}
