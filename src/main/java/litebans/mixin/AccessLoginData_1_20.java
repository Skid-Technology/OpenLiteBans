package litebans.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_2535;
import net.minecraft.class_3248;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_3248.class})
public interface AccessLoginData_1_20 {
   @Accessor("field_14160")
   GameProfile getProfile();

   @Accessor("field_14158")
   class_2535 getConnection();
}
