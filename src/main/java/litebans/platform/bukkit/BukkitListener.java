package litebans.platform.bukkit;

import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import org.bukkit.event.Listener;

@MetadataAnnotation(
   a = 0
)
public interface BukkitListener extends ListenerHandle, Listener {
}
