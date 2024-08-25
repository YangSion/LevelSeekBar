# 效果图 #
![图片](https://github.com/YangSion/LevelSeekBar/blob/master/view.png)
## 添加依赖 ##
在 settings.gradle 文件中添加
```
repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
```
在 build.gradle(:app) 文件中添加
```
dependencies {
    implementation 'com.github.YangSion:LevelSeekBar:0.1.0'
}
```
xml中使用
```
    <com.yangsion.levelseekbar.LevelSeekBar
        android:id="@+id/levelSeekBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:padding="25dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:bubble_default_icon="@drawable/ic_bubble_defualt"
        app:bubble_default_icon_height="50dp"
        app:bubble_default_icon_width="50dp"
        app:bubble_icon="@drawable/ic_bubble"
        app:bubble_icon_height="50dp"
        app:bubble_icon_width="50dp"
        app:bubble_textColor="@color/black2"
        app:level_default_icon="@drawable/ic_level_default"
        app:level_default_icon_height="50dp"
        app:level_default_icon_width="50dp"
        app:level_icon="@drawable/ic_level"
        app:level_icon_height="50dp"
        app:level_icon_width="50dp"
        app:level_number="5"
        app:level_progress_max="100"
        app:level_progress="50"
        app:level_progressBackground="@drawable/bg_progress_background"
        app:level_progressBackground_height="15dp"
        app:level_progressDrawable="@drawable/ic_progress"
        app:level_progress_height="15dp"
        app:level_textColor="@color/black3" />
```
LevelSeekBar的attrs属性，**<font color=red>注：动画显示效果还未实现</font>**
```
<declare-styleable name="LevelSeekBar">
        <attr name="bubble_default_icon" format="reference|color"/><!--气泡默认icon-->
        <attr name="bubble_default_icon_width" format="dimension"/><!--气泡默认icon的宽-->
        <attr name="bubble_default_icon_height" format="dimension"/><!--气泡默认icon的高-->
        <attr name="bubble_icon" format="reference|color"/><!--气泡当前icon-->
        <attr name="bubble_icon_width" format="dimension"/><!--气泡当前icon的宽-->
        <attr name="bubble_icon_height" format="dimension"/><!--气泡当前icon的高-->
        <attr name="bubble_textColor" format="color"/><!--气泡文字颜色-->
        <attr name="show_bubble_default_icon" format="boolean"/><!--是否显示气泡默认icon-->
        <attr name="show_bubble_icon" format="boolean"/><!--是否显示气泡当前icon-->
        <attr name="show_bubble_icon_anim" format="boolean"/><!--是否显示气泡当前icon动画-->
        <attr name="level_default_icon" format="reference|color"/><!--等级默认icon-->
        <attr name="level_default_icon_width" format="dimension"/><!--等级默认icon的宽-->
        <attr name="level_default_icon_height" format="dimension"/><!--等级默认icon的高-->
        <attr name="level_icon" format="reference|color"/><!--等级当前icon-->
        <attr name="level_icon_width" format="dimension"/><!--等级当前icon的宽-->
        <attr name="level_icon_height" format="dimension"/><!--等级当前icon的高-->
        <attr name="level_textColor" format="color"/><!--等级文字颜色-->
        <attr name="show_level_default_icon" format="boolean"/><!--是否显示等级默认icon-->
        <attr name="show_level_icon" format="boolean"/><!--是否显示等级当前icon-->
        <attr name="show_level_icon_anim" format="boolean"/><!--是否显示等级当前icon动画-->
        <attr name="level_number" format="integer"/><!--等级数-->
        <attr name="level_progress" format="integer"/><!--当前进度-->
        <attr name="level_progress_max" format="integer"/><!--最大进度-->
        <attr name="level_progress_height" format="dimension"/><!--进度条的高-->
        <attr name="level_progressDrawable" format="reference|color"/><!--自定义进度条的drawable-->
        <attr name="level_progressCornerRadius" format="dimension" /><!--进度条的圆角大小-->
        <attr name="show_level_progress_anim" format="boolean"/><!--是否显示进度条的动画-->
        <attr name="level_progressBackground" format="reference|color" /><!--进度条背景-->
        <attr name="level_progressBackground_height" format="dimension" /><!--进度条背景的高-->
        <attr name="level_progressBackgroundCornerRadius" format="dimension" /><!--进度条背景的圆角大小-->
    </declare-styleable>
```
