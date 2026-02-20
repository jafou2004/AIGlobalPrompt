package intellij.aiglobalprompt.settings

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GlobalSettingsStateTest : BasePlatformTestCase() {

    // --- DEFAULT_PROMPT constant ---

    fun testDefaultPromptIsNotBlank() {
        assertTrue(GlobalSettingsState.DEFAULT_PROMPT.isNotBlank())
    }

    fun testDefaultPromptMentionsImperativeMood() {
        assertTrue(
            "Expected DEFAULT_PROMPT to mention imperative mood",
            GlobalSettingsState.DEFAULT_PROMPT.contains("imperative", ignoreCase = true)
        )
    }

    fun testDefaultPromptMentions72Characters() {
        assertTrue(
            "Expected DEFAULT_PROMPT to mention the 72-character limit",
            GlobalSettingsState.DEFAULT_PROMPT.contains("72")
        )
    }

    // --- State data class ---

    fun testStateDefaultPromptMatchesConstant() {
        val state = GlobalSettingsState.State()
        assertEquals(GlobalSettingsState.DEFAULT_PROMPT, state.prompt)
    }

    fun testStateEqualityOnSamePrompt() {
        val a = GlobalSettingsState.State("my prompt")
        val b = GlobalSettingsState.State("my prompt")
        assertEquals(a, b)
    }

    fun testStateCopyPreservesOriginal() {
        val original = GlobalSettingsState.State("original")
        val copy = original.copy(prompt = "copy")
        assertEquals("original", original.prompt)
        assertEquals("copy", copy.prompt)
    }

    // --- getState / loadState ---

    fun testGetStateReturnsStateWithDefaultPrompt() {
        val settings = GlobalSettingsState()
        assertEquals(GlobalSettingsState.DEFAULT_PROMPT, settings.getState().prompt)
    }

    fun testLoadStatePersistsPrompt() {
        val settings = GlobalSettingsState()
        settings.loadState(GlobalSettingsState.State("persisted prompt"))
        assertEquals("persisted prompt", settings.getState().prompt)
    }

    // --- prompt property ---

    fun testPromptGetterReturnsDefaultInitially() {
        val settings = GlobalSettingsState()
        assertEquals(GlobalSettingsState.DEFAULT_PROMPT, settings.prompt)
    }

    fun testPromptSetterUpdatesValue() {
        val settings = GlobalSettingsState()
        settings.prompt = "updated prompt"
        assertEquals("updated prompt", settings.prompt)
    }

    fun testPromptSetterIsReflectedInGetState() {
        val settings = GlobalSettingsState()
        settings.prompt = "via setter"
        assertEquals("via setter", settings.getState().prompt)
    }
}
