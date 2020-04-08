package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharactersContract
import io.reactivex.Observable

class CharactersModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase): CharactersContract.Model {
    override fun getCharacterServiceUseCase(): Observable<List<Character>> = getCharacterServiceUseCase.invoke()
}