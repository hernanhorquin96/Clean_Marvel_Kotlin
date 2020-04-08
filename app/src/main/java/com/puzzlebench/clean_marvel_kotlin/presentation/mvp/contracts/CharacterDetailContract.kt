package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface CharacterDetailContract {

    interface Model {
        fun getCharacterServiceUseCase(characterId: Int): Observable<Character>
    }

    interface View {
        fun showToastNetworkError(error: String)
        fun showCharacterInformation(character: Character)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun requestCharacter()
    }
}