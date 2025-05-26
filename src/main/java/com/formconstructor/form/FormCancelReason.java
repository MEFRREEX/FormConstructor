package com.formconstructor.form;

/**
 * Represents the reason for closing the form.
 */
public enum FormCancelReason {
    /**
     * Form closed by user.
     */
    USER_CLOSED,
    /**
     * Form is sent when the player is on a loading screen.
     */
    USER_BUSY
}
