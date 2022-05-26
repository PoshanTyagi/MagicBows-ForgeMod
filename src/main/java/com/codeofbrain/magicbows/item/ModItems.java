package com.codeofbrain.magicbows.item;

import com.codeofbrain.magicbows.MagicBows;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicBows.MOD_ID);

    public static final RegistryObject<ExplosiveBowItem> EXPLOSIVE_BOW = ITEMS.register("explosive_bow", ExplosiveBowItem::new);

    public static final RegistryObject<Item> DATA_TABLET = ITEMS.register("data_tablet",
            () -> new DataTabletItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
}
