package litebans.hook;

import litebans.lib.json.JSONParser;
import litebans.lib.kotlin.text.Regex;

public final class UsernamePattern {
   public static final MojangApi mojangApi = new MojangApi(null);
   private static final Regex regex = new Regex("^\\w{2,16}$");
   private static final JSONParser jsonParser = new JSONParser();

   public static final Regex getRegex() {
      return regex;
   }

   public static final JSONParser getJsonParser() {
      return jsonParser;
   }
}
