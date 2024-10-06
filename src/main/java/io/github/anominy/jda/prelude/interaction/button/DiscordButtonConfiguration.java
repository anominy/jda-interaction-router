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

package io.github.anominy.jda.prelude.interaction.button;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DiscordButtonConfiguration {
    private final Map<String, IDiscordButton> discordButtonMapById;

    public DiscordButtonConfiguration(List<IDiscordButton> discordButtonList) {
        if (discordButtonList == null) {
            this.discordButtonMapById = null;
            return;
        }

        int size = discordButtonList.size();

        Map<String, IDiscordButton> discordButtonMapById = new HashMap<>(size);

        for (IDiscordButton discordButton : discordButtonList) {
            if (discordButton == null) {
                continue;
            }

            String discordButtonId = discordButton.getDiscordButtonId();

            if (discordButtonId == null) {
                continue;
            }

            discordButtonMapById.put(discordButtonId, discordButton);
        }

        this.discordButtonMapById = Collections.unmodifiableMap(discordButtonMapById);
    }

    public Map<String, IDiscordButton> getDiscordButtonMapById() {
        return this.discordButtonMapById;
    }
}
