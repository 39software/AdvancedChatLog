package io.github.darkkronicle.advancedchatlog;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import fi.dy.masa.malilib.util.StringUtils;
import io.github.darkkronicle.advancedchatlog.config.ChatLogConfigStorage;
import io.github.darkkronicle.advancedchatcore.chat.ChatHistory;
import io.github.darkkronicle.advancedchatcore.config.gui.GuiConfigHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ChatLogInitHandler implements IInitializationHandler {

    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance().registerConfigHandler(AdvancedChatLog.MOD_ID, new ChatLogConfigStorage());
        GuiConfigHandler.getInstance().addGuiSection(GuiConfigHandler.createGuiConfigSection(
                StringUtils.translate("advancedchatlog.tab.general"), ChatLogConfigStorage.General.OPTIONS
        ));

        // Register on new message
        ChatHistory.getInstance().addOnMessage(chatMessage -> System.out.println("Chat message happened!"));
        // Register on the clear
        ChatHistory.getInstance().addOnClear(() -> System.out.println(ChatLogConfigStorage.General.STRING_STUFF.config.getStringValue()));
    }

}