# MinimalAndroidTemplater

Attempt at using the https://templater.info library in Android. I am getting the following error:

```
    Process: com.jtt.minimaltemplaterdocx, PID: 21983
    java.lang.IncompatibleClassChangeError: The method 'java.lang.String java.lang.Object.toString()' was expected to be of type interface but instead was found to be of type virtual (declaration of 'java.lang.reflect.ArtMethod' appears in /system/framework/core-libart.jar)
        at hr.ngs.templater.p.a(Unknown Source)
        at hr.ngs.templater.amk.a(Unknown Source)
        at hr.ngs.templater.amk.b(Unknown Source)
        at hr.ngs.templater.ac.<init>(Unknown Source)
        at hr.ngs.templater.ac.<clinit>(Unknown Source)
        at hr.ngs.templater.d.<init>(Unknown Source)
        at hr.ngs.templater.Configuration.builder(Unknown Source)
        at hr.ngs.templater.Configuration.factory(Unknown Source)
        at com.jtt.minimaltemplaterdocx.MainActivity.createDocX(MainActivity.java:64)
        at com.jtt.minimaltemplaterdocx.MainActivity.requestCreateHelloWorldDocX(MainActivity.java:88)
        at com.jtt.minimaltemplaterdocx.MainActivity.access$000(MainActivity.java:25)
        at com.jtt.minimaltemplaterdocx.MainActivity$1.onClick(MainActivity.java:42)
        at android.view.View.performClick(View.java:5197)
        at android.view.View$PerformClick.run(View.java:20926)
        at android.os.Handler.handleCallback(Handler.java:739)
        at android.os.Handler.dispatchMessage(Handler.java:95)
        at android.os.Looper.loop(Looper.java:145)
        at android.app.ActivityThread.main(ActivityThread.java:5951)
        at java.lang.reflect.Method.invoke(Native Method)
        at java.lang.reflect.Method.invoke(Method.java:372)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1400)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1195)
```
