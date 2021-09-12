package top.hoshino9.mousehelper.keybind

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber
object KeyEventHandler {
    var keepClick: Boolean = false
        private set

    var keepUse: Boolean = false
        private set

    @JvmStatic
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (KeyBindings.keepClick.isPressed) {
            keepClick = ! keepClick
        }

        if (KeyBindings.keepUse.isPressed) {
            keepUse = ! keepUse
        }
    }
}