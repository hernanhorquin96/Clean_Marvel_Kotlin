package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterDetailFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import kotlinx.android.synthetic.main.character_dialog_fragment.img_character
import kotlinx.android.synthetic.main.character_dialog_fragment.txt_character_name
import kotlinx.android.synthetic.main.character_dialog_fragment.progress_bar
import kotlinx.android.synthetic.main.character_dialog_fragment.txt_character_description
import java.lang.ref.WeakReference

class CharacterDetailFragmentView(fragment: CharacterDetailFragment): CharacterDetailContract.View {
    private val fragmentRef = WeakReference(fragment)

    private fun getFragmentRef() = fragmentRef.get()

    override fun showToastNetworkError(error: String) {
        getFragmentRef()?.context?.showToast(error)
    }

    override fun showCharacterInformation(character: Character) {
        getFragmentRef()?.img_character?.getImageByUrl("${character.thumbnail.path}$DOT${character.thumbnail.extension}")
        getFragmentRef()?.txt_character_name?.text = character.name
        getFragmentRef()?.txt_character_description?.text = character.description
    }

    override fun showLoading() {
        getFragmentRef()?.progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        getFragmentRef()?.progress_bar?.visibility = View.GONE
    }

    companion object {
        private const val DOT = "."
    }
}