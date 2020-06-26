// pages/changeseller/changeseller.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    token: "",
    Name: "1",
    password: "2",
    telephone: "3",
    Address: "4",
    shopname:"5",
    sellername:"6",
  },

  PasswordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },

  telephoneInput: function (e) {
    this.setData({
      telephone: e.detail.value
    })
  },


  AddressInput: function (e) {
    this.setData({
      Address: e.detail.value
    })
  },

  shopnameInput: function (e) {
    this.setData({
      shopname: e.detail.value
    })
  },

  sellernameInput: function (e) {
    this.setData({
      sellername: e.detail.value
    })
  },

  getdata:function(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/GetInformationByShopId',
      method:"GET",
      data: {
        ShopId: self.data.Name
      },
      success: function (res) {
        console.log(res.data);
        self.setData({ password: res.data.data.sellerpassword });
        self.setData({ telephone: res.data.data.sellertelephone });
        self.setData({ Address: res.data.data.shopaddress });
        self.setData({ shopname: res.data.data.shopname });
        self.setData({ sellername: res.data.data.sellername });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let self=this;
    wx.getStorage({
      key: 'sellerid',
      success: function(res) {
        self.setData({Name:res.data});
        self.getdata();
      },
    })
  },

  formSubmit: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/ModifyShopInformation',
      method:"GET",
      header: { "Content-Type": "application/x-www-form-urlencoded" },
      data: {
        sellername: self.data.sellername,
        password: self.data.password,
        shopaddress: self.data.Address,
        shopname:self.data.shopname,
        telephone: self.data.telephone,
        shopId:self.data.Name
      },
      success: function (res) {
        console.log(res);
        wx.navigateBack({
          delta: 1
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})