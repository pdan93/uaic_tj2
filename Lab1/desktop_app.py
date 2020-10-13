import requests
import sys 

url = "http://localhost:8080/lab1/WordsServlet"


def get_words(line):
    response = requests.post(url, data={"letters": line}, headers={"Accept": "text/plain"})
    return response.text


  
  
for line in sys.stdin: 
    print("Got words:")
    print(get_words(line))