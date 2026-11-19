package litebans.lib.hikari;

public interface IConcurrentBagEntry {
   int intValue = 0;
   int intValue2 = 1;
   int intValue3 = -1;
   int intValue4 = -2;

   boolean checkInt(int var1, int var2);

   void handleInt(int var1);

   int getInt();
}
