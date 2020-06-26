Page({

  /**
   * 页面的初始数据
   */
  data: {
    state:"未登录",
    name:""
  },
  handle_waiting_pay:function(){
    wx.navigateTo({
      url: '../order/order',
    })
  },

  changemine:function(){
    wx.navigateTo({
      url: '/pages/changemine/changemine',
    })
  },

  logout:function(){
    let self=this;
    wx.clearStorage({
      success:function(){
        self.setData({name:""});
        self.setData({state:"未登录"});
      }
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
    let self = this;
    wx.getStorage({
      key: 'token',
      success: function (res) {
        self.setData({ state: "已登陆" });
      },
    })
    wx.getStorage({
      key: 'userid',
      success: function (res) {
        self.setData({ name:res.data });
      },
    })
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