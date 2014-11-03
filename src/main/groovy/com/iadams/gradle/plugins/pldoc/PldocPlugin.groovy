package com.iadams.gradle.plugins.pldoc

import com.iadams.gradle.plugins.pldoc.extensions.PldocPluginExtension
import com.iadams.gradle.plugins.pldoc.tasks.GenerateDocsTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

/**
 * Created by Iain Adams
 */
class PldocPlugin implements Plugin<Project> {
    static final PLDOC_EXTENSION = 'pldoc'
    static final PLDOC_TASK = 'pldoc'

    @Override
    void apply(Project project) {

        project.plugins.apply(BasePlugin.class)

        project.extensions.create( PLDOC_EXTENSION, PldocPluginExtension, project )

        addTasks(project)
    }
    /**
     * Add tasks to the plugin
     * @param project the target Gradle project
     */
    void addTasks( Project project ) {
        def extension = project.extensions.findByName(PLDOC_EXTENSION)

        project.task( PLDOC_TASK , type: GenerateDocsTask) {
            description = 'Generates documentation using pldoc.'
            group = 'Pldoc'

            conventionMapping.sourceDir = { extension.sourceDir }
            conventionMapping.destDir = { extension.destDir }
            conventionMapping.appName = { extension.appName }
            conventionMapping.styleSheet = { extension.styleSheet }
            conventionMapping.overview = { extension.overview }
            conventionMapping.ignoreInformalComments = { extension.ignoreInformalComments }
            conventionMapping.namesDefaultCase = { extension.namesDefaultCase }
            conventionMapping.namesUpperCase = { extension.namesUpperCase }
            conventionMapping.namesLowerCase = { extension.namesLowerCase }
            conventionMapping.inputEncoding = { extension.inputEncoding }
            conventionMapping.verbose = { extension.verbose }
            conventionMapping.exitOnError = { extension.exitOnError }
            conventionMapping.showSkippedPackages = { extension.showSkippedPackages }
        }
    }
}
