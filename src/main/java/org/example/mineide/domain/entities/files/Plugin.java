package org.example.mineide.domain.entities.files;

import java.util.Set;

public class Plugin {
    private String pluginName;
    private Set<String> pluginAuthors;
    private Set<String> pluginSoftDepend;
    private String pluginVersion;


    public Plugin(String pluginName, Set<String> pluginAuthors, Set<String> pluginSoftDepend, String pluginVersion) {
        this.pluginName = pluginName;
        this.pluginAuthors = pluginAuthors;
        this.pluginSoftDepend = pluginSoftDepend;
        this.pluginVersion = pluginVersion;
    }

    public String getPluginName() {
        return pluginName;
    }

    public Set<String> getPluginAuthors() {
        return pluginAuthors;
    }

    public Set<String> getPluginSoftDepend() {
        return pluginSoftDepend;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }
}
