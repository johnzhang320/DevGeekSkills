package com.html.email.editor;

import java.util.ArrayList;
import java.util.List;
 
import com.ckeditor.CKEditorConfig;
import com.ckeditor.EventHandler;
import com.ckeditor.GlobalEventHandler;
/**
 *  
 * @author johnzhang
 * Toolbar Configure terms:
 * 
 * // Toolbar configuration generated automatically by the editor based on config.toolbarGroups.
config.toolbar = [
	{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
	{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
	{ name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
	'/',
	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'language' ] },
	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
	{ name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
	'/',
	{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
	{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
	{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
	{ name: 'others', items: [ '-' ] },
	{ name: 'about', items: [ 'About' ] }
];

// Toolbar groups configuration.
config.toolbarGroups = [
	{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
	{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
	{ name: 'forms' },
	'/',
	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
	{ name: 'links' },
	{ name: 'insert' },
	'/',
	{ name: 'styles' },
	{ name: 'colors' },
	{ name: 'tools' },
	{ name: 'others' },
	{ name: 'about' }
];
 */
public class MiddleSizeConfigurationHelper {
 
 
	public static CKEditorConfig createConfig() {
		CKEditorConfig config = new CKEditorConfig();
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> subList = new ArrayList<String>();
		/**
		 *  Line 1 toolbar configure
		 */
		subList.add("Source");
		//subList.add("Save");
		subList.add("NewPage");
		subList.add("Preview");
		subList.add("Print");
		subList.add("Templates");
		subList.add("Cut");
		subList.add("Copy");
		subList.add("clipboard");
		subList.add("Paste");
		subList.add("PasteText");
		subList.add("PasteFromWord");
		subList.add("Undo");
		subList.add("Redo");
		subList.add("Find");
		subList.add("Replace");
		subList.add("SelectAll");
		subList.add("spellchecker");
		subList.add("/");
		/**
		 *  Line 2 toolbar configure
		 */
	
		subList.add("Bold");
		subList.add("Italic");
		subList.add("UnderLine");
		subList.add("Strike");
		subList.add("Form");
		subList.add("Table");
		subList.add("Checkbox");
		subList.add("Textarea");
		subList.add("Select");
		subList.add("Button");
		subList.add("ImageButton");
		subList.add("NumberedList");
		subList.add("BulletedList");
		 
		subList.add("Image");
		subList.add("Flash");
		 
		subList.add("Subscript");
		subList.add("Superscript");
		subList.add("Link");
		subList.add("Unlink");
		subList.add("Anchor");
		subList.add("Iframe");
		subList.add("TextColor");
		subList.add("BGColor");
		 
		
		subList.add("/");
		/**
		 *  Line 3 toolbar configure
		 */
		
		subList.add("Styles");
		subList.add("Format");
		subList.add("Font");
		subList.add("FontSize");
		subList.add("TextColor");
		subList.add("BGColor");
		
		list.add(subList);
		config.addConfigValue("toolbar", list);
		config.addConfigValue("width","740");		 
		config.addConfigValue("height","500");
	  
		return config;
	}
 
	public static EventHandler createEventHandlers() {
		EventHandler handler = new EventHandler();
		//handler.addEventHandler("save","function (ev) {  alert(\"click on Save icon on toolbar: \" + ev.data.name); }");
		handler.addEventHandler("instanceReady","function (ev) { alert(\"Loaded: \" + ev.editor.name); }");
		return handler;
	}
 
	public static GlobalEventHandler createGlobalEventHandlers() {
		GlobalEventHandler handler = new GlobalEventHandler();
		handler.addEventHandler("save","function (ev) {  alert(\"click on Save icon on toolbar: \" + ev.data.name); }");
		return handler;
	}
	
	public static GlobalEventHandler createGlobalEventHandlers2() {
		GlobalEventHandler handler = new GlobalEventHandler();
		handler.addEventHandler("dialogDefinition","function (ev) {  alert(\"Loading dialog window: \" + ev.data.name); }");
		return handler;
	}
}