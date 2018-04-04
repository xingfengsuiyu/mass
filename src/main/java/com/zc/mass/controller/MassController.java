package com.zc.mass.controller;



import com.zc.mass.service.MassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;


@Controller
public class MassController {

    @Autowired
    @Qualifier("massServiceImpl")
    private MassService massService;

    @RequestMapping("/mass")
    @ResponseBody
    public String mass(@RequestParam("file") MultipartFile file) {
        try {
            massService.mass(file.getInputStream());
            return "发送成功";
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }






}