# imgur-api
An open sourced imgur api for Android. <b>This is very much still in alpha form, and things are subject to change drastically. Use at your own risk.</b>

PR's and Issues welcomed!

For a more thorough example of this library being used, checkout the development branch of this <a href="https://github.com/AKiniyalocts/imgur-android/tree/development">repository</a>.

# Plan
Create a simple library mirroring the availble api endpoints from <a href="https://api.imgur.com/">imgur</a> for use in Android.
We'll use <a href="http://square.github.io/retrofit/">Retrofit</a> to create our REST client, and then go from there.

# Prerequisites
Go to <a href="https://api.imgur.com/">https://api.imgur.com/</a> and register an application to use anything in this library. 

# Include in your project
This project is under heavy development, because of this, only snapshots will be availble until a release client is ready.

`repositories {
	    maven { url "https://jitpack.io" }
	}`
	
`dependencies {
      compile 'com.github.AKiniyalocts:imgur-api:-SNAPSHOT'
}`

# Uses

### Initialize in your Application's onCreate()
```java
public class ImgurAndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Your Client-ID is obtained after registering your application with imgur
        ImgurClient.initialize(Constants.MY_IMGUR_CLIENT_ID);
    }
}

```

### Anonymously Upload Images
```java
 ImgurClient.getInstance()
                    .uploadImage(
                            new TypedFile("image/*", mFile),
                            "My Image Title",
                            "My Image Description",
                            new Callback<ImgurResponse<Image>>() {
                                @Override
                                public void success(ImgurResponse<Image> imageImgurResponse, Response response) {
                                    //Do something with your response.
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    //Notify user of failure
                                }
                            }
                    );
                    
    
    // Title and description Strings are not required. Pass null if needed.
    // There are also overrides for uploading from a URL or base64 image
    
```
### More to come...

License
--------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
