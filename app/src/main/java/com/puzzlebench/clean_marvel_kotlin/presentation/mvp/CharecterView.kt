package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.recycleView
import kotlinx.android.synthetic.main.activity_main.progressBar
import java.lang.ref.WeakReference


class CharacterView(activity: MainActivity) {

    companion object {
        const val EXTRA_CHARACTER = "character"
        private const val TAG = "txn_tag"
    }

    private val activityRef = WeakReference(activity)
    var adapter = CharacterAdapter(emptyList()) { character ->
                activity.applicationContext.showToast(character.name)
                activity.getCharacterById(character.id)
    }

    fun init() {
        val activity = activityRef.get()
        activity?.let { act ->
            act.recycleView.layoutManager = LinearLayoutManager(act.applicationContext, RecyclerView.VERTICAL, false)
            act.recycleView.adapter = adapter
            showLoading()
        }
    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        activity?.let { act ->
            val message = act.baseContext.resources.getString(R.string.message_no_items_to_show)
            act.applicationContext.showToast(message)
        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()?.applicationContext?.showToast(error)
    }

    fun hideLoading() {
        activityRef.get()?.progressBar?.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()?.progressBar?.visibility = View.VISIBLE
    }

    fun showCharacterInformation(character: Character) {
        val dialogFragment = CharacterDialogFragment()
        val args = Bundle()
        args.putParcelable(EXTRA_CHARACTER, character)
        dialogFragment.arguments = args
        dialogFragment.show(activityRef.get()?.supportFragmentManager, TAG)
    }

}
