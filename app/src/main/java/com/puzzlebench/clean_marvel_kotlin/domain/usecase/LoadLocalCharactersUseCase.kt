package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.LoadLocalCharactersImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

class LoadLocalCharactersUseCase(private val loadLocalCharacterImpl: LoadLocalCharactersImpl) {
    operator fun invoke(): List<Character> = loadLocalCharacterImpl.loadCharactersDB()
}