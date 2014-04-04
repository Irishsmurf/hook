# Hook [![Build Status](https://travis-ci.org/jangasoft/hook.svg?branch=master)](https://travis-ci.org/jangasoft/hook)

Hook is a Java plugin framework. It's based on a few simple concepts:
* Host application: an application that's been built to support plugins. A host application has one or more extension points;
* Extension point: a part of the application that can be extended via plugins;
* Plugin: an isolated component that extends the functionalities of a specific host application by providing extensions;
* Extension: some new functionality provided by a plugin and which hooks to a specific extension point in a host application.

The latest stable release is 0.0.1. If you use Maven, the best way to obtain it is adding the dependency below to your project. This is picked up from the Central Repository - which maven automatically reaches out to - so there's no need to add any repository.

```xml
<dependency>
	<groupId>cf.janga</groupId>
	<artifactId>hook</artifactId>
	<version>0.0.1</version>
</dependency>
```

This documentation is a work-in-progress. In time, I'll be adding more stuff in here, so bear with me.
