import sys
import re

def process_pdf_text(file_path):
    with open(file_path, "r", encoding="utf-8") as file:
        lines = file.readlines()

    processed_lines = []
    inside_stream = False
    temp_block = []

    for line in lines:
        if "stream" in line.strip():
            inside_stream = True
            temp_block = [line]  # Начинаем новый блок
        elif "endstream" in line.strip():
            inside_stream = False
            temp_block.append(line)
            processed_lines.extend(temp_block[:6])  # Оставляем первые 5 строк + "endstream"
        elif inside_stream:
            temp_block.append(line)
        else:
            processed_lines.append(line)

    output_path = file_path.replace(".txt", "_processed.txt")
    with open(output_path, "w", encoding="utf-8") as file:
        file.writelines(processed_lines)

    print(f"Обработанный файл сохранен как {output_path}")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Использование: python script.py <путь_до_файла>")
        sys.exit(1)

    process_pdf_text(sys.argv[1])
import sys
import re

def process_pdf_text(file_path):
    with open(file_path, "r", encoding="utf-8") as file:
        lines = file.readlines()

    processed_lines = []
    inside_stream = False
    temp_block = []

    for line in lines:
        if "stream" in line.strip():
            inside_stream = True
            temp_block = [line]  # Начинаем новый блок
        elif "endstream" in line.strip():
            inside_stream = False
            temp_block.append(line)
            processed_lines.extend(temp_block[:6])  # Оставляем первые 5 строк + "endstream"
        elif inside_stream:
            temp_block.append(line)
        else:
            processed_lines.append(line)

    output_path = file_path.replace(".txt", "_processed.txt")
    with open(output_path, "w", encoding="utf-8") as file:
        file.writelines(processed_lines)

    print(f"Обработанный файл сохранен как {output_path}")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Использование: python script.py <путь_до_файла>")
        sys.exit(1)

    process_pdf_text(sys.argv[1])
