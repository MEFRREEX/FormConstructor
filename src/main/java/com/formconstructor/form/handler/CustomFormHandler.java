package com.formconstructor.form.handler;

import cn.nukkit.Player;
import com.formconstructor.form.response.CustomFormResponse;

/**
 * Handler for processing custom form submissions.
 * Provides access to all form elements and validation results.
 */
@FunctionalInterface
public interface CustomFormHandler extends FormHandler {

    /**
     * Called when a player submits a custom form.
     *
     * @param player The player who submitted the form
     * @param response Contains all form elements and validation data
     */
    void handle(Player player, CustomFormResponse response);
}