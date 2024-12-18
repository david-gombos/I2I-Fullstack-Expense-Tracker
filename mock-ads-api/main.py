import os
import json
from typing import List
from contextlib import asynccontextmanager

from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles


@asynccontextmanager
async def lifespan(app: FastAPI):
    # Initialize dictionaries to hold static data
    ads: List = []

    # Load JSON data during application startup
    base_dir = "./static_data"  # Directory where the JSON files are stored
    ads_file = os.path.join(base_dir, "ads.json")

    # Load the ads data
    with open(ads_file, "r", encoding="UTF-8") as file:
        ads = json.load(file)

    print("ads loaded!")

    # Attach the data to the app state
    app.state.ads = ads

    # Yield control back to FastAPI (startup complete)
    yield

    # Perform any cleanup during shutdown (if needed)
    print("Application shutting down!")

# Create the FastAPI app with the lifespan context
app = FastAPI(lifespan=lifespan)

app.mount("/images", StaticFiles(directory="images"), name="images")

@app.get("/ad", response_model=List)
async def list_ads():
    return app.state.ads
