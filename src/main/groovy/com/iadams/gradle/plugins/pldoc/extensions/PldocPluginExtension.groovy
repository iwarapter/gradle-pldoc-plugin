package com.iadams.gradle.plugins.pldoc.extensions

import org.gradle.api.Project
/**
 * A Gradle Plugin Extension used to configure the plugin.
 * Created by Iain Adams
 */
class PldocPluginExtension {

    String sourceDir
    String destDir
    String includes = '**/*'
    String exclusions = ''
    String appName = 'PL/SQL'
    File styleSheet = null
    File overview = null
    boolean ignoreInformalComments =false
    boolean namesDefaultCase = true
    boolean namesUpperCase = false
    boolean namesLowerCase = false
    String inputEncoding = System.getProperty('file.encoding')
    Boolean verbose = false
    Boolean exitOnError = false
    boolean showSkippedPackages = true

    String sourceTypes = '.*sql|.*pkb|.*pks'

    PldocPluginExtension( Project project) {
        sourceDir = "${project.projectDir}/src/main/plsql"
        destDir = "${project.buildDir}/pldoc"
    }
}