package org.dreamerslab.formz

/**
 * A [FormInput] represents the value of a single form input field.
 * It contains information about the [value] as well as validity.
 *
 * @param value The value of the given [FormInput].
 * @param isPristine If the [FormInput] has not been modified.
 *
 * ```kotlin
 * // PasswordField.kt
 *
 * enum class PasswordError {
 *     Empty,
 *     TooShort
 * }
 *
 * // Password Input Field
 * class Password(
 *     value: String,
 *     isPure: Boolean = true
 * ) : FormInput<String, PasswordError>(value, isPure) {
 *     override fun validator(value: String): PasswordError? = when {
 *         value.isBlank() -> PasswordError.Empty
 *         value.length < 8 -> PasswordError.TooShort
 *         else -> null
 *     }
 * }
 * ```
 *
 * ```kotlin
 * // ViewModel.kt
 *
 * data class LoginFormState(
 *     val email: Email = Email(""),
 *     val password: Password = Password(""),
 *     val submissionStatus: FormSubmissionStatus = FormSubmissionStatus.Initial
 * ) : Form {
 *     override val inputs = listOf(email, password)
 * }
 *
 * class ViewModel {
 *     private val _state = MutableStateFlow(LoginFormState())
 *     val state: StateFlow<LoginFormState> = _state
 *
 *     fun updatePassword(password: String) {
 *         viewModelScope.launch {
 *             // Set `isPure` to false when updating field value to indicate
 *             // that field has been modified.
 *             _state.update { it.copy(password = Password(password, false)) }
 *         }
 *     }
 * }
 * ```
 */
public abstract class FormInput<T, E>(
    public val value: T,
    public val isPristine: Boolean = true
) {
    /**
     * A function that must return a validation error if the provided
     * [value] is invalid and `null` otherwise.
     */
    public abstract fun validator(value: T): E?

    /**
     * Whether the [FormInput] value is valid according to the
     * overridden `validator`.
     *
     * Returns `true` if `validator` returns `null` for the
     * current [FormInput] value and `false` otherwise.
     */
    public open val isValid: Boolean get() = validator(value) == null

    /**
     * Whether the [FormInput] value is not valid.
     * A value is invalid when the overridden `validator`
     * returns an error (non-null value).
     */
    public val isNotValid: Boolean get() = !isValid

    /**
     * Returns a validation error if the [FormInput] is invalid.
     * Returns `null` if the [FormInput] is valid.
     */
    public open val error: E? get() = validator(value)

    /**
     * The error to display if the [FormInput] value
     * is not valid and has been modified.
     */
    public open val displayError: E? get() = if (isPristine) null else error

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FormInput<*, *>) return false

        if (value != other.value) return false
        if (isPristine != other.isPristine) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + isPristine.hashCode()
        return result
    }
}

/**
 * A [FormInput] that caches the [error] result of the [validator].
 * Use [CachedFormInput] when implementations that make expensive computations are
 * used, such as those involving regular expressions.
 *
 * @param value The value of the given [FormInput].
 * @param isPristine If the [FormInput] has not been modified.
 *
 * ```kotlin
 * enum class EmailError {
 *     Empty,
 *     Invalid
 * }
 *
 * class Email(
 *     value: String,
 *     isPure: Boolean = true
 * ) : CachedFormInput<String, EmailError>(value, isPure) {
 *     override fun validator(value: String): EmailError? = when {
 *         value.isBlank() -> EmailError.Empty
 *         // Using Regex to validate email address
 *         !PatternsCompat.EMAIL_ADDRESS.matcher(value).matches() -> EmailError.Invalid
 *         else -> null
 *     }
 * }
 * ```
 */
public abstract class CachedFormInput<T, E>(
    value: T,
    isPristine: Boolean = true
) : FormInput<T, E>(value, isPristine) {
    private val cachedError by lazy { validator(value) }
    override val error: E? get() = cachedError
    override val isValid: Boolean get() = cachedError == null
}
