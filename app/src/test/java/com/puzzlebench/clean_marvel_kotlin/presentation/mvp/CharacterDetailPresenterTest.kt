package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharacterDetailContract
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterDetailModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterDialogPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterDetailFragmentView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CharacterDetailPresenterTest {
    private lateinit var character: Character
    private lateinit var view: CharacterDetailContract.View
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    private lateinit var charactersDetailPresenter: CharacterDetailContract.Presenter

    @Before
    fun setUp() {
        character = mock()
        view = mock<CharacterDetailFragmentView>()
        getCharacterServiceUseCase = mock()
        charactersDetailPresenter = CharacterDialogPresenter(view, CharacterDetailModel(getCharacterServiceUseCase), DEFAULT_ID)
    }

    @Test
    fun responseWithError() {
        whenever(getCharacterServiceUseCase.invoke(DEFAULT_ID)).thenReturn(Observable.error(Exception(EMPTY_MSG)))
        charactersDetailPresenter.requestCharacter()
        verify(view).showLoading()
        verify(getCharacterServiceUseCase).invoke(DEFAULT_ID)
        verify(view).hideLoading()
        verify(view).showToastNetworkError(EMPTY_MSG)
    }

    @Test
    fun responseWithItemToShow() {
        val observable = Observable.just(character)
        whenever(getCharacterServiceUseCase.invoke(DEFAULT_ID)).thenReturn(observable)
        charactersDetailPresenter.requestCharacter()
        verify(view).showLoading()
        verify(getCharacterServiceUseCase).invoke(DEFAULT_ID)
        verify(view).showCharacterInformation(character)
        verify(view).hideLoading()
    }

    companion object {
        private const val EMPTY_MSG = ""
        private const val DEFAULT_ID = 1017100
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