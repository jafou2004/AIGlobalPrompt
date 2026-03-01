# AIGlobalPrompt

![Build](https://github.com/jafou2004/AIGlobalPrompt/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/30296.svg)](https://plugins.jetbrains.com/plugin/30296)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/30296.svg)](https://plugins.jetbrains.com/plugin/30296)

<!-- Plugin description -->
**AIGlobalPrompt** keeps your AI Assistant commit message prompt consistent across all your projects.

Write your system prompt once in <kbd>Settings</kbd> > <kbd>Tools</kbd> > <kbd>AIGlobalPrompt</kbd>, and the plugin automatically pushes it to the AI Assistant's "Generate Commit Message" action every time you open a project.

### Features

- **Global prompt** — define a single system prompt applied to all projects.
- **Per-project opt-out** — disable the sync for a specific project via the same settings panel.
- **Instant apply** — clicking _Apply_ in settings pushes the prompt immediately, without restarting the IDE.
- **Reset to default** — restore the built-in prompt template with one click.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "AIGlobalPrompt"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/30296) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/30296/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/jafou2004/AIGlobalPrompt/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
