package pers.gwyog.gtneioreplugin;

import java.util.HashSet;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.init.Items;
import pers.gwyog.gtneioreplugin.util.GT5OreLayerHelper;
import pers.gwyog.gtneioreplugin.util.GT5OreSmallHelper;

@Mod(modid = GTNEIOrePlugin.MODID, name = GTNEIOrePlugin.NAME, version = GTNEIOrePlugin.VERSION, dependencies = "required-after:galacticraft;required-after:gregtech;required-after:NotEnoughItems")
public class GTNEIOrePlugin {
    public static final String MODID = "gtneioreplugin";
    public static final String NAME = "GT NEI Ore Plugin Pixian revision";
    public static final String VERSION = "@version@";
    public static boolean csv = false;
    public static String CSVname; 
    public static HashSet OreV=new HashSet();
    
    @Mod.Instance(MODID)
    public static GTNEIOrePlugin instance;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    	Config c = new Config(event, this.MODID+".cfg");
    	csv = c.tConfig.getBoolean("print csv","ALL", false, "princsv, you need apache commons collections to be injected in the minecraft jar.");
    	CSVname = c.tConfig.getString("CSV_name", "ALL", event.getModConfigurationDirectory()+"/Pixian-Oresheet.csv", "rename the oresheet here, it will appear in /config");
    	c.save();
    }
    
    @EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        if (event.getSide() == Side.CLIENT) {
        	new GT5OreLayerHelper();
            new GT5OreSmallHelper();
                if (csv) {
                	new pers.gwyog.gtneioreplugin.util.CSVMaker().run();
                }
            	}
            }

}
