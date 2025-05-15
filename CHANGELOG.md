# 3.0.0
1. Removed useless [constructors](https://github.com/MEFRREEX/FormConstructor/commit/a5ebafd0ae5fc3f7c9fb7a21d036b05662543814) and [methods](https://github.com/MEFRREEX/FormConstructor/commit/d080f3d4906064d439e0c6597dc6e5e6f1cb951a) from CustomForm, ModalForm
2. Removed dependence on Nukkit forms + added FormService class
3. New form elements (Header & Divider)
4. Removed useless method `Element#getIndex()`
5. Added `FormService#closeForms(Player player)` method
6. Moved `PlayerFormCloseEvent` and `PlayerFormSendEvent` event classes
7. Added method `ClosableForm#setCloseHandler(CloseReasonFormHandler closeHandler)`
8. Added method `Form#sendUpdate(Player player)` for form update
9. Added method `CustomForm#setSubmitButton(String submitButton)` to change submit button text
10. Added support for 1.21.70x and 1.21.80x

# 2.0.3
1. Renamed ButtonImage to ImageData
2. Added method `setImage(Image image)` in Button class

# 2.0.2
1. Fixed Input#setDefaultValue method

# 2.0.1
1. Changes in FormElement#setName method

# 2.0.0

General changes:
1. Changed package from `ru.contentforge.formconstructor` to `com.formconstructor`
2. Java 17 is now used as the main version
3. All public and protected fields changed to private and added getters and setters
4. `PlayerFormSendEvent` and `PlayerFormCloseEvent` now extend `FormEvent`
5. Added Javadoc

Changes in elements:
1. All elements for simple form and custom form are allocated to the relevant packages
2. `ImageButton` class renamed to `ButtonImage`
3. `CustomFormElement` class renamed to `CustomElement`
4. Added `ElementType` enum. Now ElementType is used to specify the element type
5. Added `Slider` element
6. `InputBuilder` class deleted. Now the Input is created from the same Input class
7. Added `setImage(ImageType, String)` and `onClick(SimpleFormHandler)` methods to Button element
8. Renamed the `getText()` method to `getName()` in the SelectableElement class
9. Renamed the `addElement()` and `addElements()` method to `addStep()` and `addSteps()` in the StepSlider class

Changes in forms:
1. Added `FormType` enum. Now FormType is used to specify the form type
2. Removed `addButton(SimpleFormHandler)` method from SimpleForm class
3. Added `addButton(String)` method to SimpleForm class
4. Added `addButton(String, ImageType, String)` method to SimpleForm class
5. Removed `HashSet<String> containsId` field from CustomForm class

Changes in responses:
1. Response class renamed to FormResponse
2. Renamed the `get(int)`, `get(String)`, and `get(String, Class)` methods in CustomFormResponse class to `getElement`
3. Renamed the `get(Class)` method in CustomFormResponse class to `getElements`
4. Added `getSlider(int)`, `getSlider(String)` and `getSliders()` methods to CustomFormResponse class
5. CustomFormResponse class constructor changed from `CustomFormResponse(CustomFormHandler, ArrayList<CustomElement>, HashSet<String>, CustomForm)` to `CustomFormResponse(CustomFormHandler, List<CustomElement>, CustomForm)`

# 1.0.4
1. Added `PlayerFormSendEvent` class
2. Added `PlayerFormCloseEvent` class
3. Added `send(Player player, boolean async)` method to Form class
4. `ModalForm` class now extends `ClosableForm` class
5. Event handler moved from `FormConstructor` class to `FormListener` class
