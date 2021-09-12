package top.hoshino9.mousehelper.keybind

import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry

object KeyBindings {
    const val CATEGORY = "key.categories.mousehelper"
    const val DESCRIPTION_PREFIX = "key.mousehelper"

    val keepClick: KeyBinding = KeyBinding("$DESCRIPTION_PREFIX.keepclick", 0, CATEGORY)
    val keepUse: KeyBinding = KeyBinding("$DESCRIPTION_PREFIX.keepuse", 0, CATEGORY)

    fun register() {
        ClientRegistry.registerKeyBinding(keepClick)
        ClientRegistry.registerKeyBinding(keepUse)
    }
}