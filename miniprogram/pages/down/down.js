// pages/down/down.js
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

  jumptogood:function(e){
    let self = this;
    console.log(e);
    this.setData({ goodid: e.currentTarget.dataset.goodid });
    console.log(self.data.goodid);
    wx.showModal({
      title: '提示',
      content: '是否要上架商品',
      confirmText: "确认",
      cancelText: "取消",
      success(res) {
        if (res.confirm) {
          self.shangjia();
        } else if (res.cancel) {
          console.log("取消删除")
        }
      },
    })
  },

  shangjia:function(){
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/ApplyDownGoodsUp',
      data: {
        GoodId: self.data.goodid,
      },
      success: function (res) {
        console.log("已申请上架，请等待审核");
        self.getdata();
      },
    })
  },

  getdata: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/getDownGoodsByShopId',
      data: {
        ShopId: self.data.sellerId,
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
    let self = this;
    wx.getStorage({
      key: 'sellerid',
      success: function (res) {
        self.setData({ sellerId: res.data });
        self.getdata()
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