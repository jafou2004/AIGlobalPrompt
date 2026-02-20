package intellij.aiglobalprompt.sync

import com.intellij.testFramework.fixtures.BasePlatformTestCase

/**
 * Tests that AiAssistantPromptSync degrades gracefully when the AI Assistant
 * plugin is not present in the test sandbox (ClassNotFoundException expected).
 */
class AiAssistantPromptSyncTest : BasePlatformTestCase() {

    fun testSyncToProjectDoesNotThrowWhenAiAssistantUnavailable() {
        // The AI Assistant plugin is not loaded in the test sandbox,
        // so ClassNotFoundException should be caught internally and logged.
        AiAssistantPromptSync.syncToProject(project, "test prompt")
    }

    fun testSyncToProjectDoesNotThrowWithEmptyPrompt() {
        AiAssistantPromptSync.syncToProject(project, "")
    }

    fun testSyncToProjectDoesNotThrowWithMultilinePrompt() {
        val multiline = "Line 1\nLine 2\nLine 3"
        AiAssistantPromptSync.syncToProject(project, multiline)
    }
}
