package com.iadams.gradle.plugins.pldoc

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import spock.lang.Unroll

/**
 * Created by Iain Adams
 */
class GradleCompatabilityIntegSpec extends IntegrationSpec {

    @Unroll("should use Gradle #requestedGradleVersion when requested")
    def "should allow to run pldoc with different Gradle versions"() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'
                        '''.stripIndent()
            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')

        and:
            gradleVersion = requestedGradleVersion

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
            result.standardOutput.contains('1 packages processed successfully.')

        where:
            requestedGradleVersion << ['1.6','1.7','1.8','1.9','1.10','1.11','1.12','2.0','2.1','2.2']
    }
}
