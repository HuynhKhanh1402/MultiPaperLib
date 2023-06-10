package me.khanh.plugins.multipaperlib;

import com.github.puregero.multilib.MultiLib;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import me.khanh.plugins.multipaperlib.event.MultiPaperPlayerJoinEvent;
import me.khanh.plugins.multipaperlib.event.MultiPaperPlayerQuitEvent;
import me.khanh.plugins.multipaperlib.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiPaperLib extends JavaPlugin {
    @Getter
    private Gson gson = new Gson();

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

        MultiLib.onString(this, "MultiLibPlayerJoin", s -> {

            JsonObject jsonObject = gson.fromJson(s, JsonObject.class);

            String playerName = jsonObject.get("name").getAsString();
            String joinMessage = jsonObject.get("message").getAsString();

            for (Player player: MultiLib.getAllOnlinePlayers()){

                if (player.getName().equals(playerName)){
                    Bukkit.getPluginManager().callEvent(new MultiPaperPlayerJoinEvent(player, joinMessage));
                }

            }

        });

        MultiLib.onString(this, "MultiLibPlayerQuit", s -> {

            JsonObject jsonObject = gson.fromJson(s, JsonObject.class);

            String playerName = jsonObject.get("name").getAsString();
            String quitMessage = jsonObject.get("message").getAsString();
            PlayerQuitEvent.QuitReason quitReason = PlayerQuitEvent.QuitReason.valueOf(jsonObject.get("reason").getAsString());

            for (Player player: MultiLib.getAllOnlinePlayers()){

                if (player.getName().equals(playerName)){
                    Bukkit.getPluginManager().callEvent(new MultiPaperPlayerQuitEvent(player, quitMessage, quitReason));
                }

            }

        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
