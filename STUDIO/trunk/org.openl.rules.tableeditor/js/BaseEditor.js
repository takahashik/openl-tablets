/**
 * Base class for Editors.
 * If you need to create your own editor just override methods of this class.
 *
 * @requires Prototype v1.6.1+ library
 *
 * @author Andrey Naumenko
 */

var BaseEditor = Class.create({

    tableEditor: null,
    parentElement: null,
    input: null,
    initialValue: null,
    stoppedEvents: null,
    focus: null,
    style: null,

    /**
     * Constructor.
     * Generally editor constructor performs the following steps:
     *   1. Saves initial cell value into initialValue variable
     *   2. Creates an HTML editor control (e.g. HTMLInputElement) and sets its value
     */
    initialize: function(tableEditor, parentId, params, initialValue, focus, style) {
        if (parentId) {
            this.tableEditor = tableEditor;
            this.parentElement = $(parentId);

            this.style = style;

            // Save initial value
            this.initialValue = initialValue ? initialValue
                    : AjaxHelper.unescapeHTML(this.parentElement.innerHTML.replace(/<br>/ig, "\n")).strip();

            this.editor_initialize(params);
            this.input.id = this.getId();
            this.focus = (focus && focus == true) ? focus : '';
            this.show(this.initialValue);
        }
    },

    /**
     *  Editor specific constructor.
     *  Typically HTML node is created and possible some events handlers are registered.
     */
    editor_initialize: Prototype.emptyFunction,

    /**
     * Obtains current value from HTML editor control.
     */
    getValue: function() {
        var input = this.getInputElement();
        return input ? AjaxHelper.getInputValue(input).toString().replace(/\u00A0/g, ' ') : null;
    },

    getDisplayValue: function() {
        var value = this.isCancelled() ? this.initialValue : this.getValue();
        if (!value.strip()) {
            value = "&nbsp";
        } else {
            value = value.escapeHTML().replace(/\n/g, "<br/>");
        }
        return value;
    },

    /**
     * Is responsible for making editor visible and active.
     * In most cases it is not needed to be overridden.
     */
    show: function(value) {
        if (this.input) {
            this.parentElement.innerHTML = "";
            this.parentElement.appendChild(this.input);
            AjaxHelper.setInputValue(this.getInputElement(), value);
            if (this.focus) {
                this.input.focus();
            }
        }
    },

    /**
     * Returns if the editing was cancelled.
     */
    isCancelled : function() {
        return this.initialValue == this.getValue();
    },

    switchTo: function(editorName) {
        this.tableEditor.switchEditor(editorName);
    },
   
    doSwitching: function(newEditor) {
        var value = this.isCancelled() ? this.initialValue : this.getValue();
        newEditor.tableEditor = this.tableEditor;
        newEditor.parentElement = this.parentElement;
        newEditor.initialValue = this.initialValue;
        newEditor.style = this.style;
        newEditor.focus = true;

        this.isCancelled = BaseEditor.T;
        this.destroy();

        newEditor.editor_initialize();
        newEditor.show(value);
    },

    /**
     * Can be overridden in editors to clean up resources.
     */
    destroy: Prototype.emptyFunction,

    getId: function() {
        return '_' + this.parentElement.id;
    },

   /**
     * Notifies table editor that editing is finished.
     */
    doneEdit: function() {
        this.tableEditor.setCellValue();
    },

    /**
     * Notifies table editor that editing is finished and canceled.
     */
    cancelEdit: function() {
        this.isCancelled = BaseEditor.T;
        this.doneEdit();
    },

    /**
     *  Returns HTML element which is actually main input element for this editor.
     */
    getInputElement: function() {
        return this.input;
    },

    is: function(element) {
        return element == this.getInputElement();
    }

});

BaseEditor.T = function() {
    return true;
}

BaseEditor.isTableEditorExists = function() {
    return typeof TableEditor != 'undefined';
}
