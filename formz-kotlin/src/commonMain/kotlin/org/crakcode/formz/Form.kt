package org.crakcode.formz

/**
 * An interface that automatically handles validation of all
 * [FormInput]s present in the [inputs].
 */
public interface Form {
    /**
     * Returns all [FormInput] instances.
     *
     * Override this and give it all [FormInput]s in your class that should be
     * validated automatically.
     */
    public val inputs: List<FormInput<*, *>>

    /**
     * Returns true if all [FormInput] values are valid.
     */
    public val isValid: Boolean get() = inputs.validate()

    /**
     * Returns true if any of the [FormInput] values is not valid.
     */
    public val isNotValid: Boolean get() = !isValid

    /**
     * Returns true if all the [FormInput] values are pure.
     */
    public val isPure: Boolean get() = inputs.isPure()

    /**
     * Returns true if any of the [FormInput] values is modified.
     */
    public val isDirty: Boolean get() = !isPure
}

/**
 * Returns true if all the [FormInput]s are valid.
 */
public fun List<FormInput<*, *>>.validate(): Boolean = all { it.isValid }

/**
 * Returns true if all the [FormInput]s are pure.
 */
public fun List<FormInput<*, *>>.isPure(): Boolean = all { it.isPure }
