package com.springpractice.azure.controllers;

import com.azure.core.annotation.Get;
import com.springpractice.azure.helpers.BlobHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileDataController {

    @GetMapping("/profile")
    public String getResume() {
        return BlobHelper.getInstance().getJSONData("resume");
    }

    @GetMapping("/background")
    public String getProfileUri() {
        return BlobHelper.getInstance().getStaticResourceURI() + "background.JPG";
    }
}
