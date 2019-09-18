# PlateNumber
车牌选择弹框

使用方法：
第一步：
gradle版本
Add it in your root build.gradle at the end of repositories:

```aidl
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

	
maven版本

```aidl
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
	
第二步：
```aidl
dependencies {
	        implementation 'com.github.546554574:PlateNumber:v1.1'
	}
```

使用方法：

```aidl
/**
* @params plateNum可传入空也可传入已经有的车牌号，可以是StringBuilder格式的，也可以是String格式的
**/
 PlateNumDialog plateNumDialog = new PlateNumDialog(this, plateNum) {
            @Override
            public void onDone(StringBuilder str) {
                //选择后的车牌号
                plateNum = str.toString();
            }

            @Override
            public void onCancle() {
            }
        }.builder().show();
```

直接上图


![image](https://github.com/546554574/PlateNumber/blob/master/plate.gif)
