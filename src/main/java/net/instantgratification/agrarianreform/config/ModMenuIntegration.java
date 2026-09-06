// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.dasik.social.api.config.GuiHelper;

/**
 * ModMenuIntegration: Entrypoint registration for ModMenu
 *
 * Dynamically binds the configuration GUI builder screen factory.
 *
 * Verified against: ModMenuApi.java (ModMenu 26.2+)
 */
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return GuiHelper.getOptionalFactory(
                "agrarian_reform",
                "net.instantgratification.agrarianreform.config.YaclScreenHelper",
                "createScreen"
        );
    }
}
