package org.crakcode.formz

enum class ValidationError {
    Blank
}

class StringFormInput(
    value: String,
    isPristine: Boolean = true
) : FormInput<String, ValidationError>(value, isPristine) {
    override fun validator(value: String): ValidationError? = when {
        value.isBlank() -> ValidationError.Blank
        else -> null
    }
}

class CachedStringFormInput(
    value: String,
    isPristine: Boolean = true
) : CachedFormInput<String, ValidationError>(value, isPristine) {
    override fun validator(value: String): ValidationError? = when {
        value.isBlank() -> ValidationError.Blank
        else -> null
    }
}
