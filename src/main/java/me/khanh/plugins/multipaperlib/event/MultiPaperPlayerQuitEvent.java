package me.khanh.plugins.multipaperlib.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MultiPaperPlayerQuitEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Component quitMessage;
    private final PlayerQuitEvent.QuitReason reason;

    public MultiPaperPlayerQuitEvent(@NotNull Player who, @Nullable String quitMessage, @Nullable PlayerQuitEvent.QuitReason quitReason) {
        super(who);
        this.quitMessage = quitMessage != null ? LegacyComponentSerializer.legacySection().deserialize(quitMessage) : null;
        this.reason = quitReason == null ? PlayerQuitEvent.QuitReason.DISCONNECTED : quitReason;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public PlayerQuitEvent.QuitReason getReason() {
        return this.reason;
    }

    public static enum QuitReason {
        DISCONNECTED,
        KICKED,
        TIMED_OUT,
        ERRONEOUS_STATE;

        private QuitReason() {
        }
    }
}
