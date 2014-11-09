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
            createSample('com.iadams')
            buildFile << '''
                        apply plugin: 'com.iadams.pldoc'
                    '''.stripIndent()


        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
            result.standardOutput.contains('1 packages processed successfully.')
    }

    def 'setup new build and add custom pldoc sourceDir'() {
        setup:
            useToolingApi = false
            createSample('com.iadams','src/plsql/')
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src/plsql"
                            }
                        '''.stripIndent()


        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def 'setup a project with other files in the target sourceDir'() {
        setup:
            useToolingApi = false
            createSample('com.iadams','src/')
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = "${project.projectDir}/src"
                                exitOnError = true
                                sourceTypes = '*'
                            }
                        '''.stripIndent()
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
            createSample('com.iadams','src/')
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
            createSample('com.iadams','src/')
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
            createSample('com.iadams','src/')
            createSample('com.example','src/')
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
            createSample('com.iadams','src/main/plsql/')
            buildFile << '''
                        apply plugin: 'com.iadams.pldoc'
                        '''.stripIndent()

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
                                styleSheet = new File('stylesheet.css')
                            }
                        '''.stripIndent()
            createSample('com.iadams')
            SupportMethods.CreateStyleSheet("${projectDir}/stylesheet.css")
        when:
            ExecutionResult result = runTasksSuccessfully('pldoc')

        then:
            result.standardOutput.contains('1 packages processed successfully.')
            fileExists("build/pldoc/Undefined/_GLOBAL.html")
    }

    def createSample(String name, String subFolder = 'src/main/plsql/' , File baseDir = projectDir){
        def path = subFolder + name.replace('.', '/') + '/Sample1.sql'
        def javaFile = createFile(path, baseDir)
        javaFile << """CREATE OR REPLACE FUNCTION betwnstr (
                       string_in   IN   VARCHAR2,
                       start_in    IN   INTEGER,
                       end_in      IN   INTEGER
                    )
                       RETURN VARCHAR2
                    IS
                       l_start PLS_INTEGER := start_in;
                    BEGIN
                       IF l_start = 0
                       THEN
                          l_start := 1;
                       END IF;

                       RETURN (SUBSTR (string_in, l_start, end_in - l_start + 1));
                    END;
                    /
        """.stripIndent()
    }
}
