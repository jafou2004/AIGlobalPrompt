package intellij.aiglobalprompt.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project

@State(
    name = "IACommitMessageProjectSettings",
    storages = [Storage("ia-commit-message-project.xml")]
)
@Service(Service.Level.PROJECT)
class ProjectSettingsState : PersistentStateComponent<ProjectSettingsState.State> {

    data class State(var useGlobalPrompt: Boolean = true)

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    var useGlobalPrompt: Boolean
        get() = myState.useGlobalPrompt
        set(value) { myState.useGlobalPrompt = value }

    companion object {
        fun getInstance(project: Project): ProjectSettingsState =
            project.getService(ProjectSettingsState::class.java)
    }
}
