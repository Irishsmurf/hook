# Hook

[![Coverage Status](https://coveralls.io/repos/jangasoft/hook/badge.png?branch=master)](https://coveralls.io/r/jangasoft/hook?branch=master)

Hook is a plugin framework and platform. It enables applications to be designed in such a way that they can be extended via plugins (also called, addons, extensions, and others). The latest stable release is 0.0.1. If you use Maven, the best way to obtain it is adding the dependency below to your project. This is picked up from the Central Repository - which maven automatically reaches out to - so there's no need to add any repository. Java 6 or higher is required.

```xml
<dependency>
	<groupId>cf.janga</groupId>
	<artifactId>hook</artifactId>
	<version>0.0.1</version>
</dependency>
```

# Documentation

This documentation is a work-in-progress. In time, I'll be adding more stuff in here, so bear with me.

## The Basics

As said before, Hook provides the basic building blocks to enable an application to be extended via plugins. Everything in Hook revolves around a few basic concepts. These are listed and described below.

* Host application: an application that's been built to support plugins. A host application has one or more extension points;
* Extension point: a part of the application that can be extended via plugins;
* Plugin: an isolated component that extends the functionalities of a specific host application by providing extensions;
* Extension: some new functionality provided by a plugin and which hooks to a specific extension point in a host application.

### Plugin Loading and Lifecycle

The core of Hook's plugin platform is the plugin loader and the plugin registry. The plugin loader - as the name says - loads plugins into the classpath and initializes all of its extensions. The loader uses the plugin registry to keep track of every plugin that's been attempted to be loaded. What this means is that, even a plugin fails to load - for whatever reason - the registry will hold as much information as is available about the plugin, along with, obviously, all information about those that were successfuly loaded. This allows the application to display information about the plugins it's currently running with.

A more in depth view of what happens under the hood when the loader is loading a plugin is given below:

1. The plugin's jar file is loaded and all classes in it put into the classpath;
2. The main plugin class is instantiated;
3. The list of all extensions provided by the plugin is instantiated;
4. For each extension, the plugin loader finds the extension point of the hosting application responsible for it. The plugin loader then asks the extension point to handle the extension, then it initializes the extension point. At this point it is provided with the API that the hosting application provides to extensions. Failing any of these steps for any of the extensions will fail the loading of the plugin altogether;
5. When the plugin loader has finished going through all extensions, it registers the end result of the operation (i.e., whether the plugin was successfully loaded or not, information about the plugin, etc) against the plugin registry.

### The Plugin Archive

Having said the above, plugins are packaged in jar files. We'll use the term plugin archive to refer to these jar files. We've tried to keep to a minimum anything that a plugin developer would be required to include in the plugin archive. With that in mind, there are only two things that any plugin archive should include.

* A class that implements the ```Plugin``` interface. This is the entry point for the plugin loader in terms of the plugin's lifecycle, as explained above;
* An entry in the jar's manifest, pointing to class above, as below:

```manifest
Plugin-class: com.somecompany.plugin.FooPlugin
```

## Defining a Plugin-based Application With Hook

TODO

### Extensions

### The Core API

## Writing Plugins

Most of the things you'll need to do around developing a plugin with Hook involves actual coding instead of cofiguration files. Because, as you'll see, the plugin is essentially a container that holds some information about its functionalities along with the extensions that actually implement those functionalities, we didn't want to see developers having to keep configuration files in synch with code. Instead, most of the stuff Hook needs from a plugin, it'll simply ask you - the plugin developer - to write the code for it. If you change an extension - e.g., the way it's instantiated, the package - chances are you'll see a problem right away.

### Writing the Basic Plugin Code

As you'll see, the ```Plugin``` interface is Hook's entry point to the plugin's functionality and basic information. From the methods in there, the most important one is probably ```getExtensions```. In this method the plugin developer is supposed to return one instance of each of the extensions that the plugin provides. Hook's plugin platform, explained later, will pick up those extensions and make sure they're hooked to the correct extension points. If you're wondering, it is possible to return multiple instances of the same extension. Whereas there's probably little usage for this - most likely none - Hook will certainly not complain about it. If your extension points, however, cannot handle multiple instances of the same extension being passed to it, Hook will catch the error and the plugin will obviously not be initialized.

TODO: Continue
