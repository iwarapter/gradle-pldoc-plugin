package com.iadams.gradle.plugins.pldoc

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

/**
 * Created by Iain Adams
 */
class PldocPluginSubprojectsIntegSpec extends IntegrationSpec {

    def 'setup multi-project build'() {
        setup:
            useToolingApi = false
            buildFile << '''
                    apply plugin: 'com.iadams.pldoc'
                '''.stripIndent()

            addSubproject('schemaOne')
            addSubproject('schemaTwo')
            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'multi-project build with custom sourceDir'() {
        setup:
            useToolingApi = false
            buildFile << '''
                        apply plugin: 'com.iadams.pldoc'

                        pldoc {
                            sourceDir = '.'
                        }
                    '''.stripIndent()

            addSubproject('schemaOne')
            addSubproject('schemaTwo')
            copyResources('source/sample1.sql','schemaOne/src/main/plsql/com/iadams/sample1.sql')
            copyResources('source/sample1.sql','schemaTwo/src/main/plsql/com/iadams/sample1.sql')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }
}
