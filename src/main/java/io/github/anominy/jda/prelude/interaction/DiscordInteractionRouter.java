/*
 * Copyright 2024 anominy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.anominy.jda.prelude.interaction;

import io.github.anominy.jda.prelude.interaction.button.IDiscordButton;
import io.github.anominy.jda.prelude.interaction.command.IDiscordSlashCommandInteraction;
import io.github.anominy.jda.prelude.interaction.command.IDiscordSlashCommandOption;
import io.github.anominy.jda.prelude.interaction.context.IDiscordContextMenu;
import io.github.anominy.jda.prelude.interaction.context.IDiscordMessageContextMenu;
import io.github.anominy.jda.prelude.interaction.context.IDiscordUserContextMenu;
import io.github.anominy.jda.prelude.interaction.modal.IDiscordModal;
import io.github.anominy.jda.prelude.interaction.select.IDiscordEntitySelectMenu;
import io.github.anominy.jda.prelude.interaction.select.IDiscordSelectMenu;
import io.github.anominy.jda.prelude.interaction.select.IDiscordStringSelectMenu;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.Interaction;

import java.util.Map;

@SuppressWarnings({"unused", "NullableProblems"})
public final class DiscordInteractionRouter extends ListenerAdapter {
    private final Map<String, IDiscordSlashCommandInteraction> discordSlashCommandMapByPath;
    private final Map<String, Map<String, IDiscordSlashCommandOption>> discordSlashCommandOptionMapByPath;
    private final Map<String, IDiscordUserContextMenu> discordUserContextMenuMapByName;
    private final Map<String, IDiscordMessageContextMenu> discordMessageContextMenuMapByName;
    private final Map<String, IDiscordButton> discordButtonMapById;
    private final Map<String, IDiscordEntitySelectMenu> discordEntitySelectMenuMapById;
    private final Map<String, IDiscordStringSelectMenu> discordStringSelectMenuMapById;
    private final Map<String, IDiscordModal> discordModalMapById;

    public DiscordInteractionRouter(
            Map<String, IDiscordSlashCommandInteraction> discordSlashCommandMapByPath,
            Map<String, Map<String, IDiscordSlashCommandOption>> discordSlashCommandOptionMapByPath,
            Map<String, IDiscordUserContextMenu> discordUserContextMenuMapByName,
            Map<String, IDiscordMessageContextMenu> discordMessageContextMenuMapByName,
            Map<String, IDiscordButton> discordButtonMapById,
            Map<String, IDiscordEntitySelectMenu> discordEntitySelectMenuMapById,
            Map<String, IDiscordStringSelectMenu> discordStringSelectMenuMapById,
            Map<String, IDiscordModal> discordModalMapById
    ) {
        this.discordSlashCommandMapByPath = discordSlashCommandMapByPath;
        this.discordSlashCommandOptionMapByPath = discordSlashCommandOptionMapByPath;
        this.discordUserContextMenuMapByName = discordUserContextMenuMapByName;
        this.discordMessageContextMenuMapByName = discordMessageContextMenuMapByName;
        this.discordButtonMapById = discordButtonMapById;
        this.discordEntitySelectMenuMapById = discordEntitySelectMenuMapById;
        this.discordStringSelectMenuMapById = discordStringSelectMenuMapById;
        this.discordModalMapById = discordModalMapById;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        IDiscordSlashCommandInteraction discordSlashCommand
                = this.getDiscordSlashCommandByEvent(event);

        if (discordSlashCommand == null) {
            return;
        }

        discordSlashCommand.onDiscordSlashCommandInteractionEvent(event);
    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        IDiscordSlashCommandOption discordSlashCommandOption
                = this.getDiscordSlashCommandOptionByEvent(event);

        if (discordSlashCommandOption == null) {
            return;
        }

        discordSlashCommandOption.onDiscordSlashCommandAutoCompleteInteractionEvent(event);
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        IDiscordUserContextMenu discordUserContextMenu
                = this.getDiscordUserContextMenuByEvent(event);

        if (discordUserContextMenu == null) {
            return;
        }

        discordUserContextMenu.onDiscordUserContextInteractionEvent(event);
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        IDiscordMessageContextMenu discordMessageContextMenu
                = this.getDiscordMessageContextMenuByEvent(event);

        if (discordMessageContextMenu == null) {
            return;
        }

        discordMessageContextMenu.onDiscordMessageContextInteractionEvent(event);
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        IDiscordButton discordButton
                = this.getDiscordButtonByEvent(event);

        if (discordButton == null) {
            return;
        }

        discordButton.onDiscordButtonInteractionEvent(event);
    }

    @Override
    public void onEntitySelectInteraction(EntitySelectInteractionEvent event) {
        IDiscordEntitySelectMenu discordEntitySelectMenu
                = this.getDiscordEntitySelectMenuByEvent(event);

        if (discordEntitySelectMenu == null) {
            return;
        }

        discordEntitySelectMenu.onDiscordEntitySelectMenuInteractionEvent(event);
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        IDiscordStringSelectMenu discordStringSelectMenu
                = this.getDiscordStringSelectMenuByEvent(event);

        if (discordStringSelectMenu == null) {
            return;
        }

        discordStringSelectMenu.onDiscordStringSelectMenuInteractionEvent(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        IDiscordModal discordModal
                = this.getDiscordModalByEvent(event);

        if (discordModal == null) {
            return;
        }

        discordModal.onDiscordModalInteractionEvent(event);
    }

    private IDiscordSlashCommandInteraction getDiscordSlashCommandByEvent(SlashCommandInteractionEvent event) {
        if (this.discordSlashCommandMapByPath == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        return this.discordSlashCommandMapByPath.get(event.getFullCommandName());
    }

    private IDiscordSlashCommandOption getDiscordSlashCommandOptionByEvent(CommandAutoCompleteInteractionEvent event) {
        if (this.discordSlashCommandOptionMapByPath == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        Map<String, IDiscordSlashCommandOption> discordSlashCommandOptionMapByName
                = this.discordSlashCommandOptionMapByPath.get(event.getFullCommandName());

        if (discordSlashCommandOptionMapByName == null) {
            return null;
        }

        String discordSlashCommandOptionName = event.getFocusedOption()
                .getName();

        return discordSlashCommandOptionMapByName.get(discordSlashCommandOptionName);
    }

    private IDiscordUserContextMenu getDiscordUserContextMenuByEvent(UserContextInteractionEvent event) {
        return getDiscordContextMenuByEvent(this.discordUserContextMenuMapByName, event);
    }

    private IDiscordMessageContextMenu getDiscordMessageContextMenuByEvent(MessageContextInteractionEvent event) {
        return getDiscordContextMenuByEvent(this.discordMessageContextMenuMapByName, event);
    }

    private IDiscordButton getDiscordButtonByEvent(ButtonInteractionEvent event) {
        if (this.discordButtonMapById == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        String discordButtonId = event.getButton()
                .getId();

        if (discordButtonId == null) {
            return null;
        }

        return this.discordButtonMapById.get(discordButtonId);
    }

    private IDiscordEntitySelectMenu getDiscordEntitySelectMenuByEvent(EntitySelectInteractionEvent event) {
        return getDiscordSelectMenuByEvent(this.discordEntitySelectMenuMapById, event);
    }

    private IDiscordStringSelectMenu getDiscordStringSelectMenuByEvent(StringSelectInteractionEvent event) {
        return getDiscordSelectMenuByEvent(this.discordStringSelectMenuMapById, event);
    }

    private IDiscordModal getDiscordModalByEvent(ModalInteractionEvent event) {
        if (this.discordModalMapById == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        return this.discordModalMapById.get(event.getModalId());
    }

    private static <T extends IDiscordContextMenu, U extends GenericContextInteractionEvent<?>> T getDiscordContextMenuByEvent(Map<String, T> discordContextMenuMapByName, U event) {
        if (discordContextMenuMapByName == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        return discordContextMenuMapByName.get(event.getName());
    }

    private static <T extends IDiscordSelectMenu, U extends GenericSelectMenuInteractionEvent<?, ?>> T getDiscordSelectMenuByEvent(Map<String, T> discordSelectMenuMapById, U event) {
        if (discordSelectMenuMapById == null) {
            return null;
        }

        if (!isInteractionByPerson(event)) {
            return null;
        }

        return discordSelectMenuMapById.get(event.getComponentId());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isInteractionByPerson(Interaction interaction) {
        if (interaction == null) {
            return false;
        }

        User user = interaction.getUser();

        return !user.isSystem() && !user.isBot();
    }
}
