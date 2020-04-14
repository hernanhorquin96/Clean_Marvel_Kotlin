package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.LoadLocalCharactersImpl
import com.puzzlebench.clean_marvel_kotlin.data.service.StoreCharactersServiceImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.LoadLocalCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.StoreCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharactersContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharactersModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharactersPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharactersView
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.button_refresh

class CharacterActivity : BaseRxActivity() {

    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val storeCharactersUseCase = StoreCharactersUseCase(StoreCharactersServiceImpl())
    val loadCharactersUseCase = LoadLocalCharactersUseCase(LoadLocalCharactersImpl())
    val presenter: CharactersContract.Presenter  =
                CharactersPresenter(
                    CharactersView(this),
                    CharactersModel(getCharacterServiceUseCase,storeCharactersUseCase,loadCharactersUseCase)
                )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init()

        button_refresh.setOnClickListener {
            presenter.requestGetCharacters()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
    }
}
