import re

with open("scratch/egoist_14u_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

# We can search in the JSON-like text block at the end where each file is described in full detail:
# Example: ,[\"16TigGiJyM6K8Xk-r9TxObdriJQqRd_N2\",[\"1auQkpVZP38R0v_qVqQ7LDVODBej_Sn_m\"],\"X14Ututorial.mp4\",\"video\/mp4\"
# Let's search using a regex that captures the GDrive ID and the exact filename in this block
pattern = r',\\x5b\\x22([a-zA-Z0-9_-]{33})\\x22,\\x5b\\x221auQkpVZP38R0v_qVqQ7LDVODBej_Sn_m\\x22\\x5d,\\x22([a-zA-Z0-9_\-\.\+ ]+)\\x22'
matches = re.findall(pattern, html)

print("Exact Mappings Found:")
for file_id, name in matches:
    print(f" - File: {name} -> GDrive ID: {file_id}")
