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

import com.google.gson.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.*;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Pindou {
    public static final String MOD_ID = "pindou";
    public static final Logger LOGGER = LoggerFactory.getLogger("Pindou");

    // 显示名 -> 英文 ID（用于方块注册名）
    static final Map<String, String> BRAND_ID_MAP = Map.of(
            "MARD",   "mard",
            "卡卡",   "kakajia",
            "Coco",   "coco",
            "咪小窝", "mixiaowo",
            "DMC",    "dmc",
            "漫漫",   "manmanjia",
            "盼盼",   "panpanpindou"
    );

    // 按顺序加载（物品组顺序）
    static final String[] BRAND_FILES = {
            "MARD.json", "卡卡.json", "Coco.json",
            "咪小窝.json", "DMC.json", "漫漫.json", "盼盼.json"
    };

    private static final Map<String, BrandData> BRANDS = new LinkedHashMap<>();
    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        initialized = true;
        loadColors();
        registerBlocks();
    }

    private static void loadColors() {
        for (String fileName : BRAND_FILES) {
            String brand = fileName.replace(".json", "");
            try (InputStreamReader reader = new InputStreamReader(
                    Pindou.class.getResourceAsStream("/data/pindou/colors/" + fileName),
                    StandardCharsets.UTF_8)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                Map<String, ColorInfo> colors = new LinkedHashMap<>();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    String code = entry.getKey();
                    JsonObject obj = entry.getValue().getAsJsonObject();
                    String rgbStr = obj.get("color").getAsString();       // "rgb(r,g,b)"
                    int[] rgb = parseRGB(rgbStr);
                    String hex = obj.get("HEX").getAsString();           // "#XXXXXX"
                    colors.put(code, new ColorInfo(rgb, rgbStr, hex));
                }
                BRANDS.put(brand, new BrandData(brand, colors));
                LOGGER.info("Loaded {} colors for brand {}", colors.size(), brand);
            } catch (Exception e) {
                LOGGER.error("Failed to load brand {}", brand, e);
            }
        }
    }

    private static int[] parseRGB(String rgb) {
        String[] parts = rgb.substring(4, rgb.length() - 1).split(",");
        return new int[] {
                Integer.parseInt(parts[0].trim()),
                Integer.parseInt(parts[1].trim()),
                Integer.parseInt(parts[2].trim())
        };
    }

    private static void registerBlocks() {
        for (BrandData brand : BRANDS.values()) {
            String brandId = BRAND_ID_MAP.getOrDefault(brand.brand, brand.brand.toLowerCase());
            for (Map.Entry<String, ColorInfo> entry : brand.colors.entrySet()) {
                String code = entry.getKey();
                ColorInfo info = entry.getValue();
                String blockName = brandId + "_" + code.toLowerCase();
                Block block = new Block(BlockBehaviour.Properties.of().noOcclusion());
                // 将 rgbStr 和 hex 传给 PindouBlocks
                PindouBlocks.register(blockName, block, brand.brand, code, info.rgb, info.rgbStr, info.hex);
            }
        }
        PindouCreativeTabs.init(BRANDS);
    }

    public static Map<String, BrandData> getBrands() { return BRANDS; }
    public static String getBrandId(String brand) { return BRAND_ID_MAP.getOrDefault(brand, brand.toLowerCase()); }

    public record ColorInfo(int[] rgb, String rgbStr, String hex) {}
    public record BrandData(String brand, Map<String, ColorInfo> colors) {}
}