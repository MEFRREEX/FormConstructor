package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.CustomForm;
import com.formconstructor.form.element.ElementIdentifiable;
import com.formconstructor.form.element.custom.validator.ValidationField;
import com.formconstructor.form.element.custom.validator.Validator;
import com.formconstructor.form.element.general.Divider;
import com.formconstructor.form.element.general.Header;
import com.formconstructor.form.handler.CustomFormHandler;
import com.formconstructor.form.element.ElementCustom;
import com.formconstructor.form.element.custom.Dropdown;
import com.formconstructor.form.element.custom.Input;
import com.formconstructor.form.element.general.Label;
import com.formconstructor.form.element.custom.Slider;
import com.formconstructor.form.element.custom.StepSlider;
import com.formconstructor.form.element.custom.Toggle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a response from a custom form submission.
 * Provides access to form elements and validation results.
 */
public class CustomFormResponse extends FormResponse<CustomFormHandler> {

    @Getter
    private final CustomForm form;

    private final List<ElementCustom> elements;

    /**
     * Creates a new custom form response.
     *
     * @param handler The handler to process this response
     * @param elements The list of form elements
     * @param form The original form instance
     */
    public CustomFormResponse(CustomFormHandler handler, List<ElementCustom> elements, CustomForm form) {
        super(handler, "");
        this.elements = elements;
        this.form = form;
    }

    /**
     * Checks if an element with the specified ID exists.
     *
     * @param elementId The element ID to check
     * @return true if element exists, false otherwise
     */
    public boolean containsId(String elementId) {
        return elements.stream().anyMatch(element ->
                element instanceof ElementIdentifiable elementIdentifiable &&
                        elementId.equals(elementIdentifiable.getElementId()));
    }

    /**
     * Gets a form element by index.
     *
     * @param index The element index
     * @return The element at specified index
     */
    public ElementCustom getElement(int index) {
        return elements.get(index);
    }

    /**
     * Gets a typed form element by index.
     *
     * @param <T> The element type
     * @param index The element index
     * @param clazz The element class
     * @return The typed element
     */
    public <T extends ElementCustom> T getElement(int index, Class<T> clazz) {
        return clazz.cast(this.getElement(index));
    }

    /**
     * Gets a form element by ID.
     *
     * @param elementId The element ID
     * @return The element with specified ID or null
     */
    public ElementCustom getElement(String elementId) {
        return elements.stream()
                .filter(element ->
                        element instanceof ElementIdentifiable elementIdentifiable &&
                        elementId.equals(elementIdentifiable.getElementId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets a typed form element by ID.
     *
     * @param <T> The element type
     * @param elementId The element ID
     * @param clazz The element class
     * @return The typed element or null
     */
    public <T extends ElementCustom> T getElement(String elementId, Class<T> clazz) {
        return clazz.cast(this.getElement(elementId));
    }

    /**
     * Gets all elements of specified type.
     *
     * @param <T> The element type
     * @param clazz The element class
     * @return List of matching elements
     */
    public <T extends ElementCustom> List<T> getElements(Class<T> clazz) {
        return elements.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }

    /**
     * Gets a Label element by index.
     *
     * @param index The label index
     * @return The Label element
     */
    public Label getLabel(int index) {
        return this.getElement(index, Label.class);
    }

    /**
     * Gets a Label element by ID.
     *
     * @param elementId The label ID
     * @return The Label element or null
     */
    public Label getLabel(String elementId) {
        return this.getElement(elementId, Label.class);
    }

    /**
     * Gets all Label elements.
     *
     * @return List of all Labels
     */
    public List<Label> getLabels() {
        return this.getElements(Label.class);
    }

    /**
     * Gets a Divider element by index.
     *
     * @param index The divider index
     * @return The Divider element
     */
    public Divider getDivider(int index) {
        return this.getElement(index, Divider.class);
    }

    /**
     * Gets a Divider element by ID.
     *
     * @param elementId The divider ID
     * @return The Divider element or null
     */
    public Divider getDivider(String elementId) {
        return this.getElement(elementId, Divider.class);
    }

    /**
     * Gets all Divider elements.
     *
     * @return List of all Dividers
     */
    public List<Divider> getDividers() {
        return this.getElements(Divider.class);
    }

    /**
     * Gets a Header element by index.
     *
     * @param index The header index
     * @return The Header element
     */
    public Header getHeader(int index) {
        return this.getElement(index, Header.class);
    }

    /**
     * Gets a Header element by ID.
     *
     * @param elementId The header ID
     * @return The Header element or null
     */
    public Header getHeader(String elementId) {
        return this.getElement(elementId, Header.class);
    }

    /**
     * Gets all Header elements.
     *
     * @return List of all Headers
     */
    public List<Header> getHeaders() {
        return this.getElements(Header.class);
    }

    /**
     * Gets an Input element by index.
     *
     * @param index The input index
     * @return The Input element
     */
    public Input getInput(int index) {
        return this.getElement(index, Input.class);
    }

    /**
     * Gets an Input element by ID.
     *
     * @param elementId The input ID
     * @return The Input element or null
     */
    public Input getInput(String elementId) {
        return this.getElement(elementId, Input.class);
    }

    /**
     * Gets all Input elements.
     *
     * @return List of all Inputs
     */
    public List<Input> getInputs() {
        return this.getElements(Input.class);
    }

    /**
     * Gets a Toggle element by index.
     *
     * @param index The toggle index
     * @return The Toggle element
     */
    public Toggle getToggle(int index) {
        return this.getElement(index, Toggle.class);
    }

    /**
     * Gets a Toggle element by ID.
     *
     * @param elementId The toggle ID
     * @return The Toggle element or null
     */
    public Toggle getToggle(String elementId) {
        return this.getElement(elementId, Toggle.class);
    }

    /**
     * Gets all Toggle elements.
     *
     * @return List of all Toggles
     */
    public List<Toggle> getToggles() {
        return this.getElements(Toggle.class);
    }

    /**
     * Gets a Slider element by index.
     *
     * @param index The slider index
     * @return The Slider element
     */
    public Slider getSlider(int index) {
        return this.getElement(index, Slider.class);
    }

    /**
     * Gets a Slider element by ID.
     *
     * @param elementId The slider ID
     * @return The Slider element or null
     */
    public Slider getSlider(String elementId) {
        return this.getElement(elementId, Slider.class);
    }

    /**
     * Gets all Slider elements.
     *
     * @return List of all Sliders
     */
    public List<Slider> getSliders() {
        return this.getElements(Slider.class);
    }

    /**
     * Gets a StepSlider element by index.
     *
     * @param index The step slider index
     * @return The StepSlider element
     */
    public StepSlider getStepSlider(int index) {
        return this.getElement(index, StepSlider.class);
    }

    /**
     * Gets a StepSlider element by ID.
     *
     * @param elementId The step slider ID
     * @return The StepSlider element or null
     */
    public StepSlider getStepSlider(String elementId) {
        return this.getElement(elementId, StepSlider.class);
    }

    /**
     * Gets all StepSlider elements.
     *
     * @return List of all StepSliders
     */
    public List<StepSlider> getStepSliders() {
        return this.getElements(StepSlider.class);
    }

    /**
     * Gets a Dropdown element by index.
     *
     * @param index The dropdown index
     * @return The Dropdown element
     */
    public Dropdown getDropdown(int index) {
        return this.getElement(index, Dropdown.class);
    }

    /**
     * Gets a Dropdown element by ID.
     *
     * @param elementId The dropdown ID
     * @return The Dropdown element or null
     */
    public Dropdown getDropdown(String elementId) {
        return this.getElement(elementId, Dropdown.class);
    }

    /**
     * Gets all Dropdown elements.
     *
     * @return List of all Dropdowns
     */
    public List<Dropdown> getDropdowns() {
        return this.getElements(Dropdown.class);
    }

    /**
     * Processes the form response for the player.
     *
     * @param player The player who submitted the form
     */
    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, this);
        }
    }

    /**
     * Checks if all form validations passed.
     *
     * @return True if all validations passed, false otherwise
     */
    public boolean isValidated() {
        return form.isValidated();
    }

    /**
     * Gets a list of validation error messages.
     *
     * @return List of validator names that failed
     */
    public List<String> getValidatorErrors() {
        List<String> errors = new ArrayList<>();

        elements.forEach(element -> {
            if (element instanceof ValidationField validationField) {
                for (Validator validator : validationField.getValidators()) {
                    if (!validator.isValidated()) {
                        errors.add(validator.getName());
                    }
                }
            }
        });

        return errors;
    }
}