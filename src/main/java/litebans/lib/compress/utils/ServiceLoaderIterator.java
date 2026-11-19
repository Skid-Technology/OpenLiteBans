package litebans.lib.compress.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class ServiceLoaderIterator implements Iterator {
   private Object object;
   private final Class classValue;
   private final Iterator iterator;

   public ServiceLoaderIterator(Class var1) {
      this(var1, ClassLoader.getSystemClassLoader());
   }

   public ServiceLoaderIterator(Class var1, ClassLoader var2) {
      this.classValue = var1;
      ServiceLoader var3 = ServiceLoader.load(var1, var2);
      this.iterator = var3.iterator();
      this.object = null;
   }

   private final boolean check() {
      while (this.object == null) {
         try {
            if (!this.iterator.hasNext()) {
               return false;
            }

            this.object = this.iterator.next();
         } catch (ServiceConfigurationError var2) {
            if (!(var2.getCause() instanceof SecurityException)) {
               throw var2;
            }
         }
      }

      return true;
   }

   @Override
   public boolean hasNext() {
      return this.check();
   }

   @Override
   public Object next() {
      if (!this.check()) {
         throw new NoSuchElementException("No more elements for service " + this.classValue.getName());
      } else {
         Object var1 = this.object;
         this.object = null;
         return var1;
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("service=" + this.classValue.getName());
   }
}
