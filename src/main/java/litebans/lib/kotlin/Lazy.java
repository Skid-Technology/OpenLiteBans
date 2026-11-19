package litebans.lib.kotlin;

public interface Lazy {
   Object getValue();

   boolean isInitialized();
}
