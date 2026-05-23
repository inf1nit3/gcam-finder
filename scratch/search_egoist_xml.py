import re

with open("scratch/egoist_folder.html", "r", encoding="utf-8") as f:
    html = f.read()

# Let's search for any strings matching XML patterns or any filename ending in .xml, .zip, etc.
# Also print any interesting JSON-like entries or patterns of files.
pattern = r'([a-zA-Z0-9_\-\.\+ ]+\.(?:xml|zip|apk|mp4))'
matches = set(re.findall(pattern, html, re.IGNORECASE))
print("All found file extensions:")
for m in sorted(matches):
    if "google" not in m.lower():
        print(f" - {m}")

# Let's search for "Egoist" inside the html (case-insensitive) to see where the XML is named
egoist_matches = [m.start() for m in re.finditer("egoist", html, re.IGNORECASE)]
print(f"\nFound {len(egoist_matches)} occurrences of 'egoist'.")
for idx, pos in enumerate(egoist_matches[:10]):
    snippet = html[max(0, pos-150):min(len(html), pos+150)]
    print(f"Occurrence {idx+1}: {repr(snippet)}")
