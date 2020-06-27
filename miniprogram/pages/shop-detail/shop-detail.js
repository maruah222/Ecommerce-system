// pages/shop-detail/shop-detail.js
Page({

  /**
   * 页面的初始数据
   */

  data: {
    sellerId: '',
    goodname: '',
    goodid: '',
    goodData: [],
  },

  jumptogood: function (e) {
    //点击后要获取商品的数据（一般是ID）
    //在进行跳转，将goodid给商品详情页
    let goodno = e.currentTarget.dataset.goodid;
    //console.log(goodno)打印出商品号
    wx.navigateTo({
      url: '/pages/good-detail/good-detail?goodno=' + goodno,
    })
  },

  getdata: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsByShopId',
      data: {
        shopId: self.data.sellerId,
        pageNum: 1,
        pageSize: 10,
      },
      success: function (res) {
        console.log(res);
        self.setData({ goodData: res.data.data.list });
        console.log(self.data.goodData)
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.sellerId = options.shopno;
    this.getdata();
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
