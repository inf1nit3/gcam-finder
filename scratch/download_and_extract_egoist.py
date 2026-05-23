import urllib.request
import re
import ssl

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = "https://drive.google.com/drive/folders/1r76IKU93fQDjF7MaTOw7u7-zjbE6xHye?usp=sharing"
headers = {
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36'
}

req = urllib.request.Request(url, headers=headers)
try:
    with urllib.request.urlopen(req, context=ctx) as response:
        html = response.read().decode('utf-8')
    
    with open("scratch/egoist_folder.html", "w", encoding="utf-8") as f:
        f.write(html)
        
    print("Folder downloaded. Now parsing files...")
    
    # Pattern to search for common file extensions inside Google Drive JS data block
    pattern = r'([a-zA-Z0-9_\-\.\+ ]+\.(?:mp4|mov|zip|apk|xml))'
    matches = list(re.finditer(pattern, html, re.IGNORECASE))
    found = set()
    for match in matches:
        filename = match.group(1)
        if filename in found or "google" in filename.lower() or "w3.org" in filename.lower():
            continue
        found.add(filename)
        pos = match.start()
        # Look around the filename to find the 33-char alphanumeric Google Drive ID
        window = html[max(0, pos-400):min(len(html), pos+400)]
        ids = re.findall(r'[a-zA-Z0-9_-]{33}', window)
        # Exclude folder ID itself: 1r76IKU93fQDjF7MaTOw7u7-zjbE6xHye
        ids = [i for i in ids if i != "1r76IKU93fQDjF7MaTOw7u7-zjbE6xHye"]
        print(f"File: {filename} -> IDs: {ids}")
        
except Exception as e:
    print(f"Error fetching/parsing GDrive: {e}")
