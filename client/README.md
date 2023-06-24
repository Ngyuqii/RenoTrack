### Run Client
- npm install
- Input GoogleMaps API Key
- ng serve (run the server before client, then access on http://localhost:4200)
- Alternatively ng build (copy the client dist folder files into server static resource to run on server, access on http://localhost:8080/)

### API Call and Response
GET Unsplash
URL = "http://localhost:8080/api/unsplash?query=livingroom"
```
[
    {
        "imageUrl": "https://images.unsplash.com/photo-1612965607446-25e1332775ae?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmdyb29tfGVufDB8fHx8MTY4NDQzNDM2MXww&ixlib=rb-4.0.3",
        "imageDes": "black flat screen tv turned off"
    },
    {
        "imageUrl": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwyfHxsaXZpbmdyb29tfGVufDB8fHx8MTY4NDQzNDM2MXww&ixlib=rb-4.0.3",
        "imageDes": "a living room filled with furniture and a large window"
    },
    {
        "imageUrl": "https://images.unsplash.com/photo-1633110187937-6e3b2f36dfca?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwzfHxsaXZpbmdyb29tfGVufDB8fHx8MTY4NDQzNDM2MXww&ixlib=rb-4.0.3",
        "imageDes": "a living room with a couch a table and chairs"
    }
]
```