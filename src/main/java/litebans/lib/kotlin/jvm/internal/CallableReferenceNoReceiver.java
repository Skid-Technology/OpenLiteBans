package litebans.lib.kotlin.jvm.internal;

import java.io.Serializable;

public class CallableReferenceNoReceiver implements Serializable {
   private static final CallableReferenceNoReceiver INSTANCE = new CallableReferenceNoReceiver();

   private CallableReferenceNoReceiver() {
   }

   private final Object getObject() {
      return INSTANCE;
   }

   public static CallableReferenceNoReceiver getInstance() {
      return INSTANCE;
   }
}
