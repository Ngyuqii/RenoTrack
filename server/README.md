### Run Server
- Input values in application properties
- Create MySQL database according to schema.sql
- mvn spring-boot:run (to use with client)

### Unsplash API Call and Response
URL = "https://api.unsplash.com/search/photos?&query=living%20room&client_id={API_KEY}"
```
{
  "total": 10000,
  "total_pages": 1000,
  "results": [
    {
      "id": "OtXADkUh3-I",
      "slug": "OtXADkUh3-I",
      "created_at": "2020-03-10T13:34:46Z",
      "updated_at": "2023-05-17T08:11:08Z",
      "promoted_at": "2020-03-10T14:48:03Z",
      "width": 4000,
      "height": 6000,
      "color": "#c0c0c0",
      "blur_hash": "LCKd}C-U4T?vX-00IAIVIAx^%NMx",
      "description": "living room",
      "alt_description": "a living room filled with furniture and a large window",
      "urls": {
        "raw": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww&ixlib=rb-4.0.3",
        "full": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww&ixlib=rb-4.0.3&q=85",
        "regular": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww&ixlib=rb-4.0.3&q=80&w=1080",
        "small": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww&ixlib=rb-4.0.3&q=80&w=400",
        "thumb": "https://images.unsplash.com/photo-1583847268964-b28dc8f51f92?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww&ixlib=rb-4.0.3&q=80&w=200",
        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1583847268964-b28dc8f51f92"
      },
      "links": {
        "self": "https://api.unsplash.com/photos/OtXADkUh3-I",
        "html": "https://unsplash.com/photos/OtXADkUh3-I",
        "download": "https://unsplash.com/photos/OtXADkUh3-I/download?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww",
        "download_location": "https://api.unsplash.com/photos/OtXADkUh3-I/download?ixid=M3w0NDg2NDV8MHwxfHNlYXJjaHwxfHxsaXZpbmclMjByb29tfGVufDB8fHx8MTY4NDM2NzEzOXww"
      }
    }
  ]
}
```