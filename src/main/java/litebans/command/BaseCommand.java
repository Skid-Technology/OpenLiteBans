package litebans.command;

import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.KtAnnotation2;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PunishmentService;
import litebans.sync.Throttler;
import org.jetbrains.annotations.NotNull;

@KtAnnotation2
public abstract class BaseCommand extends CommandRunnable implements LiteCommand {
   public static final CommandAliases commandAliases = new CommandAliases(null);
   private final String string;
   private final boolean flag;
   private final boolean argsRequired;

   public BaseCommand(@NotNull String var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.string = var1;
      this.flag = true;
      this.argsRequired = true;
   }

   public boolean isFlag() {
      return this.flag;
   }

   public boolean isArgsRequired() {
      return this.argsRequired;
   }

   @Override
   public String getName() {
      return this.string;
   }

   @Override
   public String[] getAliases() {
      return new String[0];
   }

   @Override
   public String getPermission() {
      return "litebans." + this.getName();
   }

   @Override
   public java.util.List suggest(@NotNull Sender var1, @NotNull String[] var2) {
      return this.getPlatform().getCommandRegistry().suggest(this, this.getName(), var1, var2);
   }

   public final void dispatch(@NotNull Sender var1, @NotNull String[] var2, @NotNull String var3) {
      MessageSender var4 = CommandRunnable.messageSender;
      Sender var5 = var1;

      try {
         var4.getThreadLocal().set(var5);
         Throttler var8 = (Throttler)this.getPlatform().getModule(Throttler.class);
         boolean var9 = false;

         try {
            if (this.isArgsRequired()) {
               requireArgsDefault(this, var2, null, 2, null);
            }

            if (var1.isPlayer() && var8.isThrottled((byte)3, var1.getUuidString())) {
               String var29 = "litebans.cooldown.bypass";
               if (!var1.hasPermission(var29)) {
                  var9 = true;
               }
            }

            if (!var9) {
               this.execute(var1, var2);
            }
         } catch (AbortException var24) {
            String var28 = EntryPlaceholders.getString(
               EntryPlaceholders.getString(String.valueOf(var24.getObject()), "commandArgs", () -> getObject(var2)), "command", () -> getObject(var3)
            );
            PunishmentService.reply(this, var28);
         } catch (Exception var25) {
            PunishmentService.reply(this, Message.ERROR_INTERNAL);
            CommandRunnable var11 = this;
            ((DatabaseManager)var11.getPlatform().getModule(DatabaseManager.class)).reportError(var25);
         } finally {
            if (var1.isPlayer() && !var9) {
               var8.release((byte)3, var1.getUuidString());
            }
         }
      } finally {
         var4.getThreadLocal().remove();
      }
   }

   public void requireArgs(@NotNull String[] var1, @NotNull Function0 var2) {
      MessageSender var3 = CommandRunnable.messageSender;
      boolean var4 = var1.length == 0;
      if (var4) {
         var3.getVoid(var2.invoke());
         throw new NoWhenBranchMatchedException();
      }
   }

   public static void requireArgsDefault(BaseCommand var0, String[] var1, Function0 var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = () -> getMessage(var0);
         }

         var0.requireArgs(var1, var2);
      }
   }

   private static final Object getObject(String[] var0) {
      return ArraysKt.getString(var0, " ", null, null, 0, null, null, 62, null);
   }

   private static final Object getObject(String var0) {
      return '/' + var0;
   }

   private static final Message getMessage(BaseCommand var0) {
      return Message.getMessage2(var0.string + "_USAGE");
   }
}
