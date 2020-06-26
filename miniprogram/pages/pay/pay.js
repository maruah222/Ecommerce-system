// pages/test/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address:{},
    goodData:[],
    srcgoodData:[],
    tempData:[],
    totalprice:0,
    totalnumber:0,
    token:null,
    address:"aaa",
  },
  AddressInput:function(e){
    this.setData({ address:e.detail.value})
    this.data.srcgoodData.forEach(v=>{
      v.address=e.detail.value;
    })
    console.log(this.data.srcgoodData);
  },
  getgoodData:function()
  {
    let self=this;
    //显示加载
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: 'https://ys.lumingx.com/api/manage/GoodsList?pageNo=1&pageSize=10', //仅为示例，并非真实的接口地址
      data: {
        pageNo:1,
        pageSize:10
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        //隐藏加载
        wx.hideLoading();
        console.log(res.data)
        let result=res.data;
        if(result.success && result.data.length>0)//success是自己写的接口的调用成功值，这里是true
        {
          self.setData({ goodData: result.data })
        }
      }
    })
  },
  jumptogood:function(e)
  {
    console.log(e);
    //点击后要获取商品的数据（一般是ID）
    //在进行跳转，将goodid给商品详情页
    let goodno=e.currentTarget.dataset.goodid;
    console.log(goodno)
    wx.navigateTo({
      url: '/pages/good-detail/good-detail?goodno='+goodno,
    })
  },
  onShow(){
    wx.getStorage({
      key: 'token',
      success:(res)=>{
        this.setData({token:res.data});
        let cart=wx.getStorageSync('goodData')||[];
        let cart1=wx.getStorageSync('srcgooddata');
        //过滤后的购物车数组
        cart= cart.filter(v=>v.checkstate);
        this.setData({srcgoodData:cart1});
        this.setData({goodData:cart});
        this.setCart(cart);
      }
    })
    ////////////////////////////////////////////从后台调用this.getgoodData();
  },

  //设置购物车状态同时重新计算底部工具栏数据
  setCart(gooddata){
    let totalprice=0;
    let totalnumber=0;
    this.data.goodData.forEach(v=>{
        totalprice+=v.number*v.price+ !v.ispackage*10;
        totalnumber+=v.number;
    })
    this.setData({totalprice:totalprice});
    this.setData({totalnumber:totalnumber});
    this.setData({goodData:gooddata});
  },
  handlepay:function(){
    let temp=JSON.parse(JSON.stringify(this.data.srcgoodData));
    temp.forEach(v=>{
      v.attribute=v.goodname+v.attribute;
    })
    console.log(temp);
    if(this.data.address===""){
      wx.showToast({
        title: "请填写您的收货地址",
        icon: "none",
        duration: 2000,
      });
    }else{
      wx.request({
        url: 'http://47.105.66.104:8080/ecommerce/User/ConfirmOrderByChart',
        data: temp,
        method: 'POST',
        header: {
          'Authorization': 'Bearer '+this.data.token
        },
        success:(res)=> {
          console.log(res);
          if(res.data.code===200){
            wx.showToast({
              title: "支付成功",
              icon: "none",
              duration: 2000,
            });
            setTimeout(() => {
              wx.navigateBack({
                
              })
            }, 2000);
          }else{
            wx.showToast({
              title: res.data.message+"库存不足",
              icon: "none",
              duration: 2000,
            });
            setTimeout(() => {
              wx.navigateBack({
                
              })
            }, 2000);
          }
        }
      })
      //wx.navigateBack({})
    }
  }
})