package com.cibernet.splatcraft.registries;

import com.cibernet.splatcraft.Splatcraft;
import com.cibernet.splatcraft.items.BlockItem;
import com.cibernet.splatcraft.items.InkwellItem;
import com.cibernet.splatcraft.items.ShooterItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static com.cibernet.splatcraft.registries.SplatcraftItemGroups.*;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class SplatcraftItems
{
	
	//Shooters
	public static final Item splattershot = new ShooterItem().setRegistryName("splattershot");
	
	//Rollers
	
	//Chargers
	
	//Dualies
	
	//Sloshers
	
	//Ink Tanks
	
	//Vanity
	
	//Materials
	
	public static final Item sardinium = new Item(new Item.Properties().group(GROUP_GENERAL)).setRegistryName("sardinium");
	public static final Item sardiniumBlock = new BlockItem(SplatcraftBlocks.sardiniumBlock).setRegistryName("sardinium_block");
	public static final Item sardiniumOre = new BlockItem(SplatcraftBlocks.sardiniumOre).setRegistryName("sardinium_ore");
	public static final Item powerEgg = new Item(new Item.Properties().group(GROUP_GENERAL)).setRegistryName("power_egg");
	public static final Item powerEggBlock = new BlockItem(SplatcraftBlocks.powerEggBlock).setRegistryName("power_egg_block");
	
	//Map Items
	public static final Item inkwell = new InkwellItem().setRegistryName("inkwell");
	
	//Remotes
	
	//Misc
	
	@SubscribeEvent
	public static void itemInit(final RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(splattershot);
		
		registry.register(sardinium);
		registry.register(sardiniumBlock);
		registry.register(sardiniumOre);
		registry.register(powerEgg);
		registry.register(powerEggBlock);
		registry.register(inkwell);
	}
}