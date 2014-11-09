package com.iadams.gradle.plugins.pldoc

import com.iadams.gradle.plugins.pldoc.extensions.PldocPluginExtension
import com.iadams.gradle.plugins.pldoc.tasks.GenerateDocsTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

/**
 * A Gradle Plugin to allow javadoc style documentation to be generated for PL/SQL using the Pldoc tool.
 * Created by Iain Adams
 */
class PldocPlugin implements Plugin<Project> {
    static final PLDOC_EXTENSION = 'pldoc'
    static final PLDOC_TASK = 'pldoc'

    /**
     * Applies BasePlugin to the project and add the tasks and extensions.
     * @param project Gradle Project Object
     */
    @Override
    void apply(Project project) {

        project.plugins.apply(BasePlugin.class)

        project.extensions.create( PLDOC_EXTENSION, PldocPluginExtension, project )

        addTasks(project)
    }

    /**
     * Adds the pldoc to the project
     * @param project Gradle Project Object
     */
    void addTasks( Project project ) {
        def extension = project.extensions.findByName(PLDOC_EXTENSION)

        project.task( PLDOC_TASK, type: GenerateDocsTask) {
            description = 'Generates documentation using pldoc.'
            group = 'Pldoc'

            conventionMapping.sourceDir = { new File(extension.sourceDir) }
            conventionMapping.includes = { extension.includes }
            conventionMapping.exclusions = { extension.exclusions }
            conventionMapping.destDir = { new File(extension.destDir) }
            conventionMapping.appName = { extension.appName }
            conventionMapping.stylesheet = { extension.stylesheet }
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
