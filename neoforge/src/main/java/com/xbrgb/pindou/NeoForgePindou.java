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

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Pindou.MOD_ID)
public class NeoForgePindou {
    public NeoForgePindou(IEventBus modBus) {
        // 在 RegisterEvent 中注册方块，而非构造函数
        modBus.addListener(this::onRegister);
        modBus.addListener(this::registerBlockColors);
        modBus.addListener(this::registerItemColors);
    }

    private void onRegister(RegisterEvent event) {
        // 只在方块注册时初始化一次
        if (event.getRegistryKey().equals(net.minecraft.core.registries.Registries.BLOCK)) {
            PindouCommon.init();
        }
    }

    private void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (var block : net.minecraft.core.registries.BuiltInRegistries.BLOCK) {
            int color = PindouBlocks.getColor(block);
            if (color != -1) event.register((state, world, pos, tintIndex) -> color, block);
        }
    }

    private void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (var item : net.minecraft.core.registries.BuiltInRegistries.ITEM) {
            int color = PindouBlocks.getColor(item);
            if (color != -1) event.register((stack, tintIndex) -> color, item);
        }
    }
}