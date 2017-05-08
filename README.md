## Avanti

Avanti是一个集成Retrofit+OkHttp+RxJava技术的实现框架，自定义网络框架内容；
同时包含一部分常用自定义控件。



## 版本
##### v1.1.0 增加MVP模式,实现BaseActivity和BaseFragment,统一管理APP中的Activity，实现网络框架高度定制

## 注意事项

* 1.最低兼容API 15 。
* 2.本项目已经依赖Retrofit、RxJava.
* 3.Butterknife需要在自己的Module中进行相应配置，文档中已给出。
* 4.具体开发逻辑详见项目中的Demo
* 5.如果有好的建议，可以提issue,谢谢帮忙改进~~~

## 使用

#### 添加依赖

在自己项目的app Module的build.gradle中添加项目依赖

```javascript
dependencies{
    compile 'com.ricky:avanti:1.1.0'
}
```
同时需要在项目中配置Butterknife的支持，配置 [Butterknife](https://github.com/JakeWharton/butterknife)

#### 配置权限

```javascript
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

#### 使用方式(网络框架)

##### 1.新建CustomApp继承Application,在onCreate方法中初始化

```javascript
@Override
    public void onCreate() {
        super.onCreate();
        //初始化网络请求参数;
        //第一个参数为Context,第二个参数为Retrofit中的BaseUrl,第三个参数为是否为Debug模式
        RetrofitUtils.init(this,"https://www.baidu.com/", true);
    }
```
##### 2.新建Retrofit业务请求接口

```javascript
 @FormUrlEncoded
 @POST("user/login")
 public Observable<BaseBean<LoginBean>> login(@FieldMap Map<String, String> params);
```
##### 3.通过RetrofitHelper构建网络请求API实例

```javascript
 mApi = RetrofitHelper.getInstance().getApiService(RickyApi.class
                , new OkHttpManager.Builder()
                        .cacheFile(mContext.getCacheDir())
                        .cacheSize(1024 * 1024 * 100)
                        .cookiePrefs("cookie_prefs")
                        .timeOut(15)
                        .build());
```
##### 4.集成RxJava，实现接口的参数构建和网络请求的返回构建

```javascript
//BaseBean已经在项目中集成
 public Observable<BaseBean<LoginBean>> login(String phone, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        return mApi.login(params);
    }
```
##### 5.实现调用

```javascript
 findViewById(R.id.btn_net_test).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 RickyHttpUtils.getInstance(mContext)
                         .login("Ricky", "123456")
                         .compose(RxUtils.<BaseBean<LoginBean>>rxSchedulerHelper())
                         .compose(RxUtils.<LoginBean>handleResult())
                         .subscribe(new RxSubscribe<LoginBean>(mContext) {
                             @Override
                             public boolean showProgress() {
                                 return super.showProgress();
                             }

                             @Override
                             public void _onNext(LoginBean loginBean) {
                                 Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                             }

                             @Override
                             public void _onError(int errorType, String message) {
                                 Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                             }
                         });
             }
         });
```

#### 使用方式(BaseActivity和BaseFragment统一管理页面)

##### 1.如果Activity或者Fragment为MVP模式，则直接继承BaseActivity或者BaseFragment,并实现其方法

```javascript
    //用于获取Presenter
    public abstract T getPresenter();
    //用于设置layout
    public abstract int getLayout();
    //用于初始化数据和实现界面逻辑
    public abstract void initEventAndData();
```

###### 1.1实现Presenter的时候，需要继承RxPresenter
###### 1.2实现Activity或者Fragment时，需要实现继承BaseView的接口

##### 2.如果Activity或者Fragment不是MVP模式，则直接继承SimpleActivity或者SimpleFragment,并实现其方法

```javascript
    //用于设置layout
    public abstract int getLayout();
    //用于初始化数据和实现界面逻辑
    public abstract void initEventAndData();
```


#### 其他工具类
* 1.AppManager--activity管理类
* 2.DensityUtils--单位转换工具和屏幕宽高获取工具
* 3.KeyBoardUtils--软键盘管理类
* 4.NetUtils--网络管理类

### 感谢
感谢Retrofit,RxJava,Butterknife和MaterialDialog的作者的技术支持，编写这个项目主要依靠的就是这几门技术
，如果存在知识产权的问题，请联系我，我将对项目进行修改
### License
Copyright (c) 2017 RickyChan. All right reserved.