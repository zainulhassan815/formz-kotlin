package org.crakcode.formz

enum class ValidationError {
    Blank
}

class StringFormInput(
    value: String,
    isPure: Boolean = true
) : FormInput<String, ValidationError>(value, isPure) {
    override fun validator(value: String): ValidationError? = when {
        value.isBlank() -> ValidationError.Blank
        else -> null
    }
}

class CachedStringFormInput(
    value: String,
    isPure: Boolean = true
) : CachedFormInput<String, ValidationError>(value, isPure) {
    override fun validator(value: String): ValidationError? = when {
        value.isBlank() -> ValidationError.Blank
        else -> null
    }
}
