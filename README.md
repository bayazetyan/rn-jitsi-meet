
# rn-jitsi-meet

React native wrapper for Jitsi Meet SDK

  

## Install

`yarn add git+https://github.com/bayazetyan/rn-jitsi-meet.git`

Add the following piece of code in your ```metro.config.js``` file to avoid conflicts between `rn-jitsi-meet` and react-native in metro bundler.

  

```javascript

const blacklist = require('metro-config/src/defaults/blacklist');
  
module.exports = {  
  resolver: {  
	  blacklistRE: blacklist([  
		  /ios\/Pods\/JitsiMeetSDK\/Frameworks\/JitsiMeet.framework\/assets\/node_modules\/react-native\/.*/,  
		 ]),
	 },
 }

```

  

## Get started

  

The following component is an example of use:

  

```jsx

import React from 'react';
import { View } from 'react-native';
import JitsiMeet, { JitsiMeetView } from 'rn-jitsi-meet';

function VideoCall() {
	React.useEffect(() => {
		const url = 'roomURL';
		const userInfo = {
			displayName: 'User Name',
			email: 'user@example.com',
			serviceName: 'Service name',
			avatar: 'https:/gravatar.com/avatar/abc123',
		};
		/* You can also use JitsiMeet.audioCall(url) for audio only call */
		/* You can programmatically end the call with JitsiMeet.endCall() */
		setTimeout(() => {
			JitsiMeet.call(url, userInfo)			
		}, 1000)
	}, [])
	
	function onConferenceTerminated(nativeEvent) {
		/* Conference terminated event */
	}

	function onConferenceJoined(nativeEvent) {
		/* Conference joined event */
	}

	function onConferenceWillJoin(nativeEvent) {
		/* Conference will join event */
	}

	return (
		<View style={{ flex: 1 }}>
			<JitsiMeetView 
				onConferenceJoined={onConferenceJoined}
				onConferenceWillJoin={onConferenceWillJoin}
				onConferenceTerminated={onConferenceTerminated}
				style={{ flex: 1, height: '100%', width: '100%' }}
			/>
		</View>
	)
}  

export default VideoCall;
```

### Events

  

You can add listeners for the following events:

- onConferenceJoined

- onConferenceTerminated

- onConferenceWillJoin
  

## iOS Configuration

  

1.) navigate to `<ProjectFolder>/ios/<ProjectName>/`

2.) edit `Info.plist` and add the following lines

  

```jsx
<key>NSCameraUsageDescription</key>
<string>Camera Permission</string>
<key>NSMicrophoneUsageDescription</key>
<string>Microphone Permission</string>
```

3.) in `Info.plist`, make sure that

```jsx
<key>UIBackgroundModes</key>
<array>
	//... other keys
	<string>voip</string>
</array>
```
4.) Modify your Podfile to have ```platform :ios, '10.0'``` and execute ```pod install```
5.) In Xcode, under Build setting set Enable Bitcode to No
6.) Open terminal and run:
`chmod +rx ./node_modules/rn-jitsi-meet/script.sh && ./node_modules/rn-jitsi-meet/script.sh`
  

## Android Configuration

1.) In `android/app/build.gradle`, add/replace the following lines:

```java
project.ext.react = [
	entryFile: "index.js",
	bundleAssetName: "app.bundle",
]
```
2.) In `android/app/src/main/java/com/xxx/MainApplication.java` add/replace the following methods:

 
```java
import androidx.annotation.Nullable; // <--- Add this line if not already existing
...

@Override
protected String getJSMainModuleName() {
	return "index";
}

@Override
protected @Nullable String getBundleAssetName() {
	return "app.bundle";
}

```
3.) In `android/build.gradle`, add the following code

```java
allprojects {
	repositories {
		mavenLocal()
		jcenter()
		maven {
		// All of React Native (JS, Obj-C sources, Android binaries) is installed from npm
		url "$rootDir/../node_modules/react-native/android"
	}
	maven {
		url "https://maven.google.com"
	}
	maven { // <---- Add this block
		url("$rootDir/../node_modules/rn-jitsi-meet/jitsi-sdk")
	}
	maven { url "https://jitpack.io" }
	}
}
```

  

## Android Additional Manual Install for RN < 0.60

1.) In `android/app/src/main/AndroidManifest.xml` add these permissions

```xml
<manifest  xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools" // <--- Add this line if not already existing

...

<uses-permission android:name="android.permission.CAMERA" />

<application  
  <!-- .... -->
  tools:replace="android:allowBackup"
  <!-- .... -->
  />
```

3.) In `android/settings.gradle`, include rn-jitsi-meet module

```gradle

include ':rn-jitsi-meet'

project(':rn-jitsi-meet').projectDir = new File(rootProject.projectDir, '../node_modules/rn-jitsi-meet/android')

```

  

4.) In `android/app/build.gradle`, add rn-jitsi-meet to dependencies

```java

android {
...
	packagingOptions {
		pickFirst 'lib/x86/libc++_shared.so'
		pickFirst 'lib/x86/libjsc.so'
		pickFirst 'lib/x86_64/libjsc.so'
		pickFirst 'lib/arm64-v8a/libjsc.so'
		pickFirst 'lib/arm64-v8a/libc++_shared.so'
		pickFirst 'lib/x86_64/libc++_shared.so'
		pickFirst 'lib/armeabi-v7a/libc++_shared.so'
		pickFirst 'lib/armeabi-v7a/libjsc.so'
	}
}
```

`and set your minSdkVersion to be at least 21.`

  

### Side-note

If your app already includes `react-native-locale-detector` or `react-native-vector-icons`, you must exclude them from the `rn-jitsi-meet` project implementation with the following code (even if you're app uses autolinking with RN > 0.60):

```java
implementation(project(':rn-jitsi-meet')) {
	exclude group: 'com.facebook.react',module:'react-native-locale-detector'
	exclude group: 'com.facebook.react',module:'react-native-vector-icons'
}
```