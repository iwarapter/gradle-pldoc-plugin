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
		classpath 'com.iadams:gradle-pldoc-plugin:0.1.2'
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
}
```

* `sourceDir` : Base directory to look for all source files.
* `destDir` : The location to store the generated docs.
* `appName` :  The name for the generated docs.
* `includes` : Comma seperated list of files to include.
* `exclusions` : Comma seperated list of files to ignore.
* `stylesheet` :  Name of custom stylesheet file.
* `overview` : Name of a html overview page to include.

[pldoc]:http://pldoc.sourceforge.net/maven-site/
