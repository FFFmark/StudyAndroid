# StudyAndroid

  这里记录我的一些实验的项目
  
  2018.8开始 DomeBilibili  是准备模仿知名的APP ， B站所做。我将一步一步去实现到接近真实的B站效果
  8.15 项目大致骨架完成 UI方面 缺少部分Item的图片 ，未完成 menu ，RecyclerView数据未绑定(数据来源应该是服务器) 
  
  8.19 添加了menu 部分换肤。

  8.21日，用源生的okhttp请求 http://api.m.mtime.cn/PageSubArea/TrailerList.api 该网址，成功返回了数据，但是Json数据解析失败。
  
  8.22日，通过分步解析Json将数据解析为List<Map<String,Obj>> 格式 ， 但是遇到了新问题。
  问题简述：隐式线程的非法操作。
  日志：Only the original thread that created a view hierarchy can touch its views。
  字面意思：只有创建视图层次结构的原始线程才能操作它的View。
  
  接下去的项目安排，数据绑定RV 和使用第三方封装好的OkHttp库 例：OkHttp-utils等。
  通过MVP设计的思想对项目优化。
