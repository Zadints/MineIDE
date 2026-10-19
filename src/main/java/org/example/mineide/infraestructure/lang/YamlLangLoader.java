package org.example.mineide.infraestructure.lang;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlLangLoader {

    public InMemoryLangRepository load(String language) {
        String resource = "/lang/" + language + ".yml";

        try (InputStream input = getClass().getResourceAsStream(resource)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "No se encontró " + resource
                );
            }

            Map<String, Object> yaml = new Yaml().load(input);

            Map<String, String> translations = new LinkedHashMap<>();

            flatten("", yaml, translations);

            return new InMemoryLangRepository(translations);

        } catch (java.io.IOException e) {
            throw new IllegalStateException(
                    "Error al cargar el idioma", e
            );
        }
    }

    private void flatten(
        String prefix,
        Map<String, Object> source,
        Map<String, String> result
    ) {
        if (source == null) {
            return;
        }

        for (var entry : source.entrySet()) {
            String key = prefix.isEmpty()
                    ? entry.getKey()
                    : prefix + "." + entry.getKey();

            Object value = entry.getValue();

            if (value instanceof Map<?, ?> nested) {
                Map<String, Object> child = new LinkedHashMap<>();

                nested.forEach((k, v) ->
                        child.put(String.valueOf(k), v));

                flatten(key, child, result);
            } else if (value instanceof String || value instanceof Number
                    || value instanceof Boolean) {
                result.put(key, String.valueOf(value));
            }
        }
    }
}
