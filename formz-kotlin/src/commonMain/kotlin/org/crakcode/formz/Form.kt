package org.crakcode.formz

/**
 * An interface that automatically handles validation of all
 * [FormInput]s present in the [inputs].
 *
 * ```kotlin
 * // Sample Usage
 * data class SignupFormState(
 *     val name: Name = Name(""),
 *     val email: Email = Email(""),
 *     val password: Password = Password(""),
 *     val submissionStatus: FormSubmissionStatus = FormSubmissionStatus.Initial
 * ) : Form {
 *     override val inputs = listOf(name, email, password)
 * }
 * ```
 */
public interface Form {
    /**
     * Returns all [FormInput] instances.
     *
     * Implement this and give it all [FormInput]s in your class that should be
     * validated automatically.
     */
    public val inputs: List<FormInput<*, *>>

    /**
     * Returns true if all [FormInput] values are valid.
     */
    public val isValid: Boolean get() = inputs.valid()

    /**
     * Returns true if any of the [FormInput] values is not valid.
     */
    public val isNotValid: Boolean get() = !isValid

    /**
     * Returns true if all the [FormInput] values have not been modified.
     */
    public val isPristine: Boolean get() = inputs.isPristine()

    /**
     * Returns true if any of the [FormInput] values is modified.
     */
    public val isDirty: Boolean get() = !isPristine
}

/**
 * Returns true if all the [FormInput]s are valid.
 */
public fun List<FormInput<*, *>>.valid(): Boolean = all { it.isValid }

/**
 * Returns true if all the [FormInput]s have not been modified.
 */
public fun List<FormInput<*, *>>.isPristine(): Boolean = all { it.isPristine }
