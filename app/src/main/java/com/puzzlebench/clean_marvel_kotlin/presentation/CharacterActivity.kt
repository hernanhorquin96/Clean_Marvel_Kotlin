package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharactersContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharactersModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharactersPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharactersView

open class CharacterActivity : BaseRxActivity() {

    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val presenter: CharactersContract.Presenter  = CharactersPresenter(CharactersView(this), CharactersModel(getCharacterServiceUseCase))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init()
    }
}
