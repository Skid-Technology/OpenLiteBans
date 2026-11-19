package litebans.command;

import litebans.punishment.Flag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AliasPredicate {
   AliasPredicateCompanion Companion = AliasPredicateCompanion.INSTANCE;
   boolean flag = false;
   boolean flag2 = true;

   boolean checkCommandAlias(@Nullable CommandAlias var1, @NotNull Flag var2, @NotNull String var3);
}
