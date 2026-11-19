package litebans.lib.kotlin.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.sequences.Sequence;
import litebans.lib.kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

public final class TextStreamsKt {
   public static final void handleReader(@NotNull Reader reader, @NotNull Function1 action) {
      Intrinsics.handleObject3(reader, "<this>");
      Intrinsics.handleObject3(action, "action");
      short var5 = 8192;
      Closeable var4 = reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, var5);
      Throwable var18 = null;

      try {
         BufferedReader var6 = (BufferedReader)var4;

         Iterator var7 = getSequence(var6).iterator();
         while (var7.hasNext()) {
            action.invoke(var7.next());
         }
      } catch (Throwable var16) {
         var18 = var16;
         throw var16;
      } finally {
         CloseableKt.handleCloseable(var4, var18);
      }
   }

   public static final List getList(@NotNull Reader var0) {
      Intrinsics.handleObject3(var0, "<this>");
      ArrayList var1 = new ArrayList();
      handleReader(var0, o -> getUnit(var1, (String) o));
      return var1;
   }

   public static final Sequence getSequence(@NotNull BufferedReader var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return SequencesKt.getSequence(new LinesSequence(var0));
   }

   private static final Unit getUnit(ArrayList arrayList, String it) {
      Intrinsics.handleObject3(it, "it");
      arrayList.add(it);
      return Unit.INSTANCE;
   }
}
