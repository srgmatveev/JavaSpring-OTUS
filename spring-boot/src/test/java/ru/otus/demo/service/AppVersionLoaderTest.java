package ru.otus.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.demo.config.ApplicationSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AppVersionLoaderTest {

    private AppVersionLoader appVersionLoader;


    @MockBean
    private ApplicationSettings settings;
    private ApplicationSettings settings_ctx;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setMockOutput() {
        //settings = Mockito.mock(ApplicationSettings.class);
        when(settings.getVersion()).thenReturn("15");
        settings_ctx = (ApplicationSettings) context.getBean("applicationSettings");
        appVersionLoader = new AppVersionLoader(settings_ctx);
    }

    @Test
    void getAppVersion() {
        appVersionLoader.getAppVersion();
        assertEquals(appVersionLoader.getAppVersion(), "15");
        Mockito.verify(settings_ctx, atLeast(2)).getVersion();
    }
}