package com.iadams.gradle.plugins.pldoc

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

/**
 * Created by Iain Adams
 */
class PldocPluginIntegSpec extends IntegrationSpec {

    def 'setup new build and check tasks are available'() {
        setup:
            useToolingApi = false
            buildFile << '''
                    apply plugin: 'com.iadams.pldoc'
                '''.stripIndent()

        when:
            ExecutionResult result = runTasksSuccessfully('tasks')

        then:
            result.standardOutput.contains('pldoc - Generates documentation using pldoc.')
    }

    def 'setup new build and call pldoc'() {
        setup:
            useToolingApi = false
            buildFile << '''
                        apply plugin: 'com.iadams.pldoc'
                    '''.stripIndent()

            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
            result.standardOutput.contains('1 packages processed successfully.')
    }

    def 'setup new build and add custom pldoc sourceDir'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src/plsql"
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/plsql/com/iadams/sample1.sql')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'setup a project with other files in the target sourceDir'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src"
                                exitOnError = true
                                sourceTypes = '*'
                            }
                        '''.stripIndent()


            copyResources('source/sample1.sql','src/com/iadams/sample1.sql')
            writeHelloWorld('com.iadams')

        when:
            ExecutionResult result = runTasksWithFailure('pldoc')

        then:
            result.failure
    }

    def 'filter out files using the exclusions property'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src"
                                exclusions = '**/*.java'
                                exitOnError = true
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/com/iadams/sample1.sql')
            writeHelloWorld('com.iadams')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'filter out files using the includes property'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src"
                                includes = '**/*.sql'
                                exitOnError = true
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/com/iadams/sample1.sql')
            writeHelloWorld('com.iadams')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'filter specific directories using the includes property'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src"
                                includes = 'com/iadams/*.sql, com/example/*.sql'
                                exitOnError = true
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/com/iadams/sample1.sql')
            copyResources('source/sample1.sql','src/com/example/sample1.sql')
            writeHelloWorld('com.iadams')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('2 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'run pldoc twice to check the task is up-to-date'() {
        setup:
            useToolingApi = false
            buildFile << '''
                        apply plugin: 'com.iadams.pldoc'
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")

        when:
            result = runTasksSuccessfully('pldoc')

        then:
            result.wasUpToDate(':pldoc')
    }

    def 'setup a test with a stylesheet'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'
                            pldoc {
                                stylesheet = "${projectDir}/stylesheet.css"
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')
            copyResources('html/stylesheet.css','stylesheet.css')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'setup a test with a overview'() {
        setup:
            useToolingApi = false
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'
                            pldoc {
                                overview = "${projectDir}/overview.html"
                            }
                        '''.stripIndent()

            copyResources('source/sample1.sql','src/main/plsql/com/iadams/sample1.sql')
            copyResources('html/overview.html','overview.html')

        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }
}
