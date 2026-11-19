package litebans.lib.kotlin.text;

public class CharsKt__CharKt extends CharsKt__CharJVMKt {
   public static final boolean checkChar(char var0, char var1, boolean var2) {
      if (var0 == var1) {
         return true;
      } else if (!var2) {
         return false;
      } else {
         char var3 = Character.toUpperCase(var0);
         char var4 = Character.toUpperCase(var1);
         return var3 == var4 || Character.toLowerCase(var3) == Character.toLowerCase(var4);
      }
   }
}
