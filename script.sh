rm -rf $PWD/ios/Pods/JitsiMeetSDK/Frameworks/JitsiMeet.framework
rm -rf $PWD/ios/Pods/JitsiMeetSDK/Frameworks/WebRTC.framework

cp -R $PWD/node_modules/react-native-jitsi-meet/JitsiMeet.framework  $PWD/ios/Pods/JitsiMeetSDK/Frameworks
cp -R $PWD/node_modules/react-native-jitsi-meet/WebRTC.framework  $PWD/ios/Pods/JitsiMeetSDK/Frameworks