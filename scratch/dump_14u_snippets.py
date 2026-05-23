import re

with open("scratch/egoist_14u_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

queries = [
    "AGC8.4.300_V9.6_rulerX14U.apk",
    "EGOIST_1.2k16_14u_12mp.agc",
    "EGOIST_1.2k16_14u_50mp.agc",
    "shgv1.2k16.so",
    "X14Ututorial.mp4"
]

for q in queries:
    for m in re.finditer(re.escape(q), html, re.IGNORECASE):
        pos = m.start()
        print(f"=== MATCH FOR {q} at position {pos} ===")
        # Print a window around this match to easily read the ID
        snippet = html[max(0, pos-150):min(len(html), pos+150)]
        print(repr(snippet))
        print("\n")
