package me.khanh.plugins.multipaperlib.listener;

import com.github.puregero.multilib.MultiLib;
import com.google.gson.JsonObject;
import lombok.Getter;
import me.khanh.plugins.multipaperlib.MultiPaperLib;
import me.khanh.plugins.multipaperlib.event.MultiPaperPlayerJoinEvent;
import me.khanh.plugins.multipaperlib.event.MultiPaperPlayerQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @Getter
    private final MultiPaperLib plugin;

    public PlayerListener(MultiPaperLib plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Bukkit.getPluginManager().callEvent(new MultiPaperPlayerJoinEvent(event.getPlayer(), event.getJoinMessage()));
            sendPlayerJoinNotify(event.getPlayer().getName(), event.getJoinMessage());
        }, 5);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Bukkit.getPluginManager().callEvent(new MultiPaperPlayerQuitEvent(event.getPlayer(), event.getQuitMessage(), event.getReason()));
        sendPlayerQuitNotify(event);
    }

    private void sendPlayerJoinNotify(String playerName, String message){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", playerName);
        jsonObject.addProperty("message", message);

        MultiLib.notify("MultiLibPlayerJoin", jsonObject.toString());
    }

    private void sendPlayerQuitNotify(PlayerQuitEvent event){

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", event.getPlayer().getName());
        jsonObject.addProperty("message", event.getQuitMessage());
        jsonObject.addProperty("reason", (event.getReason() == null ? PlayerQuitEvent.QuitReason.DISCONNECTED : event.getReason()).toString());

        MultiLib.notify("MultiLibPlayerQuit", jsonObject.toString());
    }


}
