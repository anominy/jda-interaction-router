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

package io.github.anominy.jda.prelude.interaction.select;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DiscordSelectMenuConfiguration {
    private final Map<String, IDiscordEntitySelectMenu> discordEntitySelectMenuMapById;
    private final Map<String, IDiscordStringSelectMenu> discordStringSelectMenuMapById;

    public DiscordSelectMenuConfiguration(List<IDiscordSelectMenu> discordSelectMenuList) {
        if (discordSelectMenuList == null) {
            this.discordEntitySelectMenuMapById = null;
            this.discordStringSelectMenuMapById = null;
            return;
        }

        int size = discordSelectMenuList.size();

        Map<String, IDiscordEntitySelectMenu> discordEntitySelectMenuMapById = new HashMap<>(size);
        Map<String, IDiscordStringSelectMenu> discordStringSelectMenuMapById = new HashMap<>(size);

        for (IDiscordSelectMenu discordSelectMenu : discordSelectMenuList) {
            if (discordSelectMenu == null) {
                continue;
            }

            String discordSelectMenuId = discordSelectMenu.getDiscordSelectMenuId();

            if (discordSelectMenuId == null) {
                continue;
            }

            if (discordSelectMenu instanceof IDiscordEntitySelectMenu discordEntitySelectMenu) {
                discordEntitySelectMenuMapById.put(discordSelectMenuId, discordEntitySelectMenu);
            } else if (discordSelectMenu instanceof IDiscordStringSelectMenu discordStringSelectMenu) {
                discordStringSelectMenuMapById.put(discordSelectMenuId, discordStringSelectMenu);
            }
        }

        this.discordEntitySelectMenuMapById = Collections.unmodifiableMap(discordEntitySelectMenuMapById);
        this.discordStringSelectMenuMapById = Collections.unmodifiableMap(discordStringSelectMenuMapById);
    }

    public Map<String, IDiscordEntitySelectMenu> getDiscordEntitySelectMenuMapById() {
        return this.discordEntitySelectMenuMapById;
    }

    public Map<String, IDiscordStringSelectMenu> getDiscordStringSelectMenuMapById() {
        return this.discordStringSelectMenuMapById;
    }
}
