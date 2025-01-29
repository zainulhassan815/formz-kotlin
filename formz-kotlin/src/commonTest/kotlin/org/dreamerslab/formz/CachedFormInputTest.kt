package org.dreamerslab.formz

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertSame

class CachedFormInputTest {

    @Test
    fun `cachedError remains consistent across multiple calls`() {
        val input = CachedStringFormInput("Valid Input")
        val firstErrorCall = input.error
        val secondErrorCall = input.error
        assertSame(firstErrorCall, secondErrorCall)
    }

    @Test
    fun `error returns validation error for invalid value`() {
        val input = CachedStringFormInput("")
        assertEquals(ValidationError.Blank, input.error)
    }

    @Test
    fun `error returns null for valid value`() {
        val input = CachedStringFormInput("Valid Input")
        assertNull(input.error)
    }

    @Test
    fun `equals returns true for identical inputs`() {
        val input1 = CachedStringFormInput("Test", isPristine = false)
        val input2 = CachedStringFormInput("Test", isPristine = false)
        assertEquals(input1, input2)
    }

    @Test
    fun `equals returns false for different inputs`() {
        val input1 = CachedStringFormInput("Test1", isPristine = false)
        val input2 = CachedStringFormInput("Test2", isPristine = false)
        assertNotEquals(input1, input2)
    }

    @Test
    fun `hashCode returns same value for identical inputs`() {
        val input1 = CachedStringFormInput("Test", isPristine = false)
        val input2 = CachedStringFormInput("Test", isPristine = false)
        assertEquals(input1.hashCode(), input2.hashCode())
    }

    @Test
    fun `hashCode returns different values for different inputs`() {
        val input1 = CachedStringFormInput("Test1", isPristine = false)
        val input2 = CachedStringFormInput("Test2", isPristine = false)
        assertNotEquals(input1.hashCode(), input2.hashCode())
    }
}
