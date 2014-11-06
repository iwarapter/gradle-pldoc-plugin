Gradle-Pldoc-Plugin
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

## Configuration

### build.gradle
```groovy
	pldoc {
		sourceDir = new File("${project.projectDir}/src/main/plsql")
		destDir = new File("${project.buildDir}/pldoc")
		appName = 'MyFancyApp'
	}
```

* `sourceDir` : Directory containing all the source file to run pldoc on.
* `destDir` : The location to store the generated docs.
* `appName` :  The name for the generated docs.

[pldoc]:http://pldoc.sourceforge.net/maven-site/
