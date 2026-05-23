import re
import json

with open("scratch/egoist_14u_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

# Google Drive JS data contains AF_initDataCallback with key 'ds:1'
# inside data there are file entries in a JSON structure like:
# ["ID", ["PARENT_ID"], "NAME", "MIME", ...]
# Let's extract all of them using regex!
# Pattern: \["([a-zA-Z0-9_-]{33})",\s*\["([a-zA-Z0-9_-]{33})"\],\s*"([^"]+)"
pattern = r'\["([a-zA-Z0-9_-]{33})",\s*\["([a-zA-Z0-9_-]{33})"\],\s*"([^"]+)"'
matches = re.findall(pattern, html)

print("Parsed File Entries:")
for file_id, parent_id, name in matches:
    if "google" not in name.lower() and "w3.org" not in name.lower():
        print(f"File Name: {name} -> GDrive ID: {file_id}")
