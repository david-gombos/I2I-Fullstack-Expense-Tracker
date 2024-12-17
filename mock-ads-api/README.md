# Currency Converter

A FastAPI-based mock ads api that hosts an endpoint to return a list of 10 mock ads. The application is containerized using Docker for ease of deployment.


## API Endpoints

### 1. **Get List of Ads**

- **Endpoint**: `GET /ad`
- **Description**: Returns a list of 10 mock ads with their image urls and links to pages where the products can be found.
- **Example Response**:
  ```json
  [
  {
    "title": "zyrtec",
    "imageUrl": "https://i.ibb.co/t8273kJ/zyrtec-banner-ad.webp",
    "link": "https://www.amazon.com/Zyrtec-Childrens-Dissolving-Cetirizine-Chewables/dp/B0CSCTPCJB/ref=sr_1_2?crid=1AXKQOWWEXYV9&dib=eyJ2IjoiMSJ9.ElfSvrVmH7aqOWxEkihAycFQjHJzsZMhudGRrfA0mYRZxKGO00H_OxDeOyfp7R8F4UMPHdnHv-STZdjAh5v0SfvEIZMstJp2r9-Ken-uvaf3JrmBieqCacpQxhwBNkfLkuww8pUuQ6zOj0FkDne1r_YBImtC_3jf9-5MM8R7_AiCnC20cDIe5eCiMNI4bvIRHLhV780Mnc1Tdk3cd0DZCO0Tp65heN9onHTnGUH_91v5kVWenwVPtJKl_OWzkBMJwAgrb6zlRh71U0vUQQy8qrIohNsqpQQiY6Aex5xUvlvXCkZMPBY97awSa4BX7CjaXZhPHLeyvBHqb5_oxey09puWXRoMx7hDRULTokrBAcjwCdA20w9UiBDuKZfongs-.uGdHpiIl7MMieszy3v4RidXfHTaUjrJ3GgQcmob1hqQ&dib_tag=se&keywords=zyrtec&qid=1733981308&sprefix=zyrte%2Caps%2C366&sr=8-2",
    "category": "Healthcare",
    "price": "35.59",
    "id": "1"
  },
  {
    "title": "whole-hearted",
    "imageUrl": "https://i.ibb.co/BB0BNPH/whole-hearted-banner-ad.webp",
    "link": "https://www.amazon.com/WholeHearted-Chicken-Breast-Freeze-Dried-Treats/dp/B0BCSG97G2/ref=sr_1_2?crid=1HK7ZBGMBSG3D&dib=eyJ2IjoiMSJ9.GxHcYb48SiOF8GlsDMTibgTVRpcOJDqwvD6Iapwp0gC-hqmZm917ZC9p9pR9JQ9r4tHGqSHK2ndfINAvVECrUdDtrelM8j8RYCBs1mnP4E4eRlR_jhH3VtmkCcFzGJxBCP7HgyNnhedxT59ka5ySxSU5zuF6uMSEdNR5nCaf5IRGEQ0lfeZLxOII7E26i5jOGhTqvnwTwXjedLNeUB23prqE3sIfsnL9djFmhRvQ_X87SSb9c12Nk3wAJVLsqmzAhpwjvv2WZqOycK4amBXD50x--niT1cMiC58ShRyxbGEBSBz2O73vcaEsP19b-uOIlPLORRNBQ0vEb_w06qTvoBLRbmyU8MwCg1bcGSSANerAn1Q1CXGnMaZbwJ1o0XA90gXVpTCGe7A4Xaoo5NJT-q0t3wMli6LCQG6tW0ie0rgdHRcIpzzH7qrAvV80rzPD.mvCE2e1tChOsNZhW2kaMp_NEP1ZbDCTZ8zSTOJIQAts&dib_tag=se&keywords=whole+hearted+dog+treats&qid=1733981464&sprefix=whole+hear%2Caps%2C962&sr=8-2",
    "category": "Groceries",
    "price": "29.49",
    "id": "2"
  },
  {
    "title": "samsung",
    "imageUrl": "https://i.ibb.co/zxkDTj1/samsung-galaxy-banner-ad.webp",
    "link": "https://www.amazon.com/SAMSUNG-Smartphone-Unlocked-Android-Titanium/dp/B0CMDMKQB7/ref=sr_1_1?crid=31J3VKPAUFZ7W&dib=eyJ2IjoiMSJ9.nsyb0SF4p0uSwFcc8N5ap2-L-Jl2n9rYXXRk69xEoNFtTmWRgGhqDDDW1a8JHfA_bjvgTYXd9_fqkWEZSdSeRpswaqzEowBhvNqr2r8nPOyGp86eQjiXzx2EuczQvFK7F9mAtV0h99brr2bA1E8zWUAompC_eoKaqzjx3nGhqhTvo1Y9Nmn7ZMuurfpNWGoLrKtWr23ajLSTHEs-igkDWqRtk6U0xC3OBC2B5mQf8Rs.jvFLkSNO6c8r0nHJ8iNjckBTbU6fdumC1D8dBAQDqWw&dib_tag=se&keywords=samsung%2Bgalaxy%2Bs24%2Bultra&qid=1733981522&sprefix=samsung%2Bgala%2Caps%2C340&sr=8-1&th=1",
    "category": "Utilities",
    "price": "1024.29",
    "id": "3"
  },
  ...
  ]
  ```

---

## Development Notes

- **Static Data**:
  - The static data files are located in the `static_data/` directory.
  - `ads.json` contains the list of 10 mock ads.

---

## Testing the Application

1. Ensure the application is running.
2. Use tools like `curl`, `Postman`, or your browser to test the endpoints.
3. Example `curl` commands:
   - Get list of ads:
     ```bash
     curl http://localhost:8000/ads
     ```
