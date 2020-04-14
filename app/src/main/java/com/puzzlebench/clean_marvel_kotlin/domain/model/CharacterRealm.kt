package com.puzzlebench.clean_marvel_kotlin.domain.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterRealm(
    @PrimaryKey var id: Int = DEFAULT_ID,
    var name: String = DEFAULT_STRING,
    var description: String = DEFAULT_STRING,
    var thumbnail: ThumbnailRealm? = ThumbnailRealm()
) : RealmObject() {
    companion object {
        private const val DEFAULT_ID = 0
        private const val DEFAULT_STRING = ""
    }
}