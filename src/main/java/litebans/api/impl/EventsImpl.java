package litebans.api.impl;

import java.util.concurrent.CopyOnWriteArrayList;
import litebans.api.Events;
import litebans.api.Events$Listener;
import org.jetbrains.annotations.NotNull;

public final class EventsImpl extends Events {
   private final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

   public final CopyOnWriteArrayList getCopyOnWriteArrayList() {
      return this.copyOnWriteArrayList;
   }

   @Override
   public void register(@NotNull Events$Listener var1) {
      if (this.copyOnWriteArrayList.contains(var1)) {
         String var2 = "Listener is already registered: " + var1;
         throw new IllegalArgumentException(var2.toString());
      } else {
         this.copyOnWriteArrayList.add(var1);
      }
   }

   @Override
   public void unregister(@NotNull Events$Listener var1) {
      this.copyOnWriteArrayList.remove(var1);
   }

   @Override
   public String toString() {
      return "Events";
   }
}
