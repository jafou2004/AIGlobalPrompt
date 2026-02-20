package intellij.aiglobalprompt.settings

import com.intellij.testFramework.fixtures.BasePlatformTestCase

class ProjectSettingsStateTest : BasePlatformTestCase() {

    // --- State data class ---

    fun testStateDefaultUseGlobalPromptIsTrue() {
        val state = ProjectSettingsState.State()
        assertTrue(state.useGlobalPrompt)
    }

    fun testStateEqualityOnSameValue() {
        val a = ProjectSettingsState.State(useGlobalPrompt = false)
        val b = ProjectSettingsState.State(useGlobalPrompt = false)
        assertEquals(a, b)
    }

    fun testStateCopyPreservesOriginal() {
        val original = ProjectSettingsState.State(useGlobalPrompt = true)
        val copy = original.copy(useGlobalPrompt = false)
        assertTrue(original.useGlobalPrompt)
        assertFalse(copy.useGlobalPrompt)
    }

    // --- getState / loadState ---

    fun testGetStateReturnsDefaultState() {
        val settings = ProjectSettingsState()
        assertTrue(settings.getState().useGlobalPrompt)
    }

    fun testLoadStatePersistsUseGlobalPromptFalse() {
        val settings = ProjectSettingsState()
        settings.loadState(ProjectSettingsState.State(useGlobalPrompt = false))
        assertFalse(settings.getState().useGlobalPrompt)
    }

    fun testLoadStatePersistsUseGlobalPromptTrue() {
        val settings = ProjectSettingsState()
        settings.loadState(ProjectSettingsState.State(useGlobalPrompt = false))
        settings.loadState(ProjectSettingsState.State(useGlobalPrompt = true))
        assertTrue(settings.getState().useGlobalPrompt)
    }

    // --- useGlobalPrompt property ---

    fun testUseGlobalPromptGetterReturnsTrueByDefault() {
        val settings = ProjectSettingsState()
        assertTrue(settings.useGlobalPrompt)
    }

    fun testUseGlobalPromptSetterToFalse() {
        val settings = ProjectSettingsState()
        settings.useGlobalPrompt = false
        assertFalse(settings.useGlobalPrompt)
    }

    fun testUseGlobalPromptSetterRoundTrip() {
        val settings = ProjectSettingsState()
        settings.useGlobalPrompt = false
        settings.useGlobalPrompt = true
        assertTrue(settings.useGlobalPrompt)
    }

    fun testUseGlobalPromptSetterIsReflectedInGetState() {
        val settings = ProjectSettingsState()
        settings.useGlobalPrompt = false
        assertFalse(settings.getState().useGlobalPrompt)
    }
}
