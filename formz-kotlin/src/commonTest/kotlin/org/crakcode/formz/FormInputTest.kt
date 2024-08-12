package org.crakcode.formz

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FormInputTest {

    @Test
    fun `isValid returns true for valid value`() {
        val input = StringFormInput("Valid Input")
        assertTrue(input.isValid)
    }

    @Test
    fun `isValid returns false for invalid value`() {
        val input = StringFormInput("")
        assertFalse(input.isValid)
    }

    @Test
    fun `isNotValid returns true for invalid value`() {
        val input = StringFormInput("")
        assertTrue(input.isNotValid)
    }

    @Test
    fun `isNotValid returns false for valid value`() {
        val input = StringFormInput("Valid Input")
        assertFalse(input.isNotValid)
    }

    @Test
    fun `error returns null for valid value`() {
        val input = StringFormInput("Valid Input")
        assertNull(input.error)
    }

    @Test
    fun `error returns validation error for invalid value`() {
        val input = StringFormInput("")
        assertEquals(ValidationError.Blank, input.error)
    }

    @Test
    fun `displayError returns null for valid value`() {
        val input = StringFormInput("Valid Input", isPure = false)
        assertNull(input.displayError)
    }

    @Test
    fun `displayError returns validation error for invalid value and isPure is false`() {
        val input = StringFormInput("", isPure = false)
        assertEquals(ValidationError.Blank, input.displayError)
    }

    @Test
    fun `displayError returns null if isPure is true regardless of value`() {
        val input = StringFormInput("", isPure = true)
        assertNull(input.displayError)
    }

    @Test
    fun `equals returns true for identical inputs`() {
        val input1 = StringFormInput("Test", isPure = false)
        val input2 = StringFormInput("Test", isPure = false)
        assertEquals(input1, input2)
    }

    @Test
    fun `equals returns false for different inputs`() {
        val input1 = StringFormInput("Test1", isPure = false)
        val input2 = StringFormInput("Test2", isPure = false)
        assertNotEquals(input1, input2)
    }

    @Test
    fun `hashCode returns same value for identical inputs`() {
        val input1 = StringFormInput("Test", isPure = false)
        val input2 = StringFormInput("Test", isPure = false)
        assertEquals(input1.hashCode(), input2.hashCode())
    }

    @Test
    fun `hashCode returns different values for different inputs`() {
        val input1 = StringFormInput("Test1", isPure = false)
        val input2 = StringFormInput("Test2", isPure = false)
        assertNotEquals(input1.hashCode(), input2.hashCode())
    }
}
