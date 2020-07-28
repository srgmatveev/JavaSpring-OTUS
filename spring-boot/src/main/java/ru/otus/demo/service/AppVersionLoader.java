package ru.otus.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.demo.config.ApplicationSettings;

@Service
public class AppVersionLoader {
    private String appVersion;

    public AppVersionLoader(ApplicationSettings applicationSettings) {
        appVersion = applicationSettings.getVersion();
    }

    public String getAppVersion() {
        return appVersion;
    }
}
