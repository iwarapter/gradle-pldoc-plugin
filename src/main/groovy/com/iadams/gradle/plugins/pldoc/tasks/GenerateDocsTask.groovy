package com.iadams.gradle.plugins.pldoc.tasks

import static groovy.io.FileType.FILES
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import net.sourceforge.pldoc.*
import org.gradle.api.logging.LogLevel

/**
 * Created by Iain Adams
 */
class GenerateDocsTask extends DefaultTask {

    @InputDirectory
    File sourceDir

    @OutputDirectory
    File destDir

    @Input
    @Optional
    String appName

    @Input
    @Optional
    File styleSheet

    @Input
    @Optional
    File overview

    @Input
    @Optional
    boolean ignoreInformalComments

    @Input
    @Optional
    boolean namesDefaultCase

    @Input
    @Optional
    boolean namesUpperCase

    @Input
    @Optional
    boolean namesLowerCase

    @Input
    @Optional
    String inputEncoding

    @Input
    @Optional
    Boolean verbose

    @Input
    @Optional
    Boolean exitOnError

    @Input
    @Optional
    boolean showSkippedPackages


    @TaskAction
    void runPldoc() {

        logger.debug "AppName " + getAppName()
        logger.debug "DestDir " + getDestDir()
        logger.debug "SourceDir " + getSourceDir()
        logger.debug "StyleSheet " + getStyleSheet()
        logger.debug "Overview " + getOverview()
        logger.debug "IgnoreInformalComments " + getIgnoreInformalComments()
        logger.debug "NamesDefaultCase " + getNamesDefaultCase()
        logger.debug "NamesUpperCase " + getNamesUpperCase()
        logger.debug "NamesLowerCase " + getNamesLowerCase()
        logger.debug "InputEncoding " + getInputEncoding()
        logger.debug "Verbose " + getVerbose()
        logger.debug "ExitOnError " + getExitOnError()
        logger.debug "ShowSkippedPackages " + getShowSkippedPackages()

        def settings = new Settings()

        def inputFiles = []
        getSourceDir().eachFileRecurse(FILES){ inputFiles << it.path }

        settings.setApplicationName(getAppName())
        settings.setOutputDirectory(getDestDir())
        settings.setInputFiles(inputFiles)
        settings.setStylesheetfile(getStyleSheet())
        settings.setOverviewfile(getOverview())
        settings.setIgnoreInformalComments(getIgnoreInformalComments())
        settings.setNamesDefaultcase(getNamesDefaultCase())
        settings.setNamesUppercase(getNamesUpperCase())
        settings.setNamesLowercase(getNamesLowerCase())
        settings.setInputEncoding(getInputEncoding())
        settings.setVerbose(getVerbose())
        settings.setExitOnError(getExitOnError())
        settings.setShowSkippedPackages(getShowSkippedPackages())

        def pldoc = new PLDoc(settings)

        logging.captureStandardOutput LogLevel.INFO
        pldoc.run()
    }
}
