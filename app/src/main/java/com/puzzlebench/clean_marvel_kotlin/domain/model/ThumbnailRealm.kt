package com.puzzlebench.clean_marvel_kotlin.domain.model

import io.realm.RealmObject
import org.jetbrains.annotations.NotNull

open class ThumbnailRealm(
        var path: String = DEFAULT_STRING,
        var extension: String = DEFAULT_STRING
): RealmObject() {
    companion object {
        private const val DEFAULT_STRING = ""
    }
}