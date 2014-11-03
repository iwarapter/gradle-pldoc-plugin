package com.iadams.gradle.plugins.pldoc

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
/**
 * Created by Iain Adams
 */
class PldocPluginIntegSpec extends IntegrationSpec {

    def 'setup new build and check tasks are available'() {
        setup:
            buildFile << '''
                    apply plugin: 'com.iadams.pldoc'
                '''.stripIndent()

        when:
            ExecutionResult result = runTasksSuccessfully('tasks')

        then:
            result.standardOutput.contains('pldoc - Generates documentation using pldoc.')
    }
}
