package org.plugin.parsersupport.service

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.vfs.VirtualFile
import org.antlr.v4.Tool
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.tool.ast.GrammarRootAST

/**
 * TODO javaDocs, currently implementations just ANTLR4 generator but this class will working with other parser generator also
 */
//class ParserSupportService(private val grammarFile: VirtualFile) {
//    private val LOG = Logger.getInstance(ParserSupportService::class.java)
//
//    fun getAst(): GrammarRootAST {
//        val antlr = Tool(arrayOf<String?>(grammarFile.path))
//        val g = antlr.loadGrammar(grammarFile.path)
//        return g.ast
//    }
//
//    fun getAtn(): ATN {
//        val antlr = Tool(arrayOf<String?>(grammarFile.path))
//        val grammar = antlr.loadGrammar(grammarFile.path)
//        return grammar.atn
//    }
//}