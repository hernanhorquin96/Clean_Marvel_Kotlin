package com.puzzlebench.clean_marvel_kotlin.data.repository

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperRealm
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import io.realm.Realm

class LoadLocalCharactersImpl(private val mapper: CharacterMapperRealm = CharacterMapperRealm()): CharacterRepository.Load {
    override fun loadCharactersDB(): List<Character> {
        val realm = Realm.getDefaultInstance()
        return mapper.transformToListOfCharacters(realm.where(CharacterRealm::class.java).findAll())
    }
}