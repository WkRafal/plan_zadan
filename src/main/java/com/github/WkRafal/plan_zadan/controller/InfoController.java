package com.github.WkRafal.plan_zadan.controller;

import com.github.WkRafal.plan_zadan.configurtion.TaskConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
//    @Value("${spring.datasource.url}")
//    private String url;

//    @Value("${task.allowMultipleTasks}")
//    private boolean prop;
    private DataSourceProperties dataSource;
    private TaskConfigurationProperties prop;

    public InfoController(final DataSourceProperties dataSource, final TaskConfigurationProperties prop) {
        this.dataSource = dataSource;
        this.prop = prop;
    }

    @GetMapping("/info/url")
    String url(){
        return dataSource.getUrl();
    }

    @GetMapping("/info/prop")
    boolean myProp() {
        return prop.getTemplete().isAllowMultipleTasks();
    }
}
