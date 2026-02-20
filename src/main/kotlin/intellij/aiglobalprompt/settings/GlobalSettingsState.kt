package intellij.aiglobalprompt.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "IACommitMessageGlobalSettings",
    storages = [Storage("ia-commit-message-global.xml")]
)
@Service(Service.Level.APP)
class GlobalSettingsState : PersistentStateComponent<GlobalSettingsState.State> {

    data class State(var prompt: String = DEFAULT_PROMPT)

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    var prompt: String
        get() = myState.prompt
        set(value) { myState.prompt = value }

    companion object {
        val instance: GlobalSettingsState
            get() = ApplicationManager.getApplication().getService(GlobalSettingsState::class.java)

        const val DEFAULT_PROMPT =
            "Generate a concise commit message for the provided diff.\n\n" +
            "Requirements:\n" +
            "- Use the imperative mood (e.g., \"Add feature\" not \"Added feature\")\n" +
            "- Keep the subject line under 72 characters\n" +
            "- Focus on what and why, not how\n" +
            "- Optionally use Conventional Commits format: feat, fix, docs, style, refactor, test, chore"
    }
}
