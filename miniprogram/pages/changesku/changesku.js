// pages/changesku/changesku.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    skuid:0,
    price1:0,
    vipprice1:0,
    goodid:"",
    picture:"",
  },


  priceInput: function (e) {
    this.setData({ price1: e.detail.value })
  },

  vippriceInput: function (e) {
    this.setData({ vipprice1: e.detail.value })
  },

  pictureInput: function (e) {
    this.setData({ picture: e.detail.value })
  },

  submit:function(){
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/ModifySku',
      data: {
        skuId:self.data.skuid,
        picture:self.data.picture,
        price:self.data.price1,
        vipprice:self.data.vipprice1,
      },
      success: function(res) {
        console.log("修改成功");
        wx.navigateBack({
          delta: 1
        });
      },
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let self=this;
    wx.getStorage({
      key: 'goodid',
      success: function(res) {
        self.setData({goodid:res.data});
        console.log(self.data.goodid);
      },
    })
    wx.getStorage({
      key: 'sku1',
      success: function(res) {
        self.setData({skuid:res.data});
        console.log(self.data.skuid);
      },
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