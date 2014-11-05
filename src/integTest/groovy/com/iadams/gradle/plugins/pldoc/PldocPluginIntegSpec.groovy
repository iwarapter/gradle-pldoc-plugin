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

    def 'setup new build and call pldoc'() {
        setup:
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
            createSample('com.iadams','src/plsql/')
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = new File("${projectDir}/src/plsql")
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
            createSample('com.iadams','src/')
            buildFile << '''
                            apply plugin: 'com.iadams.pldoc'

                            pldoc {
                                sourceDir = new File("${projectDir}/src/")
                                exitOnError = true
                            }
                        '''.stripIndent()
            writeHelloWorld('com.iadams')

        when:
            ExecutionResult result = runTasksWithFailure('pldoc')

        then:
            result.failure
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
