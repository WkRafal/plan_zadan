package com.github.WkRafal.plan_zadan.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private Templete template;

    public Templete getTemplate() {
        return template;
    }

    public void setTemplate(Templete template) {
        this.template = template;
    }

    public static class Templete{
        private boolean allowMultipleTasks;

        public boolean isAllowMultipleTasks() {
            return allowMultipleTasks;
        }

        public void setAllowMultipleTasks(boolean allowMultipleTasks) {
            this.allowMultipleTasks = allowMultipleTasks;
        }
    }

}
