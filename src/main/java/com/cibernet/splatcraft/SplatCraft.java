package com.cibernet.splatcraft;


import com.cibernet.splatcraft.commands.*;
import com.cibernet.splatcraft.proxy.CommonProxy;
import com.cibernet.splatcraft.scoreboard.CommandColorScores;
import com.cibernet.splatcraft.scoreboard.SplatcraftScoreboardHandler;
import com.cibernet.splatcraft.utils.InkColors;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Mod(name = SplatCraft.NAME, modid = SplatCraft.MODID, version = SplatCraft.VERSION)
public class SplatCraft
{
	public static final String MODID = "splatcraft";
	public static final String NAME = "Splatcraft";
	public static final String VERSION = "2.2.2";
	public static final String SHORT = "SC";

	//TODO config
	public static final int DEFAULT_INK = InkColors.INK_BLACK.getColor();
	
	public static Logger logger;
	public static boolean disableEyeHeight;
	
	@Mod.Instance(SplatCraft.MODID)
	public static SplatCraft instance;
	
	@SidedProxy
			(
					clientSide ="com.cibernet.splatcraft.proxy.ClientProxy",
					serverSide ="com.cibernet.splatcraft.proxy.CommonProxy"
			)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		disableEyeHeight = Loader.isModLoaded("moreplayermodels");
		
		logger = Logger.getLogger(MODID);
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
	
	@Mod.EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		
		File dataFile = event.getServer().getActiveAnvilConverter().getFile(event.getServer().getFolderName(), "\\data\\SplatCraftData.dat");
		if(dataFile != null && dataFile.exists())
		{
			NBTTagCompound nbt = null;
			try
			{
				nbt = CompressedStreamTools.readCompressed(new FileInputStream(dataFile));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			if(nbt != null)
				SplatcraftScoreboardHandler.readFromNBT(nbt);
			
		}
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandTurfWar());
		event.registerServerCommand(new CommandClearInk());
		event.registerServerCommand(new CommandSplatCraftGamerules());
		event.registerServerCommand(new CommandSetInkColor());
		event.registerServerCommand(new CommandColorScores());
	}
}
