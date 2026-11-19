package litebans.platform.fabric;

import com.mojang.brigadier.CommandDispatcher;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import litebans.chat.ComponentParser;
import litebans.platform.Sender;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FabricServerAdapter {
   void handleObject(Object var1, String var2);

   Object getObject();

   String getString(Object var1);

   boolean check();

   boolean check2();

   @Nullable
   List getList();

   void handleRunnable(Runnable var1);

   void handleObject2(Object var1, String var2);

   int getInt();

   Object getObject(Object var1);

   void handleObject3(@Nullable Object var1, String var2);

   void handleObject(Object var1, String var2, byte[] var3);

   Sender getSender(Object var1);

   Sender getSender2(Object var1);

   Sender getSender(Object var1, @Nullable Supplier var2);

   ComponentParser getComponentParser();

   void setMinecraftServer(MinecraftServer var1);

   void handleFunction(Function var1);

   void handleFabricCommand(FabricCommand var1, CommandDispatcher var2);

   boolean checkObject(@Nullable Object var1, @NotNull String var2);
}
