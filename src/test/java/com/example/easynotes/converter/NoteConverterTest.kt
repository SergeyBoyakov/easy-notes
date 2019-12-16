package com.example.easynotes.converter

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.example.easynotes.dto.NoteDto
import com.example.easynotes.model.Note
import org.junit.jupiter.api.Test

internal class NoteConverterTest {
    @Test
    fun `should convert collection of entitys to dtos`() {
        // given
        val firstNote = Note().apply {
            title = "first title"
            content = "first content"
        }
        val secondNote = Note().apply {
            title = "second title"
            content = "second content"
        }

        // when
        val convertedDtos = NoteConverter().toDtos(listOf(firstNote, secondNote))

        // then
        assertAll {
            assertThat(convertedDtos.map { it.title }).containsExactly("first title", "second title")
            assertThat(convertedDtos.map { it.content }).containsExactly("first content", "second content")
        }
    }

    @Test
    fun `should convert from dto to entity`() {
        // given
        val noteDto = NoteDto().apply {
            title = "title"
            content = "content"
        }

        // when
        val entity = NoteConverter().toEntity(noteDto)

        // then
        assertAll {
            assertThat(entity.content).isEqualTo("content")
            assertThat(entity.title).isEqualTo("title")
        }
    }

    @Test
    fun `should convert from entity to dto`() {
        // given
        val note = Note().apply {
            title = "title"
            content = "content"
        }

        // when
        val dto = NoteConverter().toDto(note)

        // then
        assertAll {
            assertThat(dto.content).isEqualTo("content")
            assertThat(dto.title).isEqualTo("title")
        }
    }

    @Test
    fun `should return null if dto null`() {
        // when
        val entity = NoteConverter().toEntity(null)

        // then
        assertThat(entity).isNull()
    }

    @Test
    fun `should return null if entity null`() {
        // when
        val dto = NoteConverter().toDto(null)

        // then
        assertThat(dto).isNull()
    }
}