// pages/addstock/addstock.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    skuid: "",
    number:0
  },

  AccountInput: function (e) {
    this.setData({ skuid: e.detail.value })
  },

  addInput: function (e) {
    this.setData({ number: e.detail.value })
  },

  submit: function () {
    let self=this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/AddNumberInSku',
      data: {
        skuid:self.data.skuid,
        num:self.data.number
      },
      success: function(res) {
        console.log("进货成功");
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
