# Formz-Kotlin

Formz-Kotlin is a Kotlin Multiplatform library for form representation and validation, inspired by the Dart `Formz` library. It provides a structured and reusable way to manage form state and validation in your Kotlin projects.

Formz-Kotlin addresses the following challenges:

*   **State Management:** It simplifies the management of form state, including input values, validation errors, and submission status.
*   **Validation:** It provides a flexible way to define validation rules for each form input.
*   **Reusability:** It encourages the creation of reusable form input components.
*   **Multiplatform:** It is designed to work seamlessly across different platforms, including Android, iOS, and more.

## Usage Guide

### Dependency

To use Formz-Kotlin in your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
implementation("io.github.zainulhassan815:formz:1.0.1")
```

Let's walk through creating a simple login form with email and password fields.

### 1. Define Form Inputs

First, define the validation errors and the `FormInput` classes for email and password.

**Email**

```kotlin
enum class EmailError {
    Empty
}

class Email(value: String) : CachedFormInput<String, EmailError>(value) {
    override fun validator(value: String): EmailError? = when {
        value.isBlank() -> EmailError.Empty
        else -> null
    }
}
```

**Password**

```kotlin
enum class PasswordError {
    Empty,
    TooShort
}

class Password(value: String) : FormInput<String, PasswordError>(value) {
    override fun validator(value: String): PasswordError? = when {
        value.isBlank() -> PasswordError.Empty
        value.length < 8 -> PasswordError.TooShort
        else -> null
    }
}
```

### 2. Define the Form

Next, create a data class that represents the state of your login form. This class should implement the `Form` interface.

```kotlin
data class LoginFormState(
    val email: Email = Email(""),
    val password: Password = Password(""),
    val submissionStatus: FormSubmissionStatus = FormSubmissionStatus.Initial
) : Form {
    override val inputs = listOf(email, password)
}
```

### 3. Use the Form in your ViewModel

Now, you can use the `LoginFormState` in your `ViewModel` to manage the form's state.

```kotlin
class ViewModel {
    private val _state = MutableStateFlow(LoginFormState())
    val state: StateFlow<LoginFormState> = _state

    fun updatePassword(password: String) {
        viewModelScope.launch {
            // Set `isPristine` to false when updating field value to indicate
            // that field has been modified.
            _state.update { it.copy(password = Password(password, false)) }
        }
    }

    fun onLogin() {
        val form = _state.value
        if (form.isValid) {
            // Handle successful login
        } else {
            // Handle invalid form
        }
    }
}
```

## Contribution

Contributions are welcome! If you have any ideas, suggestions, or issues, please open an issue or create a pull request on the GitHub repository.

## License

Formz-Kotlin is licensed under the [Apache License 2.0](LICENSE).
