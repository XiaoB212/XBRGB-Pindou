# Pindou (拼豆)

---

## 联系作者

**哔哩哔哩**：[春风溢于华夏](https://space.bilibili.com/354332275?spm_id_from=333.1007.0.0)

---

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green)]()
[![Fabric Loader](https://img.shields.io/badge/FabricLoader-0.19.2-blue)](https://modrinth.com/mod/fabric-api)

[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.228-orange)](https://neoforged.net/)
[![Loader](https://img.shields.io/badge/Loader-MultiLoader-orange)](https://github.com/jaredlll08/MultiLoader-Template)

> 基于 **MultiLoader** 架构的双平台拼豆色彩模组 —— 一键导入色卡，点亮创意世界！

**Pindou** 是一款面向 Minecraft 1.21.1 的模组，采用 **MultiLoader** 模板同时支持 **Fabric** 与 **NeoForge**。它能够从 JSON 格式的颜色定义文件中批量生成彩色方块，内置 **MARD**、**卡卡**、**Coco**、**咪小窝**、**DMC**、**漫漫**、**盼盼** 等 7 个知名拼豆品牌的上千种标准色。每个品牌拥有独立的创造模式物品组，方块以“品牌名 + 色号”命名，悬停即可查看 RGB 和 HEX 颜色信息，是建筑、像素画与色彩创作的完美工具。

---

## ✨ 主要特性

- 🎨 **海量彩色方块**：内置 7 个品牌、上千种预置颜色，可直接使用。
- 📦 **品牌独立标签页**：每个品牌拥有专属的创造模式标签，方便查找与管理。
- 🖱️ **悬停显示颜色信息**：鼠标悬停时显示 RGB 和 HEX 值，方便精准配色。
- 🔄 **双平台兼容**：借助 **MultiLoader** 架构，同时提供 Fabric 和 NeoForge 版本，共享约 95% 的代码。
- ⚡ **纯数据驱动**：无需前置模组，所有颜色由 JSON 文件定义，启动时自动注册。
- 🌐 **多语言支持**：附带英文和简体中文语言文件，方块名称清晰明了。
- 🧩 **可扩展**：开发者可轻松添加新品牌，仅需提供 JSON 文件与映射条目。

---

## 📥 安装

### 前置需求
- **Fabric**：需安装 [Fabric Loader](https://fabricmc.net/use/)（0.19.2+）和 [Fabric API](https://modrinth.com/mod/fabric-api)（0.116.11+）。
- **NeoForge**：需安装 NeoForge（21.1.228+）。

### 安装步骤
1. 从 [GitHub Releases](https://github.com/XiaoB212/XBRGB-pindou/releases) 下载对应平台的 `.jar` 文件。
2. 将文件放入 Minecraft 游戏目录的 `mods` 文件夹。
3. 启动游戏，即可在创造模式物品栏中找到各品牌标签页。

---

## 🎮 使用指南

进入创造模式后：

- 物品栏中会出现 **MARD**、**卡卡**、**Coco**、**咪小窝**、**DMC**、**漫漫**、**盼盼** 七个新标签页。
- 点击任一标签，即可浏览该品牌的所有色块，每个方块显示为“品牌名 色号”（例如 `MARD H01`）。
- 方块颜色完全遵循原始色卡中的 RGB 值。
- 鼠标悬停时，将显示详细的颜色信息：
  ```
  RGB: 255, 255, 255
  HEX: #FFFFFF
  ```
- 支持中英文语言环境。

---

## 📐 颜色来源

| 品牌 | 说明 |
| ---- | ---- |
| MARD | MARD 拼豆标准色 |
| 卡卡 | 国内品牌卡卡家 |
| Coco | Coco 拼豆标准色 |
| 咪小窝 | 国内品牌咪小窝 |
| DMC | DMC 刺绣线色卡 |
| 漫漫 | 国内品牌漫漫家 |
| 盼盼 | 国内品牌盼盼拼豆 |

> **注意**：以上颜色数据均为公开色卡资料，版权归属原品牌所有；本模组仅提供数据加载与展示功能。

---

## ⚙️ 开发者

本项目基于 **[MultiLoader Template](https://github.com/jaredlll08/MultiLoader-Template)** 构建，采用标准的多模块 Gradle 工程结构：

- `common/` – 共享的游戏逻辑、方块注册、颜色加载
- `fabric/` – Fabric 平台初始化、颜色注册
- `neoforge/` – NeoForge 平台初始化、颜色注册

### 构建环境
- JDK 21
- Gradle 8.10+（使用 `./gradlew` 自动管理）

### 从源码构建
```bash
git clone https://github.com/XiaoB212/XBRGB-Pindou.git
cd XBRGB-Pindou
./gradlew clean build
```

### 生成资源文件
本项目已附带预生成的方块资源；若需重新生成，可运行：
```bash
python generate_assets.py -i "path/to/brands" -o "path/to/output"
```
此脚本会扫描品牌 JSON 文件，自动生成所有方块的 `blockstates`、`models/item` 和语言文件（`en_us.json` 和 `zh_cn.json`）。

### 添加新品牌
1. 在 `common/src/main/resources/data/pindou/colors/` 下放入新的 JSON 文件（格式与现有品牌一致）。
2. 在 `Pindou.java` 的 `BRAND_ID_MAP` 中添加 `"品牌名" -> "英文id"` 的映射，并在 `BRAND_FILES` 中添加该文件名。
3. 运行 `python generate_assets.py` 重新生成资源。
4. 构建并测试即可。

### 自定义输出JAR名称
在每个模块的 `build.gradle` 中添加：
```groovy
base.archivesName = "你喜欢的名字"
```

---

## 🤝 贡献

欢迎通过 Issue 和 Pull Request 参与贡献。在提交代码前，请确保：
- 遵循现有代码风格
- 通过编译与运行测试
- 记录任何新增的品牌映射或功能变更

---

## 📄 许可证

本项目采用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 开源。

```
Copyright [2026] [XiaoB212 of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

## 联系作者

**哔哩哔哩**：[春风溢于华夏](https://space.bilibili.com/354332275?spm_id_from=333.1007.0.0)

---

## ⚠️ 免责声明

本模组仅作为颜色数据的技术承载与展示工具，不主张任何对品牌名称、色号及颜色值的所有权。若您认为相关内容侵犯了您的合法权益，请通过 GitHub Issue 联系我们，我们将及时处理。

---

*Happy Crafting!* 🎉