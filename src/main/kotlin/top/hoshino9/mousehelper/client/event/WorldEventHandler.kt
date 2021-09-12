package top.hoshino9.mousehelper.client.event

import net.minecraft.client.Minecraft
import net.minecraft.util.EnumHand
import net.minecraft.util.math.RayTraceResult
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import top.hoshino9.mousehelper.keybind.KeyEventHandler
import java.lang.reflect.Field

@Mod.EventBusSubscriber
object WorldEventHandler {
    private val mc: Minecraft by lazy { Minecraft.getMinecraft() }

    private val fieldRightClickDelayTimer: Field by lazy {
        val srgName = "field_71467_ac"
        val deobfName = "rightClickDelayTimer"

        val clazz = Minecraft::class.java
        val field: Field = clazz.declaredFields.first { it.name == deobfName || it.name == srgName }

        field.isAccessible = true
        field
    }

    @JvmStatic
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun onTick(event: TickEvent.ClientTickEvent) {
        val mouseOver = mc.objectMouseOver ?: null

        if (KeyEventHandler.keepClick) {
            if (mouseOver != null) {
                if (mouseOver.typeOfHit === RayTraceResult.Type.BLOCK) {
                    if (!mc.world.isAirBlock(mouseOver.blockPos)) {
                        mc.playerController.onPlayerDamageBlock(mouseOver.blockPos, mouseOver.sideHit)
                    }
                }
            }
        }

        if (KeyEventHandler.keepUse) {
            if (mouseOver != null) {
                if (mouseOver.typeOfHit === RayTraceResult.Type.BLOCK) {
                    if (!mc.world.isAirBlock(mouseOver.blockPos)) {
                        for (hand in EnumHand.values()) {
                            mc.playerController.processRightClickBlock(
                                mc.player,
                                mc.world,
                                mouseOver.blockPos,
                                mouseOver.sideHit,
                                mc.objectMouseOver.hitVec,
                                hand
                            )
                        }
                    }
                }
            }
        }
    }
}