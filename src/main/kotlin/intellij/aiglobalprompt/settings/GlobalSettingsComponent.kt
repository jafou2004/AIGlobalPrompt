package intellij.aiglobalprompt.settings

import intellij.aiglobalprompt.AIGlobalPromptBundle
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import javax.swing.JPanel

class GlobalSettingsComponent(onOpenAiAssistantSettings: () -> Unit) {

    private val promptArea = JBTextArea(15, 60).apply {
        lineWrap = true
        wrapStyleWord = true
    }

    private lateinit var useGlobalCheckbox: JBCheckBox

    val panel: JPanel = panel {
        row {
            useGlobalCheckbox = checkBox(AIGlobalPromptBundle.message("settings.project.useGlobal")).component
        }
        row {
            comment(AIGlobalPromptBundle.message("settings.project.useGlobal.hint"))
        }
        row {
            cell(ActionLink(AIGlobalPromptBundle.message("settings.link.aiAssistant")) { onOpenAiAssistantSettings() })
        }
        separator()
        row {
            label(AIGlobalPromptBundle.message("settings.global.description"))
            button(AIGlobalPromptBundle.message("settings.global.reset")) {
                promptArea.text = GlobalSettingsState.DEFAULT_PROMPT
            }.align(AlignX.RIGHT)
        }
        row {
            cell(JBScrollPane(promptArea))
                .align(Align.FILL)
                .resizableColumn()
        }.resizableRow()
    }

    var prompt: String
        get() = promptArea.text
        set(value) { promptArea.text = value }

    var useGlobalPrompt: Boolean
        get() = useGlobalCheckbox.isSelected
        set(value) { useGlobalCheckbox.isSelected = value }
}
