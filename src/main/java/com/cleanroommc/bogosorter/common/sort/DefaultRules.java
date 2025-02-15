package com.cleanroommc.bogosorter.common.sort;

import com.cleanroommc.bogosorter.BogoSorter;
import com.cleanroommc.bogosorter.api.IBogoSortAPI;
import com.cleanroommc.bogosorter.api.SortType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Loader;

public class DefaultRules {

    public static void init(IBogoSortAPI api) {
        api.registerItemSortingRule("mod", SortType.MOD, ItemCompareHelper::compareMod);
        api.registerItemSortingRule("id", SortType.ID, ItemCompareHelper::compareId);
        api.registerItemSortingRule("meta", SortType.META, ItemCompareHelper::compareMeta);
        api.registerItemSortingRule("registry_order", SortType.META, ItemCompareHelper::compareRegistryOrder);
        api.registerClientItemSortingRule("display_name", null, ItemCompareHelper::compareDisplayName);
        api.registerItemSortingRule("nbt_size", SortType.NBT, ItemCompareHelper::compareNbtSize);
        api.registerItemSortingRule("nbt_has", SortType.NBT, ItemCompareHelper::compareHasNbt);
        api.registerItemSortingRule("nbt_rules", SortType.NBT, ItemCompareHelper::compareNbtValues);
        api.registerItemSortingRule("nbt_all_values", SortType.NBT, ItemCompareHelper::compareNbtAllValues);
        api.registerItemSortingRule("count", SortType.COUNT, ItemCompareHelper::compareCount);
        api.registerItemSortingRule("ore_dict", SortType.OREDICT, ItemCompareHelper::compareOreDict);
        api.registerItemSortingRule("material", SortType.OREDICT, ItemCompareHelper::compareMaterial);
        api.registerItemSortingRule("ore_prefix", SortType.OREDICT, ItemCompareHelper::compareOrePrefix);
        api.registerItemSortingRule("burn_time", null, ItemCompareHelper::compareBurnTime);
        api.registerItemSortingRule("is_block", null, ItemCompareHelper::compareIsBlock);
        api.registerItemSortingRule("hunger", null, ItemCompareHelper::compareHunger);
        api.registerItemSortingRule("saturation", null, ItemCompareHelper::compareSaturation);
        api.registerClientItemSortingRule("color", null, ItemCompareHelper::compareColor);

        if (Loader.isModLoaded("projecte")) {
            api.registerItemSortingRule("emc", null, ItemCompareHelper::compareEMC);
        }

        api.registerNbtSortingRule("potion", "Potion", Constants.NBT.TAG_STRING, ItemCompareHelper::comparePotionId, DefaultRules::getPotionId);
        api.registerNbtSortingRule("enchantment", "ench", Constants.NBT.TAG_LIST, ItemCompareHelper::compareEnchantments, nbtBase -> (NBTTagList) nbtBase);
        api.registerNbtSortingRule("enchantment_book", "StoredEnchantments", Constants.NBT.TAG_LIST, ItemCompareHelper::compareEnchantments, nbtBase -> (NBTTagList) nbtBase);
        if (BogoSorter.isAnyGtLoaded()) {
            api.registerNbtSortingRule("gt_circ_config", "Configuration", Constants.NBT.TAG_INT);
            api.registerNbtSortingRule("gt_item_damage", "GT.ToolStats/Dmg", Constants.NBT.TAG_INT);
        }
    }

    private static String getPotionId(NBTBase nbt) {
        String[] potion = ((NBTTagString) nbt).getString().split(":");
        return potion[potion.length - 1];
    }
}
