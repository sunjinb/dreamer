# main.py

from fastapi import FastAPI 
from typing import List
import httpx 
import asyncio # 비동기 통신
import requests

from pydantic import BaseModel

# 꿈 데이터
class DreamModel(BaseModel):
    dreamCardContent: str
    dreamCardAuthor: int
    isShow: bool

app = FastAPI()
URL = "https://j9b301.p.ssafy.io/api"

# 홈
@app.get("/")
def root():
    return "Hello FastAPI"

# 테스트 용도
@app.get("/data")
def root():
    return "Hello FastAPI"

# exit()

async def request(client):
    response = await client.get(URL)
    return response.text

@app.post("/data/night/dream/create")
async def dreamProcessing(data: DreamModel):
    # 받은 데이터 처리
    dreamCardContent = data.dreamCardContent
    dreamCardAuthor: data.dreamCardAuthor
    isShow = "T" if data.isShow else "F"
    print("ㅋㅋㅋㅋ")
    toJavaData = {
        "dreamCardDetail": {
            "dreamCardContent": dreamCardContent,
            "dreamCardAuthor": dreamCardAuthor,
            "isShow": isShow,
            "positivePoint": 50,
            "negativePoint": 30,
            "keywords": ["학업", "재물"],
            "wordKeywords": ["돈", "부자", "공부"]
        }
    }
    response = requests.post('{URL}/s3/dream/new', toJavaData, files=None)
    return {"message": "성공했어!! 옹예!"}


