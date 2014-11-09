package com.iadams.gradle.plugins.pldoc

/**
 * Created by Iain Adams
 */
class SupportMethods {
    protected static final CreateStyleSheet(String name){
        def javaFile = new File(name)
        javaFile << """/* PLDoc style sheet. */

/* Background color */
body { background-color: #FFFFFF }

/* Table colors */
.TableHeadingColor     { background: #CCCCFF }
.TableSubHeadingColor  { background: #EEEEFF }
.TableRowColor         { background: #FFFFFF }

/* Contents frame font */
.FrameTitleFont   { font-size: normal; font-family: normal }
.FrameHeadingFont { font-size: normal; font-family: normal }
.FrameItemFont    { font-size: normal; font-family: normal }

/* Navigation bar */
.NavBarRow1    \t   { background-color:#EEEEFF;}
.NavBarRow1Chosen  { background-color:#00008B;}
.NavBarFont1       { font-family: Arial, Helvetica, sans-serif; color:#000000;}
.NavBarFont1Chosen { font-family: Arial, Helvetica, sans-serif; color:#FFFFFF;}

.NavBarRow2    { font-family: Arial, Helvetica, sans-serif; background-color:#FFFFFF;}
.NavBarRow3    { font-family: Arial, Helvetica, sans-serif; background-color:#FFFFFF;}

/* Tag Heading */
DT {font-weight: bold;}

/* Source Code  - from http://thomasf.github.io/solarized-css/solarized-light.css */

pre { background-color: #fdf6e3; color: #657b83; border: 1pt solid #93a1a1; padding: 1em; box-shadow: 5pt 5pt 8pt #eee8d5; }
pre code { background-color: #fdf6e3; }

.tag { background-color: #eee8d5; color: #d33682; padding: 0 0.2em; }
.tag { -webkit-border-radius: 0.35em; -moz-border-radius: 0.35em; border-radius: 0.35em; }

.callOut { -webkit-border-radius: 0.2em; -moz-border-radius: 0.2em; border-radius: 0.2em; background-color: #268bd2; }


/* CPD Index Page settings */
.SummaryTitle  { }
.SummaryNumber { background-color:#fdf6e3; text-align: center; }
.ItemNumber    { background-color: #EEEEFF; }
/* Make CPD textarea consistent with pre style*/
textarea.CodeFragment   /*{ background-color: #fdf6e3; display:none; font:normal normal normal 9pt Courier; }*/
      { background-color: #fdf6e3; color: #657b83; border: 1pt solid #93a1a1; padding: 1em; box-shadow: 5pt 5pt 8pt #eee8d5; }
.ExpandButton  {  -webkit-border-radius: 5px; -mozilla-border-radius: 5px;  background-color: #EEEEFF; font-size: 8pt; margin:0px; }
        """.stripIndent()
    }
}
