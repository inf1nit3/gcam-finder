import re

with open("scratch/egoist_14u_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

# Let's search for "AGC8.4.300_V9.6_rulerX14U", "EGOIST_1.2k16_14u_12mp", "EGOIST_1.2k16_14u_50mp", "shgv1.2k16.so", "X14Ututorial"
queries = [
    "AGC8.4.300_V9.6_rulerX14U.apk",
    "EGOIST_1.2k16_14u_12mp.agc",
    "EGOIST_1.2k16_14u_50mp.agc",
    "shgv1.2k16.so",
    "X14Ututorial.mp4"
]

print("Searching exact GDrive IDs:")
for q in queries:
    pos_matches = [m.start() for m in re.finditer(re.escape(q), html, re.IGNORECASE)]
    if pos_matches:
        pos = pos_matches[0]
        # Look in the surrounding text to find the 33-char ID
        window = html[max(0, pos-400):min(len(html), pos+400)]
        ids = re.findall(r'[a-zA-Z0-9_-]{33}', window)
        # Filter out folder ID
        ids = [i for i in ids if i != "1auQkpVZP38R0v_qVqQ7LDVODBej_Sn_m"]
        print(f"File: {q} -> Matching IDs: {ids}")
    else:
        print(f"File: {q} -> NOT FOUND in HTML")
