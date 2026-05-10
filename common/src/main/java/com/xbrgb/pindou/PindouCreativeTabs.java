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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class PindouCreativeTabs {
    public static void init(Map<String, Pindou.BrandData> brands) {
        int column = 0;
        for (var entry : brands.entrySet()) {
            String brand = entry.getKey();
            var colors = entry.getValue().colors();
            String brandId = Pindou.getBrandId(brand);
            String firstCode = colors.keySet().iterator().next();
            String firstBlockId = brandId + "_" + firstCode.toLowerCase();
            Block iconBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Pindou.MOD_ID, firstBlockId));
            ResourceLocation tabLocation = ResourceLocation.fromNamespaceAndPath(Pindou.MOD_ID, brandId);

            CreativeModeTab tab = CreativeModeTab.builder(CreativeModeTab.Row.TOP, column++)
                    .title(Component.literal(brand))
                    .icon(() -> new ItemStack(iconBlock))
                    .displayItems((params, output) -> {
                        for (String code : colors.keySet()) {
                            String blockId = brandId + "_" + code.toLowerCase();
                            Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(Pindou.MOD_ID, blockId));
                            if (block != null) output.accept(block);
                        }
                    })
                    .build();
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, tabLocation, tab);
        }
    }
}