package top.hoshino9.mousehelper.client

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import top.hoshino9.mousehelper.keybind.KeyBindings
import top.hoshino9.mousehelper.common.Proxy as CommonProxy

class Proxy : CommonProxy() {
    override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)

        KeyBindings.register()
    }
}