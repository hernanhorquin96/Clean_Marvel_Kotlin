package com.puzzlebench.clean_marvel_kotlin.domain.usecase

import com.puzzlebench.clean_marvel_kotlin.domain.contracts.CharacterService
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

class GetCharacterServiceUseCase(private val characterServiceImp: CharacterService) {
   operator fun invoke(): Observable<List<Character>> = characterServiceImp.getCaracters()
   operator fun invoke(id: Int): Observable<Character> = characterServiceImp.getCharacterById(id)
}