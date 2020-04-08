package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import io.reactivex.Observable

class CharacterDetailModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase, private val characterId: Int): CharacterDetailContract.Model {
    override fun getCharacterServiceUseCase(): Observable<Character> = getCharacterServiceUseCase.invoke(characterId)
}