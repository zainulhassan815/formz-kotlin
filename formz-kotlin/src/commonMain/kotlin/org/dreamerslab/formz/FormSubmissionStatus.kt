package org.dreamerslab.formz

/**
 * Enum representing the submission status of a form.
 */
public enum class FormSubmissionStatus {
    /**
     * The form has not yet been submitted.
     */
    Initial,

    /**
     * The form is in the process of being submitted.
     */
    InProgress,

    /**
     * The form has been submitted successfully.
     */
    Success,

    /**
     * The form submission failed.
     */
    Failure,

    /**
     * The form submission has been canceled.
     */
    Canceled
}

/**
 * Indicates whether the form has not yet been submitted.
 */
public val FormSubmissionStatus.isInitial: Boolean
    get() = this == FormSubmissionStatus.Initial

/**
 * Indicates whether the form is in the process of being submitted.
 */
public val FormSubmissionStatus.isInProgress: Boolean
    get() = this == FormSubmissionStatus.InProgress

/**
 * Indicates whether the form has been submitted successfully.
 */
public val FormSubmissionStatus.isSuccess: Boolean
    get() = this == FormSubmissionStatus.Success

/**
 * Indicates whether the form submission failed.
 */
public val FormSubmissionStatus.isFailure: Boolean
    get() = this == FormSubmissionStatus.Failure

/**
 * Indicates whether the form submission has been canceled.
 */
public val FormSubmissionStatus.isCanceled: Boolean
    get() = this == FormSubmissionStatus.Canceled

/**
 * Indicates whether the form is either in progress or has been submitted
 * successfully.
 *
 * This is useful for showing a loading indicator or disabling the submit
 * button to prevent duplicate submissions.
 */
public val FormSubmissionStatus.isInProgressOrSuccess: Boolean
    get() = isInProgress || isSuccess
