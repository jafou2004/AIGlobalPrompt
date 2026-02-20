package intellij.aiglobalprompt.sync

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializer
import org.jdom.Element

/**
 * Syncs the global prompt to the AI Assistant's CustomInstructions storage for a given project.
 *
 * Uses reflection to avoid a compile-time dependency on the AI Assistant plugin internals.
 * The AIAssistantStoredInstruction is created via XmlSerializer.deserialize(), which reuses
 * IntelliJ's own XML serialization (the same mechanism used when reading workspace.xml),
 * so PSString conversion is handled transparently.
 */
object AiAssistantPromptSync {

    private val LOG = thisLogger()

    private const val ACTION_ID = "AIAssistant.VCS.GenerateCommitMessage"
    private const val STORAGE_CLASS =
        "com.intellij.ml.llm.core.chat.promptLibrary.core.systemPrompts.AIAssistantCustomInstructionsStorage"
    private const val INSTRUCTION_CLASS =
        "com.intellij.ml.llm.core.chat.promptLibrary.core.systemPrompts.AIAssistantStoredInstruction"

    fun syncToProject(project: Project, prompt: String) {
        try {
            val storageClass = Class.forName(STORAGE_CLASS)
            val storage = project.getService(storageClass) ?: run {
                LOG.warn("AIAssistantCustomInstructionsStorage service not found in project '${project.name}'")
                return
            }
            val instructionClass = Class.forName(INSTRUCTION_CLASS)

            // Build an XML element matching the workspace.xml format for AIAssistantStoredInstruction.
            // XmlSerializer.deserialize handles the String → PSString conversion transparently.
            val element = Element("AIAssistantStoredInstruction")
            element.addContent(Element("option")
                .setAttribute("name", "actionId")
                .setAttribute("value", ACTION_ID))
            element.addContent(Element("option")
                .setAttribute("name", "content")
                .setAttribute("value", prompt))

            val instruction = XmlSerializer.deserialize(element, instructionClass)
            storageClass.getDeclaredMethod("save", instructionClass).invoke(storage, instruction)

            LOG.info("Synced global prompt to AI Assistant for project '${project.name}'")
        } catch (e: ClassNotFoundException) {
            LOG.warn("AI Assistant plugin not available (class not found: ${e.message})")
        } catch (e: Exception) {
            LOG.warn("Failed to sync prompt to AI Assistant: ${e.message}", e)
        }
    }
}
