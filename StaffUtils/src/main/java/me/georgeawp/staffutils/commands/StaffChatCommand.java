package me.georgeawp.staffutils.commands;

import me.georgeawp.staffutils.StaffUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class StaffChatCommand implements CommandExecutor {


    //For Chat Colour
    String Color(String c) {
        c = ChatColor.translateAlternateColorCodes('&' , c);
        return c; }



    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        if (sender instanceof Player) {
            //The sender is a player
            Player p = (Player) sender;

           //The player does not have permission to use /staffchat
            if(!p.hasPermission("staffutils.staffchat.usestaffchat")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , StaffUtils.getInstance().config.getString("commands.staffchat.nopermission")));
            }

            //The player does have permission to use /staffchat
            else if (args.length == 0) {

                // /staffchat -> retruns with a correct usage message
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , StaffUtils.getInstance().config.getString("commands.staffchat.correctusage")));
                return true;
            } else if (args.length > 0) {

                // /staffchat [memssage]

                        //Build string for message
                final StringBuilder msgBuilder = new StringBuilder();
                for (int i = 1; i < args.length; ++i) {
                    msgBuilder.append(args[i]).append(" ");
                }
                final String message = msgBuilder.toString().trim();
                String scmessage = StaffUtils.getInstance().config.getString("commands.staffchat.prefix") + p.getDisplayName() + ": ";
                for (final String c : args) {
                    scmessage = scmessage + "&f" + c + "&7 ";
                }

                for (final Player staff : Bukkit.getOnlinePlayers()) {
                    //The player does not have permission to view
                    if (!staff.hasPermission("staffutils.staffchat.view")) {
                        return true;
                    } else {
                        staff.sendMessage(ChatColor.translateAlternateColorCodes('&' , scmessage));
                        return true;

                    }
                }
            }

        } else {

            // The sender is console
           sender.sendMessage(ChatColor.RED + "[STAFFCHAT] ERROR You may not use this command as a console!");
           return true;
        }
            return false;
    }
}
