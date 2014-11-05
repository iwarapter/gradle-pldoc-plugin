package com.iadams.gradle.plugins.pldoc.extensions

import org.gradle.api.Project
/**
 * Created by Iain Adams
 */
class PldocPluginExtension {

    File sourceDir
    File destDir
    String appName = "PL/SQL"
    File styleSheet = null
    File overview = null
    boolean ignoreInformalComments =false
    boolean namesDefaultCase = true
    boolean namesUpperCase = false
    boolean namesLowerCase = false
    String inputEncoding = System.getProperty("file.encoding")
    Boolean verbose = false
    Boolean exitOnError = false
    boolean showSkippedPackages = true

    String sourceTypes = '.*sql|.*pkb|.*pks'

    PldocPluginExtension( Project project) {
        sourceDir = new File("${project.projectDir}/src/main/plsql")
        destDir = new File("${project.buildDir}/pldoc")
    }
}