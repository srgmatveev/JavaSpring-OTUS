package ru.otus.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.demo.service.AppVersionLoader;

@RestController
public class AppVersionController {
    private AppVersionLoader appVersionLoader;

    public AppVersionController(AppVersionLoader appVersionLoader) {
        this.appVersionLoader = appVersionLoader;
    }

    @GetMapping("/version")
    public String getVersion() {
        return appVersionLoader.getAppVersion();
    }
}
