package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterDetailModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterDialogPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterDetailFragmentView
import kotlinx.android.synthetic.main.character_dialog_fragment.view.btnClose

class CharacterDetailFragment() : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.character_dialog_fragment, container, false)
        this.arguments.getInt(CHARACTER_ID).let { characterId ->
            val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
            val characterDetailPresenter: CharacterDetailContract.Presenter =
                   CharacterDialogPresenter(
                           CharacterDetailFragmentView(this),
                           CharacterDetailModel(getCharacterServiceUseCase,characterId)
                   )
            characterDetailPresenter.requestCharacter()
        }
        rootView.btnClose.setOnClickListener {
            this.dismiss()
        }
        return rootView
    }

    companion object {
        private const val CHARACTER_ID = "character_id"
        fun newInstance(character_id: Int): CharacterDetailFragment {
            val args = Bundle()
            args.putInt(CHARACTER_ID, character_id)
            val fragment = CharacterDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}