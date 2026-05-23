import re

with open("scratch/egoist_s26u_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

# We can search in the JSON-like text block at the end where each file is described in full detail:
# Example: ,[\"ID\",[\"11iiS5mB63E9YZNdv3lpkI_9gu2l0yO1I\"],\"FILENAME\"
pattern = r',\\x5b\\x22([a-zA-Z0-9_-]{33})\\x22,\\x5b\\x2211iiS5mB63E9YZNdv3lpkI_9gu2l0yO1I\\x22\\x5d,\\x22([a-zA-Z0-9_\-\.\+ ]+)\\x22'
matches = re.findall(pattern, html)

print("Exact Mappings Found:")
for file_id, name in matches:
    print(f" - File: {name} -> GDrive ID: {file_id}")
