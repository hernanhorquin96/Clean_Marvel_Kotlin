package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.CharacterRealm
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.ThumbnailRealm

open class CharacterMapperRealm : BaseMapperRepository<CharacterRealm, Character> {

    override fun transform(characterRealm: CharacterRealm): Character = Character(
            characterRealm.id,
            characterRealm.name.toString(),
            characterRealm.description.toString(),
            transformToThumbnail(characterRealm.thumbnail)
    )

    override fun transformToResponse(character: Character): CharacterRealm = CharacterRealm(
            character.id,
            character.name,
            character.description,
            transformToThumbnailRealm(character.thumbnail)
    )

    fun transformToThumbnailRealm(thumbnailResponse: Thumbnail): ThumbnailRealm = ThumbnailRealm(
            thumbnailResponse.path,
            thumbnailResponse.extension
    )

    fun transformToThumbnail(thumbnail: ThumbnailRealm?) = Thumbnail(
            thumbnail?.path.toString(),
            thumbnail?.extension.toString()
    )

    fun transformToListOfCharactersRealm(characters: List<Character>) = characters.map { transformToResponse(it) }
    fun transformToListOfCharacters(charactersRealm: List<CharacterRealm>) = charactersRealm.map { transform(it) }
}