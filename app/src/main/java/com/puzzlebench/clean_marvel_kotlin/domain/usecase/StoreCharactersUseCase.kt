package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character


open class StoreCharactersUseCase(private val storeCharactersService: CharacterRepository) {
    open operator fun invoke(characters: List<Character>) = storeCharactersService.saveCharactersDB(characters)
}