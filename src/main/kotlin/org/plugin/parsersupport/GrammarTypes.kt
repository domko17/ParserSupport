package org.plugin.parsersupport

enum class GrammarTypes(val typeOfGrammar : String, val description : String) {

    BNF("bnf", "General type of grammar"),
    EBNF("ebnf", "BNF extension type of grammar"),
    ANTLR("g4", "ANTLR grammar"),
    FLEX("flex", "JFlex grammar");
}