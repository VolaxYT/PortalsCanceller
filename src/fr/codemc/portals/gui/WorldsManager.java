package fr.codemc.portals.gui;

import fr.codemc.portals.PortalsManager;
import fr.codemc.portals.utils.ItemBuilder;
import fr.codemc.portals.utils.GuiBuilder;
import fr.codemc.portals.utils.MetadataUtil;
import fr.codemc.portals.utils.Skull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WorldsManager implements GuiBuilder {
    @Override
    public String name() {
        return "§eWorlds Manager";
    }

    @Override
    public int getSize() {
        return (int) Math.ceil(Bukkit.getWorlds().size() / 9.0) * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        int slot = 0;
        List<World> normal = new ArrayList<>();
        List<World> nether = new ArrayList<>();
        List<World> end = new ArrayList<>();

        for(World world : Bukkit.getWorlds()){
            if(world.getEnvironment().equals(World.Environment.NORMAL))
                normal.add(world);

            if(world.getEnvironment().equals(World.Environment.NETHER))
                nether.add(world);

            if(world.getEnvironment().equals(World.Environment.THE_END))
                end.add(world);
        }

        for(World world : normal){
            inv.setItem(slot, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setSkullOwner("BlockminersTV").setName("§eConfigure the world §6" + world.getName()).toItemStack());
            slot++;
        }

        for(World world : nether){
            inv.setItem(slot, new ItemBuilder(Skull.getCustomSkull("http://textures.minecraft.net/texture/bb508c779f312daff337d137f34dd365a3f9c132bd92eb0c5ffab7c5b4a55c5")).setName("§eConfigure the world §6" + world.getName()).toItemStack());
            slot++;
        }

        for(World world : end){
            inv.setItem(slot, new ItemBuilder(Skull.getCustomSkull("http://textures.minecraft.net/texture/19f21f5d883316fd65a9366f32a33013182e3381dec21c17c78355d9bf4f0")).setName("§eConfigure the world §6" + world.getName()).toItemStack());
            slot++;
        }
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        if(current == null || !current.hasItemMeta() || current.getType() == null || current.getItemMeta().getDisplayName() == null)
            return;

        String worldName = current.getItemMeta().getDisplayName().substring(24);
        if(Bukkit.getWorlds().contains(Bukkit.getWorld(worldName))){
            MetadataUtil.setMetadata("lastMenu", worldName, player);
            PortalsManager.getInstance().getGuiManager().open(player, WorldSettings.class);
        }
    }
}
