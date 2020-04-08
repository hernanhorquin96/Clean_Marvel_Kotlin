package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.ActivityContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharactersView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharactersPresenter(view: CharactersView, val model: ActivityContract.Model)
    : Presenter<CharactersView>(view), ActivityContract.Presenter {

    override fun init() {
        view.init()
        requestGetCharacters()
    }

    override fun requestGetCharacters() {
        model.getCharacterServiceUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    if (characters.isEmpty()) {
                        view.showToastNoItemToShow()
                    } else {
                        view.showCharacters(characters)
                    }
                    view.hideLoading()
                }, { e ->
                    view.hideLoading()
                    view.showToastNetworkError(e.message.toString())
                })
    }
}
