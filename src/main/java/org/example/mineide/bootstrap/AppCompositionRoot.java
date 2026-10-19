package org.example.mineide.bootstrap;

import org.example.mineide.application.usecase.GetTranslationUseCase;
import org.example.mineide.domain.repository.LangRepository;
import org.example.mineide.infraestructure.lang.InMemoryLangRepository;
import org.example.mineide.infraestructure.lang.YamlLangLoader;

public class AppCompositionRoot {

    private final InMemoryLangRepository langRepository;
    private final GetTranslationUseCase getTranslation;


    public AppCompositionRoot() {

        YamlLangLoader langLoader = new YamlLangLoader();

        langRepository = langLoader.load("es");

        LangRepository lang = langRepository;

        getTranslation = new GetTranslationUseCase(lang);
    }

    public GetTranslationUseCase getTranslation() {
        return getTranslation;
    }

}
