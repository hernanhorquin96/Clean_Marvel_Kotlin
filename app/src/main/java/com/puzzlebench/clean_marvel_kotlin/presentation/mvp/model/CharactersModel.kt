package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.LoadLocalCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.StoreCharactersUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.contracts.CharactersContract
import io.reactivex.Observable

class CharactersModel(private val getCharacterServiceUseCase: GetCharacterServiceUseCase,
                      private val storeCharactersUseCase: StoreCharactersUseCase,
                      private val loadCharactersUseCase: LoadLocalCharactersUseCase
) : CharactersContract.Model {
    override fun getCharactersInfo(): Observable<List<Character>> = getCharacterServiceUseCase()
    override fun storeCharacters(characters: List<Character>){
        storeCharactersUseCase(characters)
    }
    override fun getLocalData(): List<Character> = loadCharactersUseCase()

}