package WayofTime.bloodmagic.api.teleport;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class TeleportQueue
{
    private static TeleportQueue INSTANCE = new TeleportQueue();
    private static List<ITeleport> queue;

    public static TeleportQueue getInstance()
    {
        return INSTANCE;
    }

    private TeleportQueue()
    {
        queue = new ArrayList<ITeleport>();
    }

    public void addITeleport(ITeleport iTeleport)
    {
        queue.add(iTeleport);
    }

    @SubscribeEvent
    public void serverTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END)
        {
            return;
        }

        for (ITeleport iTeleport : queue)
        {
            iTeleport.teleport();
        }

        queue.clear();
    }
}
