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

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class PindouBlockItem extends BlockItem {
    private final String rgbDisplay;
    private final String hexDisplay;

    public PindouBlockItem(Block block, String brandName, String code, String rgbStr, String hex, Properties properties) {
        super(block, properties);
        // rgbStr 格式: "rgb(255, 255, 255)"
        // 取出括号内的内容，保留空格和逗号
        String inner = rgbStr.substring(4, rgbStr.length() - 1);   // "255, 255, 255"
        this.rgbDisplay = "RGB: " + inner;
        this.hexDisplay = "HEX: " + hex;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal(rgbDisplay));
        tooltipComponents.add(Component.literal(hexDisplay));
    }
}