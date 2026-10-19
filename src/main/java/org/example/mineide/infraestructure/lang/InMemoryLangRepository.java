package org.example.mineide.infraestructure.lang;

import org.example.mineide.domain.repository.LangRepository;

import java.util.Map;

public class InMemoryLangRepository implements LangRepository {

    private final Map<String, String> translations;

    public InMemoryLangRepository(Map<String, String> translations) {
        this.translations = Map.copyOf(translations);
    }

    @Override
    public String get(String key) {
        return translations.getOrDefault(key, key);
    }
}
