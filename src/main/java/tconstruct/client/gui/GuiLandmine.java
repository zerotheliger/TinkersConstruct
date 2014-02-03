package tconstruct.client.gui;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import tconstruct.inventory.ContainerLandmine;
import tconstruct.util.landmine.behavior.Behavior;

/**
 * 
 * @author fuj1n
 * 
 */
public class GuiLandmine extends GuiContainer
{

    ContainerLandmine container;

    ResourceLocation background = new ResourceLocation("tinker:textures/gui/landmine.png");

    public GuiLandmine(ContainerLandmine par1Container)
    {
        super(par1Container);
        container = par1Container;
    }

    @Override
    protected void func_146285_a (ItemStack par1ItemStack, int par2, int par3)
    {
        List list = par1ItemStack.getTooltip(this.field_146297_k.thePlayer, this.field_146297_k.gameSettings.advancedItemTooltips);

        Behavior b = Behavior.getBehaviorFromStack(par1ItemStack);
        if (b != null)
        {
            b.getInformation(par1ItemStack, list);
        }

        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, "\u00a7" + Integer.toHexString(par1ItemStack.getRarity().rarityColor.func_96298_a()) + (String) list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String) list.get(k));
            }
        }

        FontRenderer font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
        drawHoveringText(list, par2, par3, (font == null ? field_146289_q : font));
    }

    @Override
    protected void func_146979_b (int i, int j)
    {
        if (container.te != null)
        {
            field_146289_q.drawString(container.te.func_145825_b(), 8, 5, 4210752);
        }
        else
        {
            field_146289_q.drawString("Landmine", 8, 5, 4210752);
        }
        field_146289_q.drawString(StatCollector.translateToLocal("container.inventory"), 8, field_147000_g - 96 + 3, 4210752);
    }

    @Override
    protected void func_146976_a (float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        field_146297_k.renderEngine.bindTexture(background);
        int x = (field_146294_l - field_146999_f) / 2;
        int y = (field_146295_m - field_147000_g) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, field_146999_f, field_147000_g);
    }

}