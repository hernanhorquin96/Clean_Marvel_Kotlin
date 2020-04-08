package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterDetailFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDialogPresenter(val view: CharacterDetailFragmentView, val model: CharacterDetailContract.Model)
    : CharacterDetailContract.Presenter {

    override fun requestCharacter(characterId: Int) {
        model.getCharacterServiceUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character ->
                    view.showCharacterInformation(character)
                }, { e ->
                    view.showToastNetworkError(e.message.toString())
                })
    }

}