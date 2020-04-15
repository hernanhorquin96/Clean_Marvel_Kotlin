package com.puzzlebench.clean_marvel_kotlin.domain.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character

interface CharacterRepository {

    interface Store {
        fun saveCharactersDB(characters: List<Character>)
    }

    interface Load {
        fun loadCharactersDB(): List<Character>
    }
}