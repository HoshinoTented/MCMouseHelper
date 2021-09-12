package top.hoshino9.mousehelper

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import top.hoshino9.mousehelper.common.Proxy

@Mod(
    modid = MouseHelper.MODID,
    name = MouseHelper.NAME,
    version = MouseHelper.VERSION
)
class MouseHelper {
    companion object {
        const val MODID = "mousehelper"
        const val NAME = "Mouse Helper"
        const val VERSION = "1.0"

        @SidedProxy(clientSide = "top.hoshino9.mousehelper.client.Proxy", serverSide = "top.hoshino9.mousehelper.common.Proxy")
        lateinit var proxy: Proxy
        lateinit var logger: Logger
    }


    @Mod.Instance(MODID)
    private lateinit var _instance: MouseHelper

    val instance: MouseHelper by lazy {
        _instance
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }
}