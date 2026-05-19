import os
import re

entity_dir = r"E:\Others Project\Mental_Therapy_ORM_NETHMI\Mental_Therapy_ORM\src\main\java\org\example\mental_therapy_orm\entity"

for filename in os.listdir(entity_dir):
    if filename.endswith(".java"):
        filepath = os.path.join(entity_dir, filename)
        with open(filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        if 'lombok' in content:
            continue
            
        # Add imports
        import_str = "import lombok.AllArgsConstructor;\nimport lombok.Data;\nimport lombok.NoArgsConstructor;\n"
        content = re.sub(r'(import [^;]+;\n)+', lambda m: m.group(0) + import_str, content, count=1)
        
        # Add annotations before public class
        content = re.sub(r'public class (\w+)', r'@Data\n@NoArgsConstructor\n@AllArgsConstructor\npublic class \1', content)
        
        # Remove everything after the last field
        # We can find the first 'public \w+\(' (constructor) or 'public \w+ get' (getter)
        # and delete from there to the second to last '}'
        
        match = re.search(r'(\s+public \w+\s*\()|(\s+public \w+ get\w+\()', content)
        if match:
            start_idx = match.start()
            end_idx = content.rfind('}')
            content = content[:start_idx] + '\n}\n'
            
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Refactored {filename}")
