package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperService
import com.puzzlebench.clean_marvel_kotlin.data.service.api.MarvelApi
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable


class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(), private val mapper: CharacterMapperService = CharacterMapperService()) {

    fun getCharacterById(id: Int): Observable<Character> {
        return Observable.create { subscriber ->
            val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.data?.characters?.let { characters ->
                    subscriber.onNext(mapper.transform(characters).get(0))
                    subscriber.onComplete()
                }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getCaracters(): Observable<List<Character>> {
        return Observable.create { subscriber ->
            val callResponse = api.createService(MarvelApi::class.java).getCharacter()
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.data?.characters?.let { characters ->
                    subscriber.onNext(mapper.transform(characters))
                    subscriber.onComplete()
                }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}