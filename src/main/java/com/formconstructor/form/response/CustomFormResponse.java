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

public class CustomFormResponse extends FormResponse<CustomFormHandler> {

    @Getter
    private final CustomForm form;
    private final List<ElementCustom> elements;

    public CustomFormResponse(CustomFormHandler handler, List<ElementCustom> elements, CustomForm form) {
        super(handler, "");
        this.elements = elements;
        this.form = form;
    }

    /**
     * Check if there is an element with id 
     */
    public boolean containsId(String elementId) {
        return elements.stream().anyMatch(element ->
                element instanceof ElementIdentifiable elementIdentifiable &&
                elementId.equals(elementIdentifiable.getElementId()));
    }

    /**
     * Get element by index
     * @param index Element index
     * @return CustomElement
     */
    public ElementCustom getElement(int index) {
        return elements.get(index);
    }

    public <T extends ElementCustom> T getElement(int index, Class<T> clazz) {
        return clazz.cast(this.getElement(index));
    }

    /**
     * Get element by element id
     * @param elementId Element identifier
     * @return CustomElement
     */
    public ElementCustom getElement(String elementId) {
        return elements.stream()
            .filter(element ->
                    element instanceof ElementIdentifiable elementIdentifiable &&
                    elementId.equals(elementIdentifiable.getElementId()))
            .findFirst()
            .orElse(null);
    }

    public <T extends ElementCustom> T getElement(String elementId, Class<T> clazz) {
        return clazz.cast(this.getElement(elementId));
    }

    public <T extends ElementCustom> List<T> getElements(Class<T> clazz) {
        return elements.stream()
            .filter(clazz::isInstance)
            .map(clazz::cast)
            .collect(Collectors.toList());
    }

    /**
     * Get Label by index
     * @param index Label index
     * @return Label
     */
    public Label getLabel(int index) {
        return this.getElement(index, Label.class);
    }

    /**
     * Get Label by element id
     * @param elementId Label identifier
     * @return Label
     */
    public Label getLabel(String elementId) {
        return this.getElement(elementId, Label.class);
    }

    /**
     * Get all labels
     * @return List<Label>
     */
    public List<Label> getLabels() {
        return this.getElements(Label.class);
    }

    /**
     * Get Divider by index
     * @param index Divider index
     * @return Divider
     */
    public Divider getDivider(int index) {
        return this.getElement(index, Divider.class);
    }

    /**
     * Get Divider by element id
     * @param elementId Divider identifier
     * @return Divider
     */
    public Divider getDivider(String elementId) {
        return this.getElement(elementId, Divider.class);
    }

    /**
     * Get all dividers
     * @return List<Divider>
     */
    public List<Divider> getDividers() {
        return this.getElements(Divider.class);
    }

    /**
     * Get Divider by index
     * @param index Divider index
     * @return Divider
     */
    public Header getHeader(int index) {
        return this.getElement(index, Header.class);
    }

    /**
     * Get Divider by element id
     * @param elementId Divider identifier
     * @return Divider
     */
    public Header getHeader(String elementId) {
        return this.getElement(elementId, Header.class);
    }

    /**
     * Get all headers
     * @return List<Header>
     */
    public List<Header> getHeaders() {
        return this.getElements(Header.class);
    }

    /**
     * Get Input by index
     * @param index Input index
     * @return Input
     */
    public Input getInput(int index) {
        return this.getElement(index, Input.class);
    }

    /**
     * Get Input by element id
     * @param elementId Input identifier
     * @return Input
     */
    public Input getInput(String elementId) {
        return this.getElement(elementId, Input.class);
    }

    /**
     * Get all inputs
     * @return List<Input>
     */
    public List<Input> getInputs() {
        return this.getElements(Input.class);
    }

    /**
     * Get Toggle by index
     * @param index Toggle index
     * @return Toggle
     */
    public Toggle getToggle(int index) {
        return this.getElement(index, Toggle.class);
    }

    /**
     * Get Toggle by element id
     * @param elementId Toggle identifier
     * @return Toggle
     */
    public Toggle getToggle(String elementId) {
        return this.getElement(elementId, Toggle.class);
    }

    /**
     * Get all toggles
     * @return List<Toggle>
     */
    public List<Toggle> getToggles() {
        return this.getElements(Toggle.class);
    }

    /**
     * Get Slider by index
     * @param index Slider index
     * @return Slider
     */
    public Slider getSlider(int index) {
        return this.getElement(index, Slider.class);
    }

    /**
     * Get Slider by element id
     * @param elementId Slider identifier
     * @return Slider
     */
    public Slider getSlider(String elementId) {
        return this.getElement(elementId, Slider.class);
    }

    /**
     * Get all sliders
     * @return List<Slider>
     */
    public List<Slider> getSliders() {
        return this.getElements(Slider.class);
    }

    /**
     * Get StepSlider by index
     * @param index StepSlider index
     * @return StepSlider
     */
    public StepSlider getStepSlider(int index) {
        return this.getElement(index, StepSlider.class);
    }

    /**
     * Get StepSlider by element id
     * @param elementId StepSlider identifier
     * @return StepSlider
     */
    public StepSlider getStepSlider(String elementId) {
        return this.getElement(elementId, StepSlider.class);
    }

    /**
     * Get all step sliders
     * @return List<StepSlider>
     */
    public List<StepSlider> getStepSliders() {
        return this.getElements(StepSlider.class);
    }

    /**
     * Get Dropdown by index
     * @param index Dropdown index
     * @return Dropdown
     */
    public Dropdown getDropdown(int index) {
        return this.getElement(index, Dropdown.class);
    }

    /**
     * Get Dropdown by element id
     * @param elementId Dropdown identifier
     * @return Dropdown
     */
    public Dropdown getDropdown(String elementId) {
        return this.getElement(elementId, Dropdown.class);
    }

    /**
     * Get all dropdown
     * @return List<Dropdown>
     */
    public List<Dropdown> getDropdowns() {
        return this.getElements(Dropdown.class);
    }

    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, this);
        }
    }
    
    public boolean isValidated() {
        return form.isValidated();
    }

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