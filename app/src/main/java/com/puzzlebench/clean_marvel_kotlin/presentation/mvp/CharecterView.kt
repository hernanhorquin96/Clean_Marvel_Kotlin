package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_dialog_fragment.*
import kotlinx.android.synthetic.main.character_dialog_fragment.view.*
import java.lang.ref.WeakReference


class CharecterView(activity: MainActivity) {

    companion object {
        const val EXTRA_CHARACTER = "character"
    }

    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = 1
    var adapter = CharacterAdapter(emptyList(),
            { character -> activity.applicationContext.showToast(character.name) },
            { character -> activity.getCharacterById(character.id) })

    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            activity.recycleView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
            activity.recycleView.adapter = adapter
            showLoading()
        }

    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        if (activity != null) {
            val message = activity.baseContext.resources.getString(R.string.message_no_items_to_show)
            activity.applicationContext.showToast(message)

        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()!!.applicationContext.showToast(error)
    }

    fun hideLoading() {
        activityRef.get()!!.progressBar.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()!!.progressBar.visibility = View.VISIBLE

    }



    fun showCharacterInformation(character: Character) {
        val dialogFragment = CharacterDialogFragment()
        val args = Bundle()
        args.putParcelable(EXTRA_CHARACTER, character)
        dialogFragment.arguments = args
        dialogFragment.show(activityRef.get()?.supportFragmentManager, "txn_tag")
    }

    class CharacterDialogFragment() : DialogFragment() {

        private lateinit var character: Character


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onStart() {
            super.onStart()
            val d: Dialog? = dialog
            if (d != null) {
                val width = ViewGroup.LayoutParams.MATCH_PARENT
                val height = ViewGroup.LayoutParams.MATCH_PARENT
                d.window?.setLayout(width, height)
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            var rootView: View = inflater.inflate(R.layout.character_dialog_fragment, container, false)
            character = this.arguments.getParcelable(EXTRA_CHARACTER)!!
            rootView.imgCharacter.getImageByUrl(character.thumbnail.path + "." + character.thumbnail.extension)
            rootView.txtCharacterName.text = character.name
            rootView.txtCharacterDescription.text = character.description
            rootView.btnClose.setOnClickListener { this.dismiss() }
            return rootView
        }
    }
}
