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
            result.standardOutput.contains('1 packages processed successfully.')
    }

    def createSample(String name, File baseDir = projectDir){
        def path = 'src/main/plsql/' + name.replace('.', '/') + '/Sample1.sql'
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
