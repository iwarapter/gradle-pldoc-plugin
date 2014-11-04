Gradle-Pldoc-Plugin
=========

This is a gradle plugin for the [pldoc] tool.

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
