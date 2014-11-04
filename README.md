Gradle-Pldoc-Plugin
=========

This is a gradle plugin for the [pldoc] tool.

[![Build Status](https://travis-ci.org/iwarapter/gradle-pldoc-plugin.svg?branch=master)](https://travis-ci.org/iwarapter/gradle-pldoc-plugin)

Usage
-----------

To apply the plugin:
```
buildscript {
	repositories {
	    mavenLocal()
	}
	dependencies {
		classpath 'com.iadams:gradle-pldoc-plugin:0.1'
	}
}

apply plugin: 'com.iadams.pldoc'
```

[pldoc]:http://pldoc.sourceforge.net/maven-site/
