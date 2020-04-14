package com.puzzlebench.clean_marvel_kotlin.domain.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

interface CharacterRepository {
    fun saveCharactersDB(characters: List<Character>)
}