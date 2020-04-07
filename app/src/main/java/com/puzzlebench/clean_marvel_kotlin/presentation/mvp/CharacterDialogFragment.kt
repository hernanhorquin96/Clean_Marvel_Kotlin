package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import kotlinx.android.synthetic.main.character_dialog_fragment.view.imgCharacter
import kotlinx.android.synthetic.main.character_dialog_fragment.view.txtCharacterName
import kotlinx.android.synthetic.main.character_dialog_fragment.view.txtCharacterDescription
import kotlinx.android.synthetic.main.character_dialog_fragment.view.btnClose


class CharacterDialogFragment() : DialogFragment() {

    private lateinit var character: Character

    override fun onStart() {
        super.onStart()
        val d: Dialog? = dialog
        d?.let { dialog ->
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView: View = inflater.inflate(R.layout.character_dialog_fragment, container, false)
        this.arguments.getParcelable<Character>(CharacterView.EXTRA_CHARACTER)?.let {
            character = it
        }
        rootView.imgCharacter.getImageByUrl("${character.thumbnail.path}.${character.thumbnail.extension}")
        rootView.txtCharacterName.text = character.name
        rootView.txtCharacterDescription.text = character.description
        rootView.btnClose.setOnClickListener { this.dismiss() }
        return rootView
    }
}