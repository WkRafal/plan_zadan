package com.github.WkRafal.plan_zadan.configurtion;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private Templete templete;

    public Templete getTemplete() {
        return templete;
    }

    public void setTemplete(Templete templete) {
        this.templete = templete;
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
