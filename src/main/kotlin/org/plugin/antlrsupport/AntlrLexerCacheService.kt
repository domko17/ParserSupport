package org.plugin.antlrsupport

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.testFramework.LightVirtualFile

@Service
class AntlrLexerCacheService {
    private val lexerCache = mutableMapOf<String, LightVirtualFile>()

    fun storeLexer(lexerName: String, lexerFile: LightVirtualFile) {
        lexerCache[lexerName] = lexerFile
    }

    fun getLexer(lexerName: String): LightVirtualFile? {
        return lexerCache[lexerName]
    }

    fun clearCache() {
        lexerCache.clear()
    }
}
// Access the cache service
fun getLexerCacheService(): AntlrLexerCacheService = service()