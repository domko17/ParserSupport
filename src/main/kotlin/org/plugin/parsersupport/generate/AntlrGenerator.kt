package org.plugin.parsersupport.generate

import java.io.File
import java.net.URLClassLoader
import javax.tools.ToolProvider


class AntlrGenerator() {

//    fun generateParser(grammarFile : File): ClassLoader {
//        val antlrOutputDir = File("generated/antlr").apply { mkdirs() }
//
//        val tool = _root_ide_package_.javax.tools.Tool(
//            arrayOf(
//                "-o", antlrOutputDir.absolutePath,
//                "-visitor",
//                "-package", "generated",
//                grammarFile.absolutePath
//            )
//        )
//        tool.processGrammarsOnCommandLine()
//
//        val javaFiles = antlrOutputDir.walkTopDown().filter { it.extension == "java" }.map { it.absolutePath }.toList()
//        val compiler = ToolProvider.getSystemJavaCompiler()
//
//
//        println("java.version: ${System.getProperty("java.version")}")
//        println("java.vendor: ${System.getProperty("java.vendor")}")
//        println("java.home: ${System.getProperty("java.home")}")
//        println("java.runtime.name: ${System.getProperty("java.runtime.name")}")
//        println("java.vm.name: ${System.getProperty("java.vm.name")}")
//
//        print("TESTING::: $compiler")
//
//
//        val compileResult = compiler.run(null, null, null, *javaFiles.toTypedArray())
//
//        if (compileResult != 0) {
//            throw RuntimeException("ANTLR Java file compilation failed")
//        }
//
//        val classLoader = URLClassLoader(arrayOf(antlrOutputDir.toURI().toURL()), ClassLoader.getSystemClassLoader())
//        return classLoader
//    }
//
//    fun runCommand(vararg args: String, workingDir: File): Int {
//        println("Running: ${args.joinToString(" ")}")
//        val process = ProcessBuilder(*args)
//            .directory(workingDir)
//            .inheritIO()
//            .start()
//        return process.waitFor()
//    }
//
//    fun generateAndCompileAntlrParser() {
//        val antlrJar = File("antlr-4.13.1-complete.jar").absolutePath
//        val grammarDir = File("grammar")
//        val outputDir = File("generated").apply { mkdirs() }
//
//        val grammarFile = File(grammarDir, "SimpleExpr.g4")
//
//        val antlrResult = runCommand(
//            "java", "-jar", antlrJar,
//            "-o", outputDir.absolutePath,
//            "-visitor",
//            "-no-listener",
//            grammarFile.absolutePath
//            , workingDir = grammarDir)
//
//        if (antlrResult != 0) throw RuntimeException("ANTLR failed")
//
//        // 2. Compile generated Java files using javac
//        val javaFiles = outputDir.walkTopDown()
//            .filter { it.extension == "java" }
//            .map { it.absolutePath }
//            .toList()
//
//        val javacResult = runCommand(
//            "javac", "-cp", antlrJar,
//            *javaFiles.toTypedArray()
//            , workingDir = File("."))
//
//        if (javacResult != 0) throw RuntimeException("javac failed")
//
//        println("Parser generated and compiled successfully.")
//    }
}