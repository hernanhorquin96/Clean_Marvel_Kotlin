package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.mocks.factory.CharactersFactory
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharactersModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharactersPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharactersView
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class CharactersPresenterTest {

    companion object {
        private const val EMPTY_MSG = ""
    }
    private var view = mock(CharactersView::class.java)
    private var characterServiceImp = mock(CharacterServicesImpl::class.java)
    private lateinit var charactersPresenter: CharactersPresenter
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        charactersPresenter = CharactersPresenter(view, CharactersModel(getCharacterServiceUseCase))
    }

    @Ignore
    fun reposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Observable.error(Exception(EMPTY_MSG)))
        charactersPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).hideLoading()
        verify(view).showToastNetworkError(EMPTY_MSG)
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharecters = CharactersFactory.getMockCharacter()
        val observable = Observable.just(itemsCharecters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        charactersPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()
        verify(view).hideLoading()
        verify(view).showCharacters(itemsCharecters)
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharecters = emptyList<Character>()
        val observable = Observable.just(itemsCharecters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        charactersPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCaracters()
    }
}