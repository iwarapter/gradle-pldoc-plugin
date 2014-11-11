package com.iadams.gradle.plugins.pldoc.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created by Iain Adams
 */

class GenerateDocsTaskSpec extends Specification {
    Project project

    void setup(){
        project = ProjectBuilder.builder().build()
    }

    def "Add generateDocs task"() {
        expect:
            project.tasks.findByName( 'pltest' ) == null

        when:
            project.task( 'pltest', type: GenerateDocsTask ){
                appName = "Big Cheese"
                destDir = new File('out')
                sourceDir = 'in'
                styleSheet = new File('stylesheet.css')
                overview = new File('overview.html')
                ignoreInformalComments = true
                namesDefaultCase = false
                namesUpperCase = true
                namesLowerCase = false
                inputEncoding = "UTF-8"
                verbose = true
                exitOnError = true
                showSkippedPackages = false
            }

        then:
            Task task = project.tasks.findByName( 'pltest' )
            task.appName == "Big Cheese"
            task.destDir == new File('out')
            task.sourceDir == 'in'
            task.styleSheet == new File('stylesheet.css')
            task.overview == new File('overview.html')
            task.ignoreInformalComments == true
            task.namesDefaultCase == false
            task.namesUpperCase == true
            task.namesLowerCase == false
            task.inputEncoding == "UTF-8"
            task.verbose == true
            task.exitOnError == true
            task.showSkippedPackages == false
    }
}
