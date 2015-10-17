package me.vAdmin.com;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	Logger myLogger = Bukkit.getLogger();
	PluginManager pm = Bukkit.getServer().getPluginManager();
	ArrayList<String> adminMode = new ArrayList<String>();
	ArrayList<String> vis = new ArrayList<String>();
	ArrayList<String> respawn = new ArrayList<String>();
	
	public void onEnable() {
		pm.registerEvents(this, this);
		this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.BLUE + "=========================");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.GREEN + "   vAdmin Activated!!   ");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.GREEN + "    By: SnipingVSATS    ");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.BLUE + "=========================");
        this.getServer().getConsoleSender().sendMessage("");
	}
	
	public void onLogin() {
		
	}
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.BLUE + "=========================");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.GREEN + "   vAdmin DeActivated!!   ");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.GREEN + "    By: SnipingVSATS    ");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage("");
        this.getServer().getConsoleSender().sendMessage((Object)ChatColor.BLUE + "=========================");
        this.getServer().getConsoleSender().sendMessage("");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player p = (Player) sender;
		String aAdmin = sender.getName();
		if(commandLabel.equalsIgnoreCase("admin")) {
			if(p.hasPermission("vAdmin.use")) {
				if(!(adminMode.contains(aAdmin))) {
					adminMode.add(aAdmin);
					p.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "You are now in Admin mode.");
					p.getInventory().clear();
					p.setGameMode(GameMode.CREATIVE);
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 2));
					ItemStack wds = new ItemStack(Material.COMPASS);
			        ItemMeta wdsMeta = wds.getItemMeta();
			        ItemStack wdw = new ItemStack(Material.BOOK);
			        ItemMeta wdwMeta = wdw.getItemMeta();
			        ItemStack td = new ItemStack(Material.RED_ROSE);
			        ItemMeta tdMeta = td.getItemMeta();
			        ItemStack dl = new ItemStack(Material.CLAY_BALL);
			        ItemMeta dlMeta = dl.getItemMeta();
			        ItemStack al = new ItemStack(Material.DIAMOND_SWORD);
			        ItemMeta alMeta = dl.getItemMeta();
			 
			        wdsMeta.setDisplayName(ChatColor.RED + "Teleporter");
			        wds.setItemMeta(wdsMeta);
			 
			        wdwMeta.setDisplayName(ChatColor.GREEN + "Inventory Checker");
			        wdw.setItemMeta(wdwMeta);
			 
			        tdMeta.setDisplayName(ChatColor.GOLD + "Nearest Player");
			        td.setItemMeta(tdMeta);
			 
			        dlMeta.setDisplayName(ChatColor.AQUA + "Invisibility");
			        dl.setItemMeta(dlMeta);
			        
			        alMeta.setDisplayName(ChatColor.YELLOW + "Admin Mode");
			        al.setItemMeta(alMeta);
			        
			        p.getInventory().setItem(0, wds);
			        p.getInventory().setItem(1, wdw);
			        p.getInventory().setItem(4, al);
			        p.getInventory().setItem(7, td);
			        p.getInventory().setItem(8, dl);
			        
			        for (Player players : Bukkit.getOnlinePlayers())
                    {
			          if(players.hasPermission("vAdmin.use")) {
			        	  players.showPlayer(p);
			          } else {
                        players.hidePlayer(p);
			          }
                    }
			        
				} else {
					adminMode.remove(aAdmin);
					p.sendMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "You are no longer in admin mode."); 
					p.getInventory().clear();
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					for (Player players : Bukkit.getOnlinePlayers())
                    {
                        players.showPlayer(p);
                    }

				}
			} else {
				p.sendMessage(ChatColor.RED + "Nice try.");
			}
		} else if(commandLabel.equalsIgnoreCase("vis")) {
			if(adminMode.contains(p.getName())) {
				if(!(vis.contains(sender.getName()))) {
					vis.add(sender.getName());
					for (Player players : Bukkit.getOnlinePlayers())
                    {
                        players.showPlayer(p);
                    }
					p.sendMessage(ChatColor.BLUE + "You are now visble to everyone.");
				} else {
					if(vis.contains(sender.getName())) {
						vis.remove(sender.getName());
						p.sendMessage(ChatColor.BLUE + "You are no longer visble to everyone.");
						for (Player players : Bukkit.getOnlinePlayers())
	                    {
	                        players.hidePlayer(p);
	                    }
					}
				}
			}
		}
		return true;
	}
	
	@EventHandler
	public void onClick1(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if(adminMode.contains(p.getName())) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK 
				|| e.getAction() == Action.RIGHT_CLICK_AIR 
					|| e.getAction() == Action.LEFT_CLICK_BLOCK 
						|| e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Admin Mode")) {
					p.performCommand("admin");
				} 
			}
		}
	}
	
	@EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer(); 
				
		if(adminMode.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = (Player) e.getPlayer();
		  if(adminMode.contains(p.getName())) {
			 if(p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Inventory Checker")) {
			  p.performCommand("invsee " + ((Player) e.getRightClicked()).getName());
			 }
		 } 
	}
	
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e) {
		if(adminMode.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if(adminMode.contains(p.getName())) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK 
				|| e.getAction() == Action.RIGHT_CLICK_AIR 
					|| e.getAction() == Action.LEFT_CLICK_BLOCK 
						|| e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Invisibility")) {
					p.performCommand("vis");
				} 
			}
		}
	}
	@EventHandler
	public void onClick2(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		if(adminMode.contains(p.getName())) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK 
				|| e.getAction() == Action.RIGHT_CLICK_AIR 
					|| e.getAction() == Action.LEFT_CLICK_BLOCK 
						|| e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Nearest Player")) {
					p.performCommand("near");
				} 
			}
		}
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		
		if(adminMode.contains(p.getName())) {
			adminMode.remove(p.getName());
			respawn.add(p.getName());
			e.getDrops().clear();
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if(respawn.contains(p.getName())) {
			Bukkit.dispatchCommand(p, "admin");
			respawn.remove(p.getName());
		}
	}
}