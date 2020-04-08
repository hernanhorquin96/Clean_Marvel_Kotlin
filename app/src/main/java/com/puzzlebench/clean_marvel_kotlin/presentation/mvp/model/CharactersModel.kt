package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.ActivityContract
import io.reactivex.Observable

class CharactersModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase): ActivityContract.Model {
    override fun getCharacterServiceUseCase(): Observable<List<Character>> = getCharacterServiceUseCase.invoke()
}