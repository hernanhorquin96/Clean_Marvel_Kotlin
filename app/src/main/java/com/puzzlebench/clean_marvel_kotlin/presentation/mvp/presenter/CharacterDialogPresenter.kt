package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDialogPresenter(val view: CharacterDetailContract.View, val model: CharacterDetailContract.Model, private val characterId: Int)
    : CharacterDetailContract.Presenter {

    override fun requestCharacter() {
        view.showLoading()
        model.getCharacterServiceUseCase(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character ->
                    view.showCharacterInformation(character)
                    view.hideLoading()
                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
    }

}