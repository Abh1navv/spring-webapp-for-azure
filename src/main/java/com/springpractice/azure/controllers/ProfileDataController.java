package com.springpractice.azure.controllers;

import com.springpractice.azure.helpers.BlobHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProfileDataController {

    @GetMapping("/profile")
    public String getResume() {
        String jsonString = "";
        try {
            jsonString = BlobHelper.getInstance().getJSONData("resume");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return jsonString;
    }

    @GetMapping("/background")
    public String getProfileUri() {
        return BlobHelper.getInstance().getStaticResourceURI() + "background.JPG";
    }
}
