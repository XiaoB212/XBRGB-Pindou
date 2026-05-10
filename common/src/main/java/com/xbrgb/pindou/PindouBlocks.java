/*
 * Copyright [2026] [XiaoB212 of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.xbrgb.pindou;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import java.util.HashMap;
import java.util.Map;

public class PindouBlocks {
    private static final Map<Block, int[]> BLOCK_COLORS = new HashMap<>();

    public static void register(String name, Block block, String brandDisplay, String code,
                                int[] rgb, String rgbStr, String hex) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Pindou.MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id,
                new PindouBlockItem(block, brandDisplay, code, rgbStr, hex, new Item.Properties()));
        BLOCK_COLORS.put(block, rgb);
    }

    public static int getColor(Block block) {
        int[] rgb = BLOCK_COLORS.get(block);
        return rgb == null ? -1 : (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
    }

    public static int getColor(Item item) {
        if (item instanceof PindouBlockItem bi) return getColor(bi.getBlock());
        return -1;
    }
}