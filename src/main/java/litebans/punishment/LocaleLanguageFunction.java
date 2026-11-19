package litebans.punishment;

import java.util.Locale;
import litebans.lib.kotlin.jvm.internal.Function1;

public final class LocaleLanguageFunction implements Function1 {
   public static final LocaleLanguageFunction INSTANCE = new LocaleLanguageFunction();

   public final CharSequence getCharSequence(Locale var1) {
      return var1.getLanguage();
   }

   @Override
   public Object invoke(Object var1) {
      return this.getCharSequence((Locale)var1);
   }
}
