package com.puzzlebench.clean_marvel_kotlin.domain.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface CharacterService {
    fun getCharacterById(id: Int): Observable<Character>
    fun getCaracters(): Observable<List<Character>>
}