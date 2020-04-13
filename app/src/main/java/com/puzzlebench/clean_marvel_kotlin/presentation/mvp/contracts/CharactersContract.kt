package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface CharactersContract {
    interface Model {
        fun getCharacterServiceUseCase(): Observable<List<Character>>
        fun storeCharactersUseCase(characters: List<Character>)
    }

    interface View {
        fun init()
        fun showToastNoItemToShow()
        fun showToastNetworkError(error: String)
        fun hideLoading()
        fun showCharacters(characters: List<Character>)
        fun showLoading()
    }

    interface Presenter {
        fun init()
        fun requestGetCharacters()
    }
}