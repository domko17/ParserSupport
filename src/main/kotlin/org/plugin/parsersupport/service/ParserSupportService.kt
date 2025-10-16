package org.plugin.parsersupport.service

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.vfs.VirtualFile
import org.antlr.v4.Tool
import org.antlr.v4.parse.ANTLRParser
import org.antlr.v4.runtime.atn.ATN
import org.antlr.v4.tool.ast.GrammarAST
import org.antlr.v4.tool.ast.GrammarRootAST

/**
 * TODO javaDocs, currently implementations just ANTLR4 generator but this class will working with other parser generator also
 */
class ParserSupportService(private val grammarFile: VirtualFile) {
    private val LOG = Logger.getInstance(ParserSupportService::class.java)

    fun getAst(): GrammarRootAST {
        val antlr = Tool(arrayOf<String?>(grammarFile.path))
        val g = antlr.loadGrammar(grammarFile.path)
        return g.ast
    }

    fun getAtn(): ATN {
        val antlr = Tool(arrayOf<String?>(grammarFile.path))
        val grammar = antlr.loadGrammar(grammarFile.path)
        return grammar.atn
    }

    fun printTree(ast: GrammarRootAST) {
        println("Grammar AST:")
        printAstRecursive(ast, 0)
    }

    private fun printAstRecursive(node: GrammarAST, indent: Int) {
        val indentStr = " ".repeat(indent * 2)

        // types of node which can influence a build tree
        if (node.type == ANTLRParser.ACTION ||
            node.type == ANTLRParser.SEMPRED ||
            node.type == ANTLRParser.AT ||
            node.type == ANTLRParser.ARG_ACTION ||
            node.type == ANTLRParser.LOCALS) {
            print("VALUE: ${node.text.trim()}")
        }

        val typeName = node.token?.text ?: node.text ?: node.javaClass.simpleName

        println("$indentStr- $typeName")

        // Iterate children
        for (child in node.children ?: emptyList<GrammarAST>()) {
            printAstRecursive(child as GrammarAST, indent + 1)
        }
    }
}