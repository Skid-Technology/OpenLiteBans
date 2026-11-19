package litebans.platform;

import java.util.List;
import litebans.lib.kotlin.jvm.internal.KtAnnotation2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation2
public interface LiteCommand {
   void execute(@NotNull Sender var1, @NotNull String[] var2);

   String getName();

   @Nullable
   String getPermission();

   String[] getAliases();

   @NotNull
   List suggest(@NotNull Sender var1, @NotNull String[] var2);
}
