package org.dreamerslab.formz

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

data class SignupFormState(
    val name: FormInput<String, ValidationError> = StringFormInput(""),
    val email: FormInput<String, ValidationError> = StringFormInput("")
) : Form {
    override val inputs = listOf(name, email)
}

class FormTest {

    @Test
    fun `isValid returns true when all FormInputs are valid`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("john@example.com")
        )
        assertTrue(form.isValid)
    }

    @Test
    fun `isValid returns false when any FormInput is invalid`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("")
        )
        assertFalse(form.isValid)
    }

    @Test
    fun `isNotValid returns true when any FormInput is invalid`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("")
        )
        assertTrue(form.isNotValid)
    }

    @Test
    fun `isNotValid returns false when all FormInputs are valid`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("john@example.com")
        )
        assertFalse(form.isNotValid)
    }

    @Test
    fun `isPristine returns true when all FormInputs are pristine`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("john@example.com")
        )
        assertTrue(form.isPristine)
    }

    @Test
    fun `isPristine returns false when any FormInput is not pristine`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe", isPristine = false),
            email = StringFormInput("john@example.com")
        )
        assertFalse(form.isPristine)
    }

    @Test
    fun `isNotPristine returns true when any FormInput is not pristine`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe", isPristine = false),
            email = StringFormInput("john@example.com")
        )
        assertTrue(form.isDirty)
    }

    @Test
    fun `isNotPristine returns false when all FormInputs are pristine`() {
        val form = SignupFormState(
            name = StringFormInput("John Doe"),
            email = StringFormInput("john@example.com")
        )
        assertFalse(form.isDirty)
    }
}
