package intellij.aiglobalprompt.settings

import intellij.aiglobalprompt.AIGlobalPromptBundle
import intellij.aiglobalprompt.sync.AiAssistantPromptSync
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import javax.swing.JComponent

class GlobalSettingsConfigurable(private val project: Project) : Configurable {

    private var component: GlobalSettingsComponent? = null

    override fun getDisplayName(): String = AIGlobalPromptBundle.message("settings.global.displayName")

    override fun createComponent(): JComponent {
        component = GlobalSettingsComponent {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, "AI Assistant")
        }
        return component!!.panel
    }

    override fun isModified(): Boolean {
        val comp = component ?: return false
        return comp.prompt != GlobalSettingsState.instance.prompt ||
               comp.useGlobalPrompt != ProjectSettingsState.getInstance(project).useGlobalPrompt
    }

    override fun apply() {
        val comp = component ?: return
        GlobalSettingsState.instance.prompt = comp.prompt
        ProjectSettingsState.getInstance(project).useGlobalPrompt = comp.useGlobalPrompt
        if (comp.useGlobalPrompt) {
            AiAssistantPromptSync.syncToProject(project, comp.prompt)
        }
    }

    override fun reset() {
        val comp = component ?: return
        comp.prompt = GlobalSettingsState.instance.prompt
        comp.useGlobalPrompt = ProjectSettingsState.getInstance(project).useGlobalPrompt
    }

    override fun disposeUIResources() {
        component = null
    }
}
