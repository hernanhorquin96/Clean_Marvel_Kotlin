package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.data.repository.StoreCharactersImpl
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

class StoreCharactersUseCase {
    operator fun invoke(characters: List<Character>) {
        storeCharacters.saveCharactersDB(characters)
    }

    companion object {
        private val storeCharacters: CharacterRepository.Store = StoreCharactersImpl()
    }
}