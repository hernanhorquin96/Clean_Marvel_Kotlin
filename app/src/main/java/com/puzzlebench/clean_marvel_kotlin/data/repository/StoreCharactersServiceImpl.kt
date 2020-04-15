package com.puzzlebench.clean_marvel_kotlin.data.repository

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperRealm
import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterRepository
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.realm.Realm

class StoreCharactersServiceImpl(private val mapper: CharacterMapperRealm = CharacterMapperRealm()) : CharacterRepository.Store {

    override fun saveCharactersDB(characters: List<Character>) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.insertOrUpdate(mapper.transformToListOfCharactersRealm(characters))
        }
    }
}