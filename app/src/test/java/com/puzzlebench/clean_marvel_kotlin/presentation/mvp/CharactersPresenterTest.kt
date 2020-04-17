package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.LoadLocalCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.StoreCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharactersContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharactersModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharactersPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharactersView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CharactersPresenterTest {
    private lateinit var characters: List<Character>
    private lateinit var view: CharactersContract.View
    private lateinit var storeCharactersUseCase: StoreCharactersUseCase
    private lateinit var loadCharactersUseCase: LoadLocalCharactersUseCase
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var charactersPresenter: CharactersContract.Presenter

    @Before
    fun setUp() {
        characters = mock()
        view = mock<CharactersView>()
        storeCharactersUseCase = mock()
        loadCharactersUseCase = mock()
        getCharacterServiceUseCase = mock()
        charactersPresenter =
                CharactersPresenter(
                        view,
                        CharactersModel(getCharacterServiceUseCase, storeCharactersUseCase, loadCharactersUseCase)
                )
    }

    @Test
    fun responseWithError() {
        whenever(getCharacterServiceUseCase.invoke()).thenReturn(Observable.error(Exception(EMPTY_MSG)))
        characters = emptyList()
        charactersPresenter.init()
        charactersPresenter.requestGetCharacters()
        verify(view).init()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).showToastNetworkError(EMPTY_MSG)
    }

    @Test
    fun responseWithItemToShow() {
        val observable = Observable.just(characters)
        whenever(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        charactersPresenter.init()
        charactersPresenter.requestGetCharacters()
        verify(view).init()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).showCharacters(characters)
    }

    @Test
    fun responseWithoutItemToShow() {
        characters = emptyList()
        val observable = Observable.just(characters)
        whenever(getCharacterServiceUseCase()).thenReturn(observable)
        charactersPresenter.init()
        charactersPresenter.requestGetCharacters()
        verify(view).init()
        verify(getCharacterServiceUseCase).invoke()
        verify(view).hideLoading()
        verify(view).showToastNoItemToShow()
    }

    @Test
    fun responseLocalCharacterCorrect() {
        whenever(loadCharactersUseCase.invoke()).thenReturn(characters)
        charactersPresenter.init()
        charactersPresenter.loadLocalData()
        verify(view).init()
        verify(loadCharactersUseCase).invoke()
        verify(view).hideLoading()
        verify(view).showCharacters(characters)
    }

    @Test
    fun responseLocalCharacterError() {
        characters = emptyList()
        whenever(loadCharactersUseCase.invoke()).thenReturn(characters)
        charactersPresenter.init()
        charactersPresenter.loadLocalData()
        verify(view).init()
        verify(loadCharactersUseCase).invoke()
        verify(view).hideLoading()
        verify(view).showToastNoItemToShow()
    }

    companion object {
        private const val EMPTY_MSG = ""
        private const val NO_DELAY = 0

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, NO_DELAY.toLong(), unit)
                }
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }
}