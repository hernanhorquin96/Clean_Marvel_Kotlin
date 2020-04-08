package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterDetailFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.ActivityContract
import kotlinx.android.synthetic.main.activity_main.recycleView
import kotlinx.android.synthetic.main.activity_main.progressBar
import java.lang.ref.WeakReference


class CharactersView(activity: CharacterActivity): ActivityContract.View {
    private val activityRef = WeakReference(activity)
    var adapter = CharacterAdapter(emptyList()) { character ->
        activity.applicationContext.showToast(character.name)
        val characterFragment = CharacterDetailFragment.newInstance(character.id)
        characterFragment.show(activity.supportFragmentManager, activity.getString(R.string.tag))
    }

    override fun init() {
        activityRef.get()?.let {
            it.recycleView.layoutManager = LinearLayoutManager(it.applicationContext, RecyclerView.VERTICAL, false)
            it.recycleView.adapter = adapter
            showLoading()
        }
    }

    override fun showToastNoItemToShow() {
        activityRef.get()?.let {
            val message = it.baseContext.resources.getString(R.string.message_no_items_to_show)
            it.applicationContext.showToast(message)
        }
    }

    override fun showToastNetworkError(error: String) {
        activityRef.get()?.applicationContext?.showToast(error)
    }

    override fun hideLoading() {
        activityRef.get()?.progressBar?.visibility = View.GONE
    }

    override fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    override fun showLoading() {
        activityRef.get()?.progressBar?.visibility = View.VISIBLE
    }
}
