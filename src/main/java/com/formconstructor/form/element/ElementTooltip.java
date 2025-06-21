package com.formconstructor.form.element;

/**
 * This class indicates that the element can contain a tooltip.
 */
public interface ElementTooltip {

    /**
     * Get element tooltip.
     *
     * @return Tooltip
     */
    String getTooltip();

    /**
     * Set element tooltip.
     *
     * @param tooltip Tooltip to set
     */
    Element setTooltip(String tooltip);
}
