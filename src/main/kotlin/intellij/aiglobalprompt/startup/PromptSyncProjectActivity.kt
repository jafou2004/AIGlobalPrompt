package intellij.aiglobalprompt.startup

import intellij.aiglobalprompt.settings.GlobalSettingsState
import intellij.aiglobalprompt.settings.ProjectSettingsState
import intellij.aiglobalprompt.sync.AiAssistantPromptSync
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class PromptSyncProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        if (ProjectSettingsState.getInstance(project).useGlobalPrompt) {
            AiAssistantPromptSync.syncToProject(project, GlobalSettingsState.instance.prompt)
        }
    }
}
