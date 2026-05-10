#!/usr/bin/env python3
import json, argparse
from pathlib import Path

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("-i", "--input-dir", default="common/src/main/resources/data/pindou/colors")
    parser.add_argument("-o", "--output-dir", default="common/src/main/resources/assets/pindou")
    args = parser.parse_args()
    input_dir = Path(args.input_dir)
    output_dir = Path(args.output_dir)

    brand_id_map = {
        "MARD.json": "mard", "卡卡.json": "kakajia", "Coco.json": "coco",
        "咪小窝.json": "mixiaowo", "DMC.json": "dmc", "漫漫.json": "manmanjia", "盼盼.json": "panpanpindou"
    }
    blockstate_dir = output_dir / "blockstates"
    item_model_dir = output_dir / "models" / "item"
    lang_dir = output_dir / "lang"
    for d in [blockstate_dir, item_model_dir, lang_dir]:
        d.mkdir(parents=True, exist_ok=True)

    lang_json = {}

    for filename, brand_id in brand_id_map.items():
        file_path = input_dir / filename
        if not file_path.exists(): continue
        with open(file_path, encoding="utf-8") as f:
            data = json.load(f)
        brand_display = filename.replace(".json", "")
        for code in data:
            block_id = f"{brand_id}_{code.lower()}"
            state = {"variants": {"": {"model": "pindou:block/color_block"}}}
            with open(blockstate_dir / f"{block_id}.json", "w", encoding="utf-8") as out:
                json.dump(state, out, indent=2)
            item_model = {"parent": "pindou:block/color_block"}
            with open(item_model_dir / f"{block_id}.json", "w", encoding="utf-8") as out:
                json.dump(item_model, out, indent=2)
            lang_key = f"block.pindou.{block_id}"
            lang_value = f"{brand_display} {code}"
            lang_json[lang_key] = lang_value

    # 生成英语语言文件
    with open(lang_dir / "en_us.json", "w", encoding="utf-8") as out:
        json.dump(lang_json, out, indent=2, ensure_ascii=False)

    # 生成中文语言文件（内容相同）
    with open(lang_dir / "zh_cn.json", "w", encoding="utf-8") as out:
        json.dump(lang_json, out, indent=2, ensure_ascii=False)

    print(f"Assets+lang generated for {len(lang_json)} blocks.")

if __name__ == "__main__":
    main()