package com.iadams.gradle.plugins.pldoc.tasks

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

    @Input
    @Optional
    String includes

    @Input
    @Optional
    String exclusions

    @OutputDirectory
    File destDir

    @Input
    @Optional
    String appName

    @Input
    @Optional
    String stylesheet

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

        logger.info "SourceDir: ${getSourceDir().absolutePath}"
        logger.info "DestDir: ${getDestDir().absolutePath}"
        logger.info "Includes: ${getIncludes()}"
        logger.info "Exclusions: ${getExclusions()}"

        def settings = new Settings()

        def inputFiles = new FileNameFinder().getFileNames(getSourceDir().toString(),getIncludes(),getExclusions())

        settings.setApplicationName(getAppName())
        settings.setOutputDirectory(getDestDir())
        settings.setInputFiles(inputFiles)
        settings.setStylesheetfile(getStylesheet())
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
