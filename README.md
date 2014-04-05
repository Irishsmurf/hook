# Hook [![Build Status](https://travis-ci.org/jangasoft/hook.svg?branch=master)](https://travis-ci.org/jangasoft/hook) [![Coverage Status](https://coveralls.io/repos/jangasoft/hook/badge.png?branch=master)](https://coveralls.io/r/jangasoft/hook?branch=master) [![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/jangasoft/hook/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

This documentation is a work-in-progress. In time, I'll be adding more stuff in here, so bear with me.

### Overview

Hook is a Java plugin framework. It's based on a few simple concepts:
* Host application: an application that's been built to support plugins. A host application has one or more extension points;
* Extension point: a part of the application that can be extended via plugins;
* Plugin: an isolated component that extends the functionalities of a specific host application by providing extensions;
* Extension: some new functionality provided by a plugin and which hooks to a specific extension point in a host application.

### Getting Hook

The latest stable release is 0.0.1. If you use Maven, the best way to obtain it is adding the dependency below to your project. This is picked up from the Central Repository - which maven automatically reaches out to - so there's no need to add any repository.

```xml
<dependency>
	<groupId>cf.janga</groupId>
	<artifactId>hook</artifactId>
	<version>0.0.1</version>
</dependency>
```

### Platform and Plugin Lifecycle

Hook's platform is the entry point for applications to load plugins into themselves. It is comprised of the plugin loader (which we simply call platform at the code level, since it's the entry point for developers) and the plugin registry. The plugin loader - as the name says - loads plugins into the classpath and initializes all of its extensions. The loader uses the plugin registry to keep track of every plugin that's been attempted to be loaded. What this means is that, even a plugin fails to load - for whatever reason - the registry will hold as much information as is available about the plugin, along with, obviously, all information about those that were successfuly loaded. This allows the application to display runtime information about the plugins it's currently running with.

TODO: Lifecyclr

### Writing your Plugins with Hook

Most of the things you'll need to do around developing a plugin with Hook involves actual coding instead of cofiguration files. Because, as you'll see, the plugin is essentially a container that holds some information about its functionalities along with the extensions that actually implement those functionalities, we didn't want to see developers having to keep configuration files in synch with code. Instead, most of the stuff Hook needs from a plugin, it'll simply ask you - the plugin developer - to write the code for it. If you change an extension - e.g., the way it's instantiated, the package - chances are you'll see a problem right away.

#### The Plugin Archive

Having said the above, plugins are packaged in jar archives, with a couple of contents required. We'll use the term plugin archive to refer to a jar file that has these required contents, which are:
* A class that implements the ```Plugin``` interface;
* An entry in the jar's manifest, pointing to said class, as below:

```manifest
Plugin-class: com.somecompany.plugin.FooPlugin
```

#### Writing the Basic Plugin Code

As you'll see, the ```Plugin``` interface is Hook's entry point to the plugin's functionality and basic information. From the methods in there, the most important one is probably ```getExtensions```. In this method the plugin developer is supposed to return one instance of each of the extensions that the plugin provides. Hook's plugin platform, explained later, will pick up those extensions and make sure they're hooked to the correct extension points. If you're wondering, it is possible to return multiple instances of the same extension. Whereas there's probably little usage for this - most likely none - Hook will certainly not complain about it. If your extension points, however, cannot handle multiple instances of the same extension being passed to it, Hook will catch the error and the plugin will obviously not be initialized.

TODO: Continue
