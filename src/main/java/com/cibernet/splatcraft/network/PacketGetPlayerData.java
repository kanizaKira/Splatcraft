package com.cibernet.splatcraft.network;

import com.cibernet.splatcraft.SplatCraft;
import com.cibernet.splatcraft.world.save.SplatCraftPlayerData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGetPlayerData implements IMessage
{

    private boolean messageValid;


    public PacketGetPlayerData()
    {
        messageValid = true;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        try
        {
        } catch (IndexOutOfBoundsException e)
        {
            SplatCraft.logger.info(e.toString());
        }
        messageValid = true;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        if(!messageValid)
            return;
    }

    public static class Handler implements IMessageHandler<PacketGetPlayerData, IMessage>
    {

        @Override
        public IMessage onMessage(PacketGetPlayerData message, MessageContext ctx) {
            if(!message.messageValid && ctx.side != Side.SERVER)
                return null;
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> process(message, ctx));
            return null;
        }

        void process(PacketGetPlayerData message, MessageContext ctx)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            SplatCraftPlayerData.writeToNBT(nbt);
            SplatCraftPacketHandler.instance.sendTo(new PacketReturnPlayerData(ctx.getServerHandler().player.getUniqueID(), nbt), ctx.getServerHandler().player);

        }
    }
}
