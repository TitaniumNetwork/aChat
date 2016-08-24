package com.TitaniumNetwork.aChat;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.DARK_PURPLE;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.LIGHT_PURPLE;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.ChatColor.YELLOW;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mkremins.fanciful.FancyMessage;

public class Core extends JavaPlugin implements Listener{

	@Override
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
    static void console(String msg){
    	Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
	
	private static String chat(String rank, String rawRank, Player p, String message, ChatColor pName, ChatColor cColor) {
		FancyMessage testt = new FancyMessage("[")
				.color(GRAY)
				.then("Hub")
				.color(RED)
				.command("/hub")
				.tooltip(GOLD + "Click to teleport to the " + RED + "Hub" + GOLD + "!")
				.then("] [") 
				.color(GRAY)
				.then(rank)
				.tooltip(GRAY + "Rank: " + "[" + rank + GRAY + "]")
				.then("] ")
				.color(GRAY)
				.then(p.getName())
				.color(pName)
				.suggest("/msg " + p.getName() + " ")
				.tooltip(GOLD + "Click to message the " + rawRank + ": " + pName + p.getName())
				.then(": ")
				.color(WHITE)
				.then(message)
				.color(cColor);
		for(Player all : Bukkit.getOnlinePlayers()){
			testt.send(all);
		}
		console(WHITE + "CHAT: " + GRAY + "[" + RED + "Hub" + GRAY + "] [" + rank + GRAY + "] " + pName + p.getName() + WHITE + ": " + cColor + message);
		return testt.toJSONString();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String message = e.getMessage();
		e.setCancelled(true);
		if(p.hasPermission("rank.player")){
				String rank = ChatColor.YELLOW + "Player";
				chat(rank, "Player", p, message, YELLOW, WHITE);
				return;
		}
		if(p.hasPermission("rank.vip")){
				String rank = ChatColor.LIGHT_PURPLE + "VIP";
				chat(rank, "VIP", p, message, DARK_PURPLE, LIGHT_PURPLE);
				return;
		}
		if(p.hasPermission("rank.jrmod")){
			String rank = ChatColor.GREEN + "JrMod";
			chat(rank, "JrMod", p, message, AQUA, GREEN);
			return;
		}
		if(p.hasPermission("rank.mod")){
			String rank = ChatColor.DARK_GREEN + "Mod";
			chat(rank, "Mod", p, message, AQUA, DARK_GREEN);
			return;
		}
		if(p.hasPermission("rank.srmod")){
			String rank = ChatColor.RED + "SrMod";
			chat(rank, "SrMod", p, message, AQUA, RED);
			return;
		}
		if(p.hasPermission("rank.admin")){
			String rank = ChatColor.GOLD + "Admin";
			chat(rank, "Admin", p, message, AQUA, GOLD);
			return;
		}
		if(p.hasPermission("rank.owner")){
			String rank = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Owner";
			chat(rank, "Owner", p, message, AQUA, GOLD);
			return;
		}
	}
}