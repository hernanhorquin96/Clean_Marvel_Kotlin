package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterDetailFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import kotlinx.android.synthetic.main.character_dialog_fragment.imgCharacter
import kotlinx.android.synthetic.main.character_dialog_fragment.txtCharacterName
import kotlinx.android.synthetic.main.character_dialog_fragment.txtCharacterDescription
import java.lang.ref.WeakReference

class CharacterDetailFragmentView(fragment: CharacterDetailFragment): CharacterDetailContract.View {
    private val fragmentRef = WeakReference(fragment)

    private fun getFragmentRef() = fragmentRef.get()

    override fun showToastNetworkError(error: String) {
        getFragmentRef()?.context?.showToast(error)
    }

    override fun showCharacterInformation(character: Character) {
        getFragmentRef()?.imgCharacter?.getImageByUrl("${character.thumbnail.path}$DOT${character.thumbnail.extension}")
        getFragmentRef()?.txtCharacterName?.text = character.name
        getFragmentRef()?.txtCharacterDescription?.text = character.description
    }

    companion object {
        private const val DOT = "."
    }
}