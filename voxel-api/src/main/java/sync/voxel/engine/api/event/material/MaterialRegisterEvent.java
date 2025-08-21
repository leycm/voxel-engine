package sync.voxel.engine.api.event.material;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import sync.voxel.engine.api.material.VoxMaterial;

public class MaterialRegisterEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final VoxMaterial material;
    private boolean cancelled;

    public MaterialRegisterEvent(VoxMaterial material) {
        this.material = material;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public VoxMaterial getMaterial() {
        return material;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
