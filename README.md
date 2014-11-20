Gradle Pldoc Plugin
=========

This is a gradle plugin for the [pldoc] tool.

Latest Version
--------------
 [ ![Download](https://api.bintray.com/packages/iwarapter/gradle-plugins/gradle-pldoc-plugin/images/download.svg) ](https://bintray.com/iwarapter/gradle-plugins/gradle-pldoc-plugin/_latestVersion)
 
 
Build Status
------------
[![Build Status](https://travis-ci.org/iwarapter/gradle-pldoc-plugin.svg?branch=master)](https://travis-ci.org/iwarapter/gradle-pldoc-plugin)

Usage
-----------

To apply the plugin:
```
buildscript {
	repositories {
		maven { url 'http://dl.bintray.com/iwarapter/gradle-plugins/' }
		mavenCentral()
	}
	dependencies {
		classpath 'com.iadams:gradle-pldoc-plugin:0.1.3'
	}
}

apply plugin: 'com.iadams.pldoc'
```

Tasks
-----------
```
Pldoc tasks
-----------
pldoc - Generates documentation using pldoc.
```
## Configuration

### build.gradle
```groovy
pldoc {
	sourceDir = 'src/main/'
	destDir = 'build/docs'
	appName = 'MyFancyApp'
	includes = '**/*'
    exclusions = ''
	stylesheet = 'stylesheet.css'
	overview = 'overview.html'
	verbose = false
	exitOnError = false
	showSkippedPackages = true
	ignoreInformalComments = false
}
```

* `sourceDir` : Base directory to look for all source files.
* `destDir` : The location to store the generated docs.
* `appName` :  The name for the generated docs.
* `includes` : Comma seperated list of files to include.
* `exclusions` : Comma seperated list of files to ignore.
* `stylesheet` :  Name of custom stylesheet file.
* `overview` : Name of a html overview page to include.
* `verbose` : Enables more verbose logging for pldoc (all pldoc output is logged an INFO level.)
* `exitOnError` : Fails the build in the event of a parsing error.
* `showSkippedPackages` : Shows the number of skipped packages (when exitOnError is disabled) (all pldoc output is logged an INFO level.)
* `ignoreInformalComments` : Ignore informal comments when generating documentation.

[pldoc]:http://pldoc.sourceforge.net/maven-site/
