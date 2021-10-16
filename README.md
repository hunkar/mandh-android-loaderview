<h3 align="center">Mandh Android Loading Screen Helper</h3>

<div align="center">

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> This project created for helping management of loading screens in Mandh Android applications.
    <br> 
</p>

## 📝 Table of Contents

- [About](#about)
- [Usage](#usage)
- [Authors](#authors)

## 🧐 About <a name = "about"></a>

LoaderView is making easy for handle loading screens in our applications. We expect to prevent confusing and gaining time for creation loading view in every applications.

## 🎈 Usage <a name="usage"></a>

Add Jitpack to proect build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

Add implementation to app/build.gradle
```
dependencies {
    ...
    implementation 'com.github.hunkar:mandh-android-loaderview:1.0.0'
    ...
}
```

You can imlement LoaderView from layout .xml file. Then you can show/hide loader view from activity.
```
    <com.mandh.loader.LoaderView
        android:id="@+id/loader_view"
        app:loaderSrc="@drawable/circular_arrow"
        app:loaderBackgroundColor="#112233"
        app:loaderImageHeight="500"
        app:loaderBackgroundOpacity="0.8"
        app:loaderOpacity="1"
        app:loaderImageWidth="500"
        app:rotationDirection="anticlockwise"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
```
    LoaderView loaderView = findViewById(R.id.loader_view);
    loaderView.setloaderStatus(true);
    loaderView.setloaderStatus(false);
```
* <b>loaderSrc</b> = Drawable for loader icon
* <b>loaderOpacity</b> = Loader icon opacity that is float between 0-1
* <b>loaderImageWidth</b> = Loader icon width value that is integer
* <b>loaderImageHeight</b> = Loader icon height value that is integer
* <b>loaderBackgroundColor</b> = String color for background overlay
* <b>loaderBackgroundOpacity</b> = Opacity of background overlay that is float between 0-1
* <b>rotationDirection</b> = 'clockwise' or 'anticlockwise'


You don't need add anything to xml layout. You can create insstance and set parameters. And then you can show/hide.
```
    LoaderView loaderView = new LoaderView(this);       //Parameter is context
    loaderView.setLoaderImageHeight(75);                //Loader icon width value that is integer
    loaderView.setLoaderImageWidth(75);                 //Loader icon height value that is integer
    loaderView.setLoaderOpacity(0.7);                   //Loader icon opacity that is float between 0-1
    loaderView.setLoaderBackgroundColor('#ffffff');     //String color for background overlay
    loaderView.setLoaderBackgroundOpacity(0.8);         //Opacity of background overlay that is float between 0-1

    //RotationDirection.CLOCKWISE
    //RotationDirection.ANTI_CLOCKWISE
    loaderView.setLoaderDirection(RotationDirection.CLOCKWISE);

    loaderView.setLoaderImage(getDrawable(R.drawable.circular_arrow));

    loaderView.setloaderStatus(true);
    loaderView.setloaderStatus(false);
```

## ✍️ Authors <a name = "authors"></a>

- [Mandh Solutions](http://www.mandhsolutions.com/) - Idea & Initial work