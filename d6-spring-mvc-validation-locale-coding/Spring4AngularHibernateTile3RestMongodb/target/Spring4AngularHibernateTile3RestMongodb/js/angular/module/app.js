/**
 * Create AngularJS Module

	Modules are the most important part of AngularJS application. A module in AngularJS can be
    thought of as packages in Java. 
    
     AngularJS can group together certain functionalities/Javascript under a single module.

    A module can define it’s own controllers, services, filter, directives, etc which will be 
    accessible throughout the module.
    A module can depend on other modules.
    A module can be used by AngularJS to bootstrap an application. By passing the module name to 
    ng-app directive, we can tell AngularJS to load this module as the main entry point for the application
	
	A module can be used by AngularJS to bootstrap an application. By passing the module name to ng-app 
	directive, we can tell AngularJS to load this module as the main entry point for the application. 
	Below is our module. To know more in details about AngularJS Modules
	
	define a Module with dependencies

	Below snippet defines a module named myApp, which depends on two other modules: ui.bootstrap & ngResource:
	
	angular.module('myapp', ['ui.bootstrap', 'ngResource']);
	
	It means all the functionalities available in ‘ui.bootstrap’ & ‘ngResource’, will be accessible throughout 
	module ‘myApp’.
	
	Load an existing Module
	
	If we just want to load an existing module defined elsewhere, call module function with only one parameter
    which is name of module.
    
	angular.module('myApp');
	
 */
'use strict';
var dmarcApp = angular.module('dmarcApp',[])    // First Parameter is Module name, second parameter is dependence