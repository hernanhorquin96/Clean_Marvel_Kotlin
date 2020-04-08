package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable

interface DialogFragmentContract {

    interface Model {
        fun getCharacterServiceUseCase(): Observable<Character>
    }

    interface View {
        fun showToastNetworkError(error: String)
        fun showCharacterInformation(character: Character)
    }

    interface Presenter {
        fun requestCharacter(characterId: Int)
    }
}