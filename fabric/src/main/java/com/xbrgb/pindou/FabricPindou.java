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

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public class FabricPindou implements ModInitializer, ClientModInitializer {
    @Override public void onInitialize() { PindouCommon.init(); }

    @Override public void onInitializeClient() {
        for (Block block : BuiltInRegistries.BLOCK) {
            int color = PindouBlocks.getColor(block);
            if (color != -1) ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> color, block);
        }
        for (var item : BuiltInRegistries.ITEM) {
            int color = PindouBlocks.getColor(item);
            if (color != -1) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> color, item);
        }
    }
}