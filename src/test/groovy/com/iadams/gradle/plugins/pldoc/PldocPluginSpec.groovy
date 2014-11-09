package com.iadams.gradle.plugins.pldoc

import nebula.test.PluginProjectSpec
import org.gradle.api.Task

/**
 * Created by Iain Adams
 */

class PldocPluginSpec extends PluginProjectSpec {

    static final String PLUGIN_ID = 'com.iadams.pldoc'

    @Override
    String getPluginName() {
        return PLUGIN_ID
    }

    def setup() {
        project.apply plugin: pluginName
    }

    def "apply creates pldoc extension" () {
        expect: project.extensions.findByName('pldoc')
    }

    def "apply creates tasks of type pldoc with default values"() {
        setup:
            Task task = project.tasks.findByName('pldoc')

        expect:
            task != null
            task.group == 'Pldoc'
            task.appName == "PL/SQL"
            task.destDir == new File("${project.buildDir}/pldoc")
            task.sourceDir == new File("${project.projectDir}/src/main/plsql")
            task.stylesheet == ''
            task.overview == null
            task.ignoreInformalComments == false
            task.namesDefaultCase == true
            task.namesUpperCase == false
            task.namesLowerCase == false
            task.inputEncoding == System.getProperty("file.encoding")
            task.verbose == false
            task.exitOnError == false
            task.showSkippedPackages == true
    }
}
