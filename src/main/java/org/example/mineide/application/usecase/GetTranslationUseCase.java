package org.example.mineide.application.usecase;

import org.example.mineide.domain.repository.LangRepository;

public class GetTranslationUseCase {

    private final LangRepository repository;

    public GetTranslationUseCase(LangRepository repository) {
        this.repository = repository;
    }

    public String execute(String key) {
        return repository.get(key);
    }
}
