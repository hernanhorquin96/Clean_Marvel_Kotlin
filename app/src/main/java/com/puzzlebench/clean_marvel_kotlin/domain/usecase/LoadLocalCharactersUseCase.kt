package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.service.LoadLocalCharactersImpl
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

class LoadLocalCharactersUseCase {
    operator fun invoke(): List<Character> = loadLocalCharacterImpl.loadCharactersDB()

    companion object {
        private val loadLocalCharacterImpl: CharacterRepository.Load = LoadLocalCharactersImpl()
    }
}