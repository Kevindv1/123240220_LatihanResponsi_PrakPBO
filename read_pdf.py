import sys
from PyPDF2 import PdfReader

try:
    reader = PdfReader("LatihanResponsiPBO (1).pdf")
    text = ""
    for page in reader.pages:
        text += page.extract_text() + "\n"
    print(text)
except Exception as e:
    print(f"Error reading PDF: {e}")
