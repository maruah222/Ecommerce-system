// pages/base/base.js
// pages/home/home.js
var app = getApp()
Page({
  data: {
    background: ['https://img12.360buyimg.com/da/s590x470_jfs/t1/132276/20/2431/86168/5ee98b05E317fdbea/7c48dd5bb288baf2.jpg.webp', 'https://img13.360buyimg.com/da/jfs/t1/128049/34/4652/105871/5ee36641E6c06caa4/f9ad247477cdf98f.jpg!q90.webp', 'https://img30.360buyimg.com/da/jfs/t1/146743/39/855/145343/5ee97bd6E3d93d258/c9087a2334d68464.jpg!q90.webp'],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 2000,
    duration: 500,
    navbar: ['综合','商家', '销量','价格↑','价格↓'],
    currentTab: 0,//默认加载第0个页面
    goodData: [
      {
        "goodid": "d5d9b278930f4d16bf30d19f1683c578",
        "shopid": "123456",
        "goodname": "华为手机",
        "goodpicture": "//img14.360buyimg.com/n1/s450x450_jfs/t1/117400/10/2356/65449/5ea182c4E2d5c0060/e57574f6564bea42.jpg",
        "checkstate": 1,
        "number": 0,
        "ispackage": 0,
        "frontpicture": "//img20.360buyimg.com/vc/jfs/t1/112707/38/3362/195227/5ea7970dE5e120f21/d138f55f3565d8ef.jpg",
        "shangtime": "2020-06-19T01:08:32.000+00:00",
        "categoryid": "手机",
        "allsellnumber": 0,
        "deletestate": 0,
        "updownstate": 1,
        "introduction": null
      }
    ],
    shopdata: [{
      "shopid": "915477812@qq.com",
      "shopname": "阿狗家",
      "totalsales": 0,
      "shopaddress": "广东省",
      "registerstate": 1,
      "sellerpassword": "$2a$10$0ZGEOb58F8ZxgRfc9oBtfu457zo/OIPgrqwiPWxFJBG8idotwlR42",
      "sellername": "阿狗",
      "sellertelephone": "171717171"}]
  },
  
  // 导航切换监听
  navbarTap: function (e) {
    let self=this;
    this.setData({
      currentTab: e.currentTarget.dataset.idx//把当前用户点击的Tab坐标传给currentTab。
    })
    switch (e.currentTarget.dataset.idx) {
      case 0:
        self.getgoodData();
        break;
      case 1:
        self.getshopdata();
        break;
      case 2:
        self.getgoodData1();
        break;
      case 3:
        self.getgoodData2();
        break;
      case 4:
        self.getgoodData3();
        break;    
    }
  },
  getshopdata:function(){
    let self = this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetAllShop', //仅为示例，并非真实的接口地址
      data: {
        pageNum: 1,
        pageSize: 100
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result = res.data;
        if (result.code == 200 && result.data.list.length > 0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ shopdata: result.data.list })
        }
      }
    })
  },

  getgoodData:function()
  {
    let self=this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetAllGoods', //仅为示例，并非真实的接口地址
      data: {
        pageNum:1,
        pageSize:100
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result=res.data;
        if(result.code==200 && result.data.list.length>0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ goodData: result.data.list })
        }
      }
    })
  },

  getgoodData1: function () {
    let self = this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsOrderByNumber', //仅为示例，并非真实的接口地址
      data: {
        pageNum: 1,
        pageSize: 100
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result = res.data;
        if (result.code == 200 && result.data.list.length > 0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ goodData: result.data.list })
        }
      }
    })
  },

  getgoodData2: function () {
    let self = this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsOrderByPriceAsc', //仅为示例，并非真实的接口地址
      data: {
        pageNum: 1,
        pageSize: 100
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result = res.data;
        if (result.code == 200 && result.data.list.length > 0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ goodData: result.data.list })
        }
      }
    })
  },

  getgoodData3: function () {
    let self = this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsOrderByPriceDesc', //仅为示例，并非真实的接口地址
      data: {
        pageNum: 1,
        pageSize: 100
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result = res.data;
        if (result.code == 200 && result.data.list.length > 0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ goodData: result.data.list })
        }
      }
    })
  },
  jumptoshop:function(e){
    console.log(e);
    let shopno = e.currentTarget.dataset.shopid;
    console.log(shopno);
    wx.navigateTo({
      url: '/pages/shop-detail/shop-detail?shopno='+shopno,
    })
  },
  jumptogood:function(e)
  {
    console.log(e);
    //点击后要获取商品的数据（一般是ID）
    //在进行跳转，将goodid给商品详情页
    let goodno=e.currentTarget.dataset.goodid;
    //console.log(goodno)打印出商品号
    wx.navigateTo({
      url: '/pages/good-detail/good-detail?goodno='+goodno,
    })
  },

  onLoad:function()
  {
    this.getgoodData();
  },

  onShow:function()
  {
    this.getgoodData();
  }
})