package me.khanh.plugins.multipaperlib.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MultiPaperPlayerJoinEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String  joinMessage;


    public MultiPaperPlayerJoinEvent(@NotNull Player playerJoined, @Nullable String joinMessage) {
        super(playerJoined);
        this.joinMessage = joinMessage;
    }


    public @Nullable String getJoinMessage() {
        return this.joinMessage;
    }

    public void setJoinMessage(@Nullable String joinMessage) {
        this.joinMessage = joinMessage;
    }

    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }
}
