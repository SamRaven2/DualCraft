package com.bats.dualcraft.client.gui;

import com.bats.dualcraft.inventory.ContainerDualCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bats on 4/7/2015.
 */

@SideOnly(Side.CLIENT)
public class GuiDualCraft extends GuiContainer
{
    private static final ResourceLocation dualWorkbenchTexture = new ResourceLocation("dualcraft:textures/guis/guiDualCraft.png");

    public GuiDualCraft(InventoryPlayer playerInv, World world, int x, int y, int z)
    {
        super(new ContainerDualCraft(playerInv, world, x, y, z));
        xSize = 176;
        ySize = 209;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float arg1, int arg2, int arg3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(dualWorkbenchTexture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
