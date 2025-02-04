package org.plugin.antlrsupport

import org.antlr.v4.Tool

fun generateParser(grammarPath: String, outputDir: String) {
    val antlr = Tool(
        arrayOf(
            "-Dlanguage=Java",  // Output Java classes
            "-o", outputDir,    // Output directory
            grammarPath         // Grammar file to process
        )
    )
    antlr.processGrammarsOnCommandLine()
}