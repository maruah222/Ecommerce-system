// pages/seller/seller.js
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
    let self = this;
    console.log(e);
    this.setData({ goodid: e.currentTarget.dataset.goodid });
    this.setData({ goodname: e.currentTarget.dataset.goodname });
    wx.setStorage({
      key: 'goodname',
      data: self.data.goodname,
    }),
      wx.setStorage({
        key: 'goodid',
        data: self.data.goodid,
        success: function (res) {
          console.log(self.data.goodid)
          wx.navigateTo({
            url: '/pages/good-change/good-change',
          })
        },
      })
  },
  getorders:function(){
    wx.downloadFile({
      url: 'http://47.105.66.104:8080/ecommerce/Shop/goodsExcel',
      data:{
        //ShopId:this.data.sellerId
      },
      
      success:(res)=>{
        var filePath = res.tempFilePath;
          console.log(res);
          wx.openDocument({
              filePath: filePath,
              success: function(res) {
                  console.log('打开文档成功')
              },
              fail: function(res) {
                  console.log(res);
              },
              complete: function(res) {
                  console.log(res);
              }
          })
      }
    })
  },
  getdata: function () {
    let self = this;
    wx.request({
      url: 'http://47.105.66.104:8080/ecommerce/User/GetGoodsByShopId',
      header: {        //'content-type': 'application/json' // 默认值
        //这里修改json为text   json的话请求时会返回400(bad request)
        'content-type': 'application/texts'
      },
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
    let self = this;
    wx.getStorage({
      key: 'sellerid',
      success: function (res) {
        self.setData({ sellerId: res.data });
        self.getdata()
      },
    })
  },


  changeseller:function(){
    wx.navigateTo({
      url: '/pages/changeseller/changeseller',
    })
  },

  addgood:function(){
    wx.navigateTo({
      url: '/pages/good-add/good-add',
    })
  },

  deletegood:function(){
    wx.navigateTo({
      url: '/pages/delete-good/delete-good',
    })
  },

  change:function(){
    wx.navigateTo({
      url: '/pages/good-change/good-change',
    })
  },

  manageorder:function(){
    console.log(this.data.goodData[0].sellerID);
    let sellerid = this.data.goodData[0].sellerID;
    wx.navigateTo({
      url: '/pages/manager-order/manager-order',
    })
  },

  changesku:function(){
    wx.navigateTo({
      url: '/pages/good-addsku/good-addsku',
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