package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import io.reactivex.Observable

class CharacterDetailModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase): CharacterDetailContract.Model {
    override fun getCharacterInfo(characterId: Int): Observable<Character> = getCharacterServiceUseCase(characterId)
}