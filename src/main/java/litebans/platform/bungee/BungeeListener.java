package litebans.platform.bungee;

import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import net.md_5.bungee.api.plugin.Listener;

@MetadataAnnotation(
   a = 1
)
public interface BungeeListener extends ListenerHandle, Listener {
}
