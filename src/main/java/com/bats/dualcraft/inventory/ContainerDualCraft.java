package com.bats.dualcraft.inventory;

import com.bats.dualcraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

/**
 * Created by Bats on 4/7/2015.
 */
public class ContainerDualCraft extends Container
{
    public InventoryCrafting craftMatrix1 = new InventoryCrafting(this, 3, 3);
    public InventoryCrafting craftMatrix2 = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult1 = new InventoryCraftResult();
    public IInventory craftResult2 = new InventoryCraftResult();
    private World world;
    private int x;
    private int y;
    private int z;

    public ContainerDualCraft(InventoryPlayer playerInv, World world, int x, int y, int z)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        //Output Slots
        this.addSlotToContainer(new SlotCrafting(playerInv.player, this.craftMatrix1, this.craftResult1, 0, 124, 27));
        this.addSlotToContainer(new SlotCrafting(playerInv.player, this.craftMatrix2, this.craftResult2, 0, 124, 86));

        //Top Grid
        for (int l = 0; l < 3; ++l)
            for (int i1 = 0; i1 < 3; ++i1)
                this.addSlotToContainer(new Slot(this.craftMatrix1, i1 + l * 3, 30 + i1 * 18, 9 + l * 18));

        //Lower Grid
        for (int j = 0; j < 3; ++j)
            for (int j1 = 0; j1 < 3; ++j1)
                this.addSlotToContainer(new Slot(this.craftMatrix2, j1 + j * 3, 30 + j1 * 18, 68 + j * 18));

        //PlayerInv Main
        for (int p = 0; p < 3; ++p)
            for (int p1 = 0; p1 < 9; ++p1)
                this.addSlotToContainer(new Slot(playerInv, p1 + p * 9 + 9, 8 + p1 * 18, 127 + p * 18));

        //Player Hotbar
        for (int h = 0; h < 9; ++h)
        {
            this.addSlotToContainer(new Slot(playerInv, h, 8 + h * 18, 185));
        }

        this.onCraftMatrixChanged(this.craftMatrix1);
        this.onCraftMatrixChanged(this.craftMatrix2);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.world.getBlock(this.x, this.y, this.z) != Blocks.crafting_table ? false : player.getDistanceSq((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    @Override
    public void onCraftMatrixChanged(IInventory matrix)
    {
        this.craftResult1.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix1, this.world));
        this.craftResult2.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix2, this.world));
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!this.world.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftMatrix1.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }

            for (int j = 0; j < 9; ++j)
            {
                ItemStack itemstack = this.craftMatrix2.getStackInSlotOnClosing(j);

                if (itemstack != null)
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        LogHelper.info(slotID);
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID == 0 || slotID == 1)
            {
                if (!this.mergeItemStack(itemstack1, 20, 56, true))
                {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotID >= 20 && slotID < 47)
            {
                if (!this.mergeItemStack(itemstack1, 47, 56, false))
                {
                    return null;
                }
            } else if (slotID >= 47 && slotID < 56)
            {
                if (!this.mergeItemStack(itemstack1, 20, 47, false))
                {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 20, 56, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            } else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }
}
